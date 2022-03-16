package sudoku;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Solver solves sudoku puzzle.
 */
public class Solver {
    private final int BOARD_START_INDEX = 0;
    private final int BOARD_SIZE = 9;
    private final int NO_VALUE = 0;

    /**
     * Creates copy of an array and generates unique solution.
     *
     * @param array board to solve
     * @return solved copy of board if solution is unique
     */
    public int[][] solve(int[][] array) {
        int[][] solution = Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
        int[][] secondSolution = Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
        if (generateSolution(solution, false)) {
            generateSolution(secondSolution, true);
            if (Arrays.deepEquals(solution, secondSolution)) {
                return solution;
            }
            return null;
        }
        return null;
    }


    /**
     * Solves sudoku using backtracking algorithm
     *
     * @param board to solve
     * @return true if board is solvable and solution is unique
     */
    private boolean generateSolution(int[][] board, boolean reverseNumToTry) throws ArrayIndexOutOfBoundsException {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (board[row][column] == NO_VALUE) {
                    int MIN_VALUE = 1;
                    int MAX_VALUE = 9;
                    if (!reverseNumToTry) {
                        for (int numToTry = MIN_VALUE; numToTry <= MAX_VALUE; numToTry++) {
                            board[row][column] = numToTry;
                            if (isValid(board, row, column) && generateSolution(board, false)) {
                                return true;
                            }
                        }
                        board[row][column] = NO_VALUE;
                    } else {
                        for (int numToTry = MAX_VALUE; numToTry >= MIN_VALUE; numToTry--) {
                            board[row][column] = numToTry;
                            if (isValid(board, row, column) && generateSolution(board, true)) {
                                return true;
                            }

                            board[row][column] = NO_VALUE;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check validity of inserted values in row and column and subsection.
     *
     * @param board  number to validate
     * @param row    number to validate
     * @param column number to validate
     * @return true if sudoku rules are met
     */
    private boolean isValid(int[][] board, int row, int column) {
        return (rowConstraint(board, row)
                && columnConstraint(board, column)
                && boxConstraint(board, row, column));
    }

    /**
     * Checks if number in row is not repeated
     *
     * @param board to check
     * @param row   number to check
     * @return true if requirements are met
     */
    private boolean rowConstraint(int[][] board, int row) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(column -> checkConstraint(board, row, constraint, column));
    }

    /**
     * Checks if number in column is not repeated
     *
     * @param board  to check
     * @param column number to check
     * @return true if requirements are met
     */
    private boolean columnConstraint(int[][] board, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        return IntStream.range(BOARD_START_INDEX, BOARD_SIZE)
                .allMatch(row -> checkConstraint(board, row, constraint, column));
    }

    /**
     * Checks if number in 3x3 box is not repeated
     *
     * @param board  to check
     * @param row    number to check
     * @param column number to check
     * @return true if requirements are met
     */
    private boolean boxConstraint(int[][] board, int row, int column) {
        boolean[] constraint = new boolean[BOARD_SIZE];
        int BOX_SIZE = 3;
        int boxRowStart = (row / BOX_SIZE) * BOX_SIZE;
        int boxRowEnd = boxRowStart + BOX_SIZE;

        int boxColumnStart = (column / BOX_SIZE) * BOX_SIZE;
        int boxColumnEnd = boxColumnStart + BOX_SIZE;

        for (int r = boxRowStart; r < boxRowEnd; r++) {
            for (int c = boxColumnStart; c < boxColumnEnd; c++) {
                if (!checkConstraint(board, r, constraint, c)) return false;
            }
        }
        return true;
    }

    /**
     * Checks if number is not repeating
     *
     * @param board      to check
     * @param row        number to check
     * @param constraint list that represent values from 1-9, should all be false
     * @param column     number to check
     * @return true if number doesn't repeat
     */
    private boolean checkConstraint(int[][] board, int row, boolean[] constraint, int column) {
        if (board[row][column] != NO_VALUE) {
            if (!constraint[board[row][column] - 1]) {
                constraint[board[row][column] - 1] = true;
            } else {
                return false;
            }
        }
        return true;
    }
}
