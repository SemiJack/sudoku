import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import sudoku.*;

import static org.junit.Assert.*;

public class UnitTest {

    @Test
    @DisplayName("Test for Solver, pass if Solver correctly solve sudoku.")
    public void shouldSolve() {
        int[][] board1 = {
                { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                { 0, 9, 0, 0, 0, 0, 4, 0, 0 }};

        int[][] board2 = {
                {0, 2, 7, 5, 6, 8, 0, 9, 3},
                {0, 0, 0, 0, 0, 7, 5, 0, 4},
                {0, 0, 5, 3, 4, 0, 0, 0, 7},
                {1, 0, 0, 6, 0, 5, 7, 4, 0},
                {5, 0, 8, 7, 3, 0, 0, 0, 2},
                {7, 0, 0, 2, 0, 0, 3, 5, 0},
                {0, 4, 0, 0, 0, 1, 0, 7, 0},
                {8, 0, 0, 9, 0, 6, 4, 3, 0},
                {0, 0, 6, 4, 7, 0, 0, 0, 0}};

        int[][] board1Solved = {
                {8 ,1 ,2 ,7 ,5, 3 ,6 ,4 ,9},
                {9 ,4 ,3, 6 ,8, 2 ,1, 7, 5},
                {6 ,7, 5, 4, 9 ,1, 2, 8, 3},
                {1 ,5, 4 ,2 ,3 ,7, 8 ,9 ,6},
                {3 ,6, 9, 8, 4, 5 ,7, 2 ,1},
                {2 ,8 ,7 ,1 ,6 ,9 ,5, 3, 4},
                {5, 2, 1 ,9, 7 ,4 ,3 ,6 ,8},
                {4 ,3 ,8 ,5, 2 ,6, 9 ,1, 7},
                {7, 9 ,6 ,3 ,1 ,8 ,4 ,5 ,2}};

        int[][] board2Solved = {
                {4, 2, 7, 5, 6, 8, 1, 9, 3},
                {6, 8, 3, 1, 9, 7, 5, 2, 4},
                {9, 1, 5, 3, 4, 2, 8, 6, 7},
                {1, 3, 2, 6, 8, 5, 7, 4, 9},
                {5, 9, 8, 7, 3, 4, 6, 1, 2},
                {7, 6, 4, 2, 1, 9, 3, 5, 8},
                {3, 4, 9, 8, 5, 1, 2, 7, 6},
                {8, 7, 1, 9, 2, 6, 4, 3, 5},
                {2, 5, 6, 4, 7, 3, 9, 8, 1}};

        Solver solver = new Solver();
        Assert.assertArrayEquals(board1Solved, solver.solve(board1));
        Assert.assertArrayEquals(board2Solved, solver.solve(board2));
    }

        @Test
        @DisplayName("Test for wrong Level input value. Should change level to 0")
        public void shouldReactOnWrongLevel () {
            Sudoku sudoku = new Sudoku(-19);
            assertEquals(0, sudoku.getLevel());
            sudoku.setLevel(45);
            assertEquals(0, sudoku.getLevel());
        }

        @Test
        @DisplayName("Test for incorrect reading file with sudoku.")
        public void shouldIncorrectlyReadFile () {
            int[][] expextedArray1 = {
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}};
            int[][] array1 = FileReaderTo2D.readFile("test/test1_1_SudokuFile.txt");
            assertArrayEquals(expextedArray1, array1);
            int[][] array2 = FileReaderTo2D.readFile("test/test1_2_SudokuFile.txt");
            assertArrayEquals(expextedArray1, array2);
        }

        @Test
        @DisplayName("Test for correct reading file with sudoku.")
        public void shouldCorrectlyReadFile () {
            int[][] expextedArray = {
                    {4, 2, 7, 5, 6, 8, 1, 9, 3},
                    {6, 8, 3, 1, 9, 7, 5, 2, 4},
                    {9, 1, 5, 3, 4, 2, 8, 6, 7},
                    {1, 3, 2, 6, 8, 5, 7, 4, 9},
                    {5, 9, 8, 7, 3, 4, 6, 1, 2},
                    {7, 6, 4, 2, 1, 9, 3, 5, 8},
                    {3, 4, 9, 8, 5, 1, 2, 7, 6},
                    {8, 7, 1, 9, 2, 6, 4, 3, 5},
                    {2, 5, 6, 4, 7, 3, 9, 8, 1}};
            int[][] array = FileReaderTo2D.readFile("test/test2SudokuFile.txt");
            assertArrayEquals(expextedArray, array);
        }


    }
