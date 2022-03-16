package sudoku;

import java.util.Arrays;


/**
 * Generates sudoku puzzle semi-randomly. It uses predefined boards and does a lot of random column and row swapping. At the end it checks if puzzle is solvable.
 */
public class Generator {
    /**
     * Predefined boards.
     */
    private final int[][] easy_1 = {
            {4, 2, 7, 5, 6, 8, 1, 9, 3},
            {6, 8, 3, 1, 9, 7, 5, 2, 4},
            {9, 1, 5, 3, 4, 2, 8, 6, 7},
            {1, 3, 2, 6, 8, 5, 7, 4, 9},
            {5, 9, 8, 7, 3, 4, 6, 1, 2},
            {7, 6, 4, 2, 1, 9, 3, 5, 8},
            {3, 4, 9, 8, 5, 1, 2, 7, 6},
            {8, 7, 1, 9, 2, 6, 4, 3, 5},
            {2, 5, 6, 4, 7, 3, 9, 8, 1}};
    private final int[][] medium_1 = {
            {6, 8, 5, 9, 1, 3, 4, 2, 7},
            {4, 2, 3, 6, 8, 7, 9, 1, 5},
            {9, 7, 1, 2, 5, 4, 6, 8, 3},
            {1, 9, 2, 5, 3, 8, 7, 6, 4},
            {3, 4, 7, 1, 6, 2, 5, 9, 8},
            {8, 5, 6, 4, 7, 9, 1, 3, 2},
            {2, 6, 9, 3, 4, 5, 8, 7, 1},
            {5, 1, 8, 7, 2, 6, 3, 4, 9},
            {7, 3, 4, 8, 9, 1, 2, 5, 6}
    };

    /**
     * Generates puzzle with specified difficulty level.
     *
     * @param level Difficulty level
     * @return int[][] with sudoku puzzle
     */
    public int[][] generate(int level) {
        if (level == 0) {
            return mixPuzzle(level, this.easy_1);
        } else {
            return mixPuzzle(level, this.medium_1);
        }
    }

    /**
     * Does permutation of predefined board, and deletes fields according to difficulty level.
     *
     * @param level diifficulty level
     * @param array predefined sudoku puzzle
     * @return ready to play "brand-new" sudoku puzzle
     */
    private int[][] mixPuzzle(int level, int[][] array) {
        int deletes;
        if (level == 0) {
            deletes = 40;
        } else {
            deletes = 55;
        }

        int[][] puzzle;
        Solver solver = new Solver();
        do {
            puzzle = Arrays.stream(array).map(int[]::clone).toArray(int[][]::new);
            int i = Random.generateRandomInt(0, 4);
            while (i >= 0) {
                swapColumns(puzzle);
                i--;
            }
            i = Random.generateRandomInt(0, 4);
            while (i >= 0) {
                swap3xRows(puzzle);
                i--;
            }
            i = Random.generateRandomInt(0, 4);
            while (i >= 0) {
                swap3xColumns(puzzle);
                i--;
            }
            i = Random.generateRandomInt(0, 4);
            while (i >= 0) {
                swapRows(puzzle);
                i--;
            }
            for (int a = 0; a < deletes; a++) {
                deleteRandomField(puzzle);
            }
        } while (solver.solve(puzzle) == null);
        return puzzle;
    }

    /**
     * Deletes random field in sudoku puzzle.
     *
     * @param puzzle sudoku puzzle from which fields are deleted
     */
    private void deleteRandomField(int[][] puzzle) {
        int i = Random.generateRandomInt(0, 8);
        int j = Random.generateRandomInt(0, 8);
        puzzle[i][j] = 0;
    }

    /**
     * Swaps 2 random groups of 3 columns in 2-dimensional 9x9 array. Possible groups 0-2, 3-5, 6-8 (indexes).
     *
     * @param array int[][] array 9x9
     */
    private void swap3xColumns(int[][] array) {
        boolean flag = true;
        int a = 0;
        int b = 0;

        while (flag) {
            flag = false;
            a = Random.generateRandomInt(0, 2) * 3;
            b = Random.generateRandomInt(0, 2) * 3;
            if (a == b) {
                flag = true;
            }
        }
        try {
            int temp;
            int c;
            int r;
            for (int i = 0; i <= 8; i++) {
                c = a;
                r = b;
                for (int p = 0; p < 3; p++) {
                    temp = array[i][c];
                    array[i][c] = array[i][r];
                    array[i][r] = temp;
                    c++;
                    r++;
                }
            }
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
            Colors.red("In: swap3xColumns(). Index in array out of bounds!");
        }
    }

    /**
     * Swaps 2 random groups of 3 rows in 2-dimensional 9x9 array. Possible groups 0-2, 3-5, 6-8 (indexes).
     *
     * @param array int[][] array 9x9
     */
    private void swap3xRows(int[][] array) {
        boolean flag = true;
        int a = 0, b = 0;

        while (flag) {
            flag = false;
            a = Random.generateRandomInt(0, 2) * 3;
            b = Random.generateRandomInt(0, 2) * 3;
            if (a == b) {
                flag = true;
            }
        }
        try {
            int[] row;
            for (int i = 0; i < 3; i++) {
                row = array[a];
                array[a] = array[b];
                array[b] = row;
                a++;
                b++;
            }
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
            Colors.red("In: swap3xRows(). Index in array out of bounds!");
        }
    }

    /**
     * Swaps 2 random columns in 2-dimensional 9x9 array.
     *
     * @param array int[][] array 9x9
     */
    private void swapColumns(int[][] array) {
        boolean flag = true;
        int a = 0;
        int b = 0;

        while (flag) {
            flag = false;
            a = Random.generateRandomInt(0, 8);
            b = Random.generateRandomInt(0, 8);
            if (a == b) {
                flag = true;
            }
        }
        try {
            int temp;
            for (int i = 0; i < 9; i++) {
                temp = array[i][a];
                array[i][a] = array[i][b];
                array[i][b] = temp;
            }
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
            Colors.red("In: swapColumns(). Index in array out of bounds!");
        }
    }

    /**
     * Swaps 2 random rows in 2-dimensional 9x9 array.
     *
     * @param array int[][] array 9x9
     */
    private void swapRows(int[][] array) {
        boolean flag = true;
        int a = 0, b = 0;

        while (flag) {
            flag = false;
            a = Random.generateRandomInt(0, 8);
            b = Random.generateRandomInt(0, 8);
            if (a == b) {
                flag = true;
            }
        }
        try {
            int[] row;
            row = array[a];
            array[a] = array[b];
            array[b] = row;
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
            Colors.red("In: swapRows(). Index in array out of bounds!");
        }
    }
}
