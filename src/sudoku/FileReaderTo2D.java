package sudoku;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Reads from txt file in format which provides SaveToTXT
 */
public class FileReaderTo2D {
    public static final int ROWS = 9;
    public static final int COLUMNS = 9;
    public static int[][] zero = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    /**
     * Reads txt file with sudoku puzzle. Opens file explorer window to choose file.
     *
     * @return sudoku int[][] array
     */
    public static int[][] readFile() {

        int[][] sudokuTable = new int[ROWS][COLUMNS];
        try (Scanner scanner = new Scanner(choseFile())) {
            while (scanner.hasNextLine()) {
                for (int i = 0; i < sudokuTable.length; i++) {
                    String[] strings = scanner.nextLine().split("\\p{Punct}");
                    if (strings.length != 9) {
                        throw new IllegalColumnNumberException();
                    }
                    for (int j = 0; j < strings.length; j++) {

                        sudokuTable[i][j] = Integer.parseInt(strings[j]);
                        if (sudokuTable[i][j] < 0 || sudokuTable[i][j] > 9) {
                            throw new NumberException();
                        }
                    }
                }
            }
            return sudokuTable;

        } catch (FileNotFoundException | IllegalColumnNumberException | NumberException e) {
            e.printStackTrace();
            return zero;
        }

    }

    /**
     * Reads from given txt file.
     *
     * @param filepath filepath of readed file
     * @return sudoku int[][] array
     */
    public static int[][] readFile(String filepath) {

        int[][] sudokuTable = new int[ROWS][COLUMNS];
        try (Scanner scanner = new Scanner(new File(filepath))) {

            while (scanner.hasNextLine()) {
                for (int i = 0; i < sudokuTable.length; i++) {
                    String[] strings = scanner.nextLine().split("\\p{Punct}");
                    if (strings.length != 9) {
                        throw new IllegalColumnNumberException();
                    }
                    for (int j = 0; j < strings.length; j++) {

                        sudokuTable[i][j] = Integer.parseInt(strings[j]);
                        if (sudokuTable[i][j] < 0 || sudokuTable[i][j] > 9) {
                            throw new NumberException();
                        }
                    }
                }
            }
            return sudokuTable;
        } catch (FileNotFoundException | IllegalColumnNumberException | NumberException e) {
            e.printStackTrace();
            return zero;
        }

    }

    /**
     * Opens file explorer to choose file.
     *
     * @return File object with readed sudoku puzzle
     */
    private static File choseFile() {
        FileDialog dialog = new FileDialog((Frame) null);
        dialog.dispose();
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible((true));
        File[] file = dialog.getFiles();
        dialog.dispose();
        return file[0];
    }
}
