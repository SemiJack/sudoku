package sudoku;

import javax.swing.*;
import java.awt.*;

public class DisplayBoard {
    /**
     * Print board in new window.
     *
     * @param board sudoku puzzle int[][] array
     */
    public static void display(int[][] board) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700,700);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9,9, 3 ,3));
        JTextArea[][] text = new JTextArea[9][9];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                text[i][j] = new JTextArea();
                text[i][j].setText("" + board[i][j]);
                text[i][j].setEditable(false);
                Font font = new Font("Verdana", Font.BOLD, 40);
                text[i][j].setFont(font);

                panel.add(text[i][j]);
            }
        }
        frame.add(panel);
        frame.setVisible(true);
    }
}
