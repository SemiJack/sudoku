package sudoku;

/**
 * This class is representation of sudoku board.
 */
public class Board {
    private int[][] board;
    int level;

    /**
     * Constructor of Board Class Object
     *
     * @param level Sudoku difficulty level.
     * @param board Sudoku unsolved board.
     */
    public Board(int level, int[][] board) {
        setBoard(board);
        setLevel(level);
    }

    /**
     * Constructor of Board Class Object
     *
     * @param board Sudoku unsolved board.
     */
    public Board(int[][] board) {
        this.board = board;
    }

    /**
     * Gets board.
     *
     * @return board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Returns value of one specified field in board.
     *
     * @param row Row index
     * @param col Column index
     * @return Value of specified field in board
     */
    public int getField(int row, int col) {
        return board[row][col];
    }

    /**
     * Sets specified field in board.
     *
     * @param row   row int value
     * @param col   column int value
     * @param value value to fill specified field
     */
    public void setField(int row, int col, int value) {
        this.board[row][col] = value;
    }

    /**
     * Gets board difficulty level.
     *
     * @return board difficulty level
     */
    public int getLevel() {
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    /**
     * Sets board.
     */
    public void setBoard(int[][] array) {
        this.board = array;
    }

    /**
     * Saves board to .txt file. Which can be reloaded to application with FileReaderTo2D.
     *
     * @param path filepath for saved .txt file
     */
    public void saveBoardToTxt(String path) {
        SaveToTXT.save(this.board, path);
    }

    /**
     * Prints board on console.
     */
    public void printBoard() {
        StringBuilder sB = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                sB.append(board[row][column]);
                sB.append("  ");
            }
            sB.append("\n");
        }
        System.out.println(sB);
    }
}
