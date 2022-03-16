package sudoku;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class which provides: generate, set, get, print and other base operations on sudoku play-unsolvedBoard.
 */
public class Sudoku {
    private Board unsolvedBoard;
    private int level = 0;
    private Board solution;
    private Board boardToModify;

    /**
     * Constructor to use Sudoku as generator
     * @param level Sudoku difficulty
     */
    public Sudoku(int level) {
        setLevel(level);
        Generator generator = new Generator();
        this.unsolvedBoard = new Board(level, generator.generate(getLevel()));
        this.boardToModify = new Board(level, unsolvedBoard.getBoard());
        generateSolution();
    }

    /**
     * Constructor to use Sudoku as solver
     * @param array sudoku board to solve
     */
    public Sudoku(int level, int[][] array) {
        this.unsolvedBoard = new Board(level,array);
        this.boardToModify = new Board(level,array);
        generateSolution();
    }

    /**
     * Displays solved board in pop up window
     */
    public void displaySolution(){
        DisplayBoard.display(this.solution.getBoard());
    }

    /**
     * Displays unsolved board in pop up window
     */
    public void displayUnsolvedBoard(){
        DisplayBoard.display(this.unsolvedBoard.getBoard());
    }

    /**
     * Displays modified board in pop up window
     */
    public void displayModifiedBoard(){
        DisplayBoard.display(this.boardToModify.getBoard());
    }
    /**
     * Prints unsolved board in terminal
     */
    public void printUnsolvedBoard() {
        this.unsolvedBoard.printBoard();
    }

    /**
     * Prints solution board in terminal
     */
    public void printSolution() {
        this.solution.printBoard();
    }

    /**
     * Prints modified board in terminal
     */
    public void printBoardToModify(){
        this.boardToModify.printBoard();
    }

    /**
     * Saves solved board to file
     */
    public void saveSolutionToFile() throws IOException {
        solution.saveBoardToTxt("solution.txt");
    }

    /**
     * Saves unsolved board to file
     */
    public void saveUnsolvedBoardToFile() throws IOException {
        unsolvedBoard.saveBoardToTxt("boardToSolve.txt");
    }
    /**
     * Saves modified board to file
     */
    public void saveModifiedBoardToFile() throws IOException {
        boardToModify.saveBoardToTxt("progress.txt");
    }

    /**
     * Saves unsolved board to pdf
     */
    public void saveUnsolvedBoardToPdf(){
        SaveToPDF saver = new SaveToPDF();
        saver.saveArrayToFile(unsolvedBoard.getLevel(), unsolvedBoard.getBoard(), "sudoku");
    }


    /**
     * Compares provided board with solution to sudoku
     * @param solToCheck Solution to sudoku
     */
    public void checkSolution(int[][] solToCheck){
        if(Arrays.deepEquals(solution.getBoard(), solToCheck)){
            System.out.println("Solution is correct");
        }else{
            System.out.println("Solution is incorrect");
        }
    }

    /**
     * Checks if user enteret good solution
     */
    public void checkBoardToModify(){
        checkSolution(this.boardToModify.getBoard());
    }

    /**
     * Compares provided board with solution to sudoku.
     * User selects file containing 2D array.
     * Numbers must be separated with '|'
     */
    public void checkSolutionFromFile(){
        if(Arrays.deepEquals(solution.getBoard(), FileReaderTo2D.readFile())){
            System.out.println("Solution is correct");
        }else{
            System.out.println("Solution is incorrect");
        }
    }

    /**
     *Generates solution to provided board.
     */
    private void generateSolution() {
        if (solution == null) {
            Solver solver = new Solver();
            int[][] array = solver.solve(this.unsolvedBoard.getBoard());
            this.solution = new Board(array);
        }
    }

    /**
     * Fills field at given coordinates with given value
     * @param row number
     * @param col number
     * @param value number
     */
    public void fillField(int row, int col, int value) {
        boardToModify.setField(row, col, value);
    }

    /**
     * Sets sudoku level
     * @param level number 0 or 1
     */
    public void setLevel(int level) {
        try {
            if (level > 1 || level < 0) {
                throw new WrongLevelException();
            } else {
                this.level = level;
            }
        } catch (WrongLevelException wle) {
            System.out.println(wle.getMessage());
            this.level = 0;
        }
    }

    /**
     * Gets level
     * @return difficulty level
     */
    public int getLevel() {
        return this.level;
    }
}
