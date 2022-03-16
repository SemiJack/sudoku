package sudoku;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class saves sudoku puzzle board to .txt file, which can be reloaded in this application.
 */
public class SaveToTXT {
    /**
     * Saves sudoku pullze to .txt file.
     * @param array sudoku puzzle int[][] array
     * @param name filename of new .txt file
     */
    public static void save(int[][] array, String name){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(name))) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    writer.write(String.valueOf(array[i][j]));
                    if (j < (array.length - 1)) {
                        writer.write("|");
                    }
                }
                if (i < (array.length - 1)) {
                    writer.write("\r\n");
                }
            }
            writer.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            ioe.getMessage();
        }


    }
}
