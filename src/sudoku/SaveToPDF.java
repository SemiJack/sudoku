package sudoku;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class provides saving sudoku do PDF file, which can be used to print it on printer.
 */
public class SaveToPDF {
    /**
     * Saves gives 9x9 sudoku puzzle array to .pdf file.
     *
     * @param level    level of sudoku puzzle
     * @param array    sudoku puzzle int[][] array
     * @param filename name for saving file without ".extension" (i.e. .pdf)
     */
    public void saveArrayToFile(int level, int[][] array, String filename) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("" + filename + ".pdf"));
            document.open();
            Font font = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
            Paragraph para = new Paragraph();
            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(60);

            makeHeader(level,font, para);
            document.add(para);
            addRows(table, array, font);
            document.add(table);
            document.close();
            String path = new File(".").getCanonicalPath();
            System.out.println("Saved sudoku to 'sudoku.pdf' in: "+path.toString());
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            document.close();
        }
    }

    /**
     * Makes header.
     *
     * @param level difficulty level of sudoku puzzle
     * @param font  font used to write text
     * @param para  Paragraph where Header should be placed
     */
    private void makeHeader(int level, Font font, Paragraph para) {
        String difficulty;
        if (level == 0) {
            difficulty = "Easy";
        } else if (level == 1) {
            difficulty = "Hard";
        } else {
            difficulty = "Unknown";
        }
        Phrase text = new Phrase(("Sudoku " + difficulty), font);
        para.add(text);
        para.setAlignment(Element.ALIGN_CENTER);
        para.setSpacingAfter(15);
    }

    /**
     * Fill whole table.
     *
     * @param table PdfPTable Object which will be filled
     * @param array int[][] array with values to fill table
     * @param font  font used to write text
     */
    private void addRows(PdfPTable table, int[][] array, Font font) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int value = array[i][j];
                String phrase;
                if (value == 0) {
                    phrase = " ";
                } else {
                    phrase = Integer.toString(value);
                }
                PdfPCell cell = new PdfPCell(new Phrase(phrase, font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setMinimumHeight(50);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setFixedHeight(34);
                table.addCell(cell);
            }
        }
    }
}