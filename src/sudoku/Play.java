package sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class to interact with user.
 */
public class Play {
    public static void main(String[] args){
        start();
    }

    /**
     * Operates Sudoku class
     */
    private static void start(){
        System.out.println("Welcome!");
        String conditon = "";
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
        while (!conditon.equals("Exit")){
            System.out.println("Type 'Generator' to enter generator mode or 'Solver' to solver mode. Type 'Exit' to exit.");
            conditon = br.readLine();
            String command = "";

            if (conditon.equals("Generator")) {
                System.out.println("""
                        Choose level:
                        0-easy\s
                        1-hard""");

                Sudoku sudoku = createSudoku(br.readLine());
                if (sudoku == null){
                    command = "Change mode";
                } else {
                    commands();
                }

                while (!command.equals("Change mode") && !command.equals("Change level")) {
                    System.out.print("(Generator mode)Type command:");
                    command = br.readLine();
                    operate(sudoku,br,command);
                }
            } else if (conditon.equals("Solver")) {
                System.out.print("Type difficulty of sudoku: 0 - easy, 1- hard\n");
                command = br.readLine();
                Sudoku sudoku = new Sudoku(Integer.parseInt(command),FileReaderTo2D.readFile());
                commands();
                while (!command.equals("Change mode")) {
                    System.out.print("(Solver mode)Type command: ");
                    command = br.readLine();
                    operate(sudoku,br,command);
                }
            }else if(!conditon.equals("Exit")){
                System.out.println("Wrong command");
            }
        }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Methos used to operate Solver mode
     * @param sudoku Sudoku object to solve
     * @param br Buffered reader object
     * @param command to execute
     * @throws IOException possible exception
     */
    private static void operate(Sudoku sudoku, BufferedReader br, String command) throws IOException {
        switch (command) {
            case "Fill mode" -> fillMode(sudoku, br);
            case "Print solution" -> sudoku.printSolution();
            case "Print board to solve" -> sudoku.printUnsolvedBoard();
            case "Print modified board" -> sudoku.printBoardToModify();
            case "Display solution" -> sudoku.displaySolution();
            case "Display board to solve" -> sudoku.displayUnsolvedBoard();
            case "Check" -> sudoku.checkBoardToModify();
            case "Load and check" -> sudoku.checkSolutionFromFile();
            case "Save board to solve to pdf" -> sudoku.saveUnsolvedBoardToPdf();
            case "Save solution to txt" -> sudoku.saveSolutionToFile();
            case "Save board to solve to txt" -> sudoku.saveUnsolvedBoardToFile();
            case "Save progress to txt" -> sudoku.saveModifiedBoardToFile();
            case "Help" -> commands();
            case "Change mode", "Exit" -> {
                break;
            }
            default -> System.out.println("Unknown command. Type 'help' to print available commands");
        }
    }

    /**
     * Creates Sudoku class
     * @param lvl game difficulty
     * @return Sudoku class
     */
    private static Sudoku createSudoku(String lvl){
        try {
            return  new Sudoku(Integer.parseInt(lvl));
        }catch (NumberFormatException ex){
            System.out.println("You must enter 0 or 1");
            return null;
        }
    }

    /**
     * Method used to fill numbers in sudoku board
     * @param sudoku Sudoku clas
     * @param br Buffered reader object
     * @throws IOException possible Exception
     */
    private static void fillMode(Sudoku sudoku, BufferedReader br) throws IOException {
        sudoku.printBoardToModify();
        System.out.println("""
                You are in fill mode.\s
                Fill board by typing [row] [col] [value], make sure to type spaces between numbers
                Exit fill mode by typing 'Exit fill mode'""");
        String command = "";
        while (!command.equals("Exit fill mode")){

            command = br.readLine();
            if (command.matches("[1-9]\s[1-9]\s[0-9]")) {
                try {
                    List<Integer> ints = Arrays.stream(command.split("\s"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());
                    sudoku.fillField(ints.get(0)-1, ints.get(1)-1, ints.get(2));
                    System.out.println("Row- "+ ints.get(0)+" Col-" + ints.get(1) + " Changed to "+ ints.get(2));
                    sudoku.printBoardToModify();
                }catch (NumberFormatException ex){
                    Colors.red("Incorrect value!");
                    ex.printStackTrace();
                }
            }else if(command.equals("Print modified board")){
                sudoku.printBoardToModify();
            }else if(!command.equals("Exit fill mode")){
                System.out.println("Remember about [row] [col] [value] format" +
                        "\n or type 'Exit fill mode' to exit fill mode");
            }
        }
    }

    /**
     * Prints available commands
     */
    private static void commands() {
        System.out.println("Available comands:" +
                "\n-Type " +Colors.yellow("Fill mode")+ " to begin fill board" +
                "\n-Type " +Colors.yellow("Print solution")+" to print solved board" +
                "\n-Type " +Colors.yellow("Print board to solve")+" to print board to solve" +
                "\n-Type " +Colors.yellow("Print modified board")+" to print modified board" +
                "\n-Type " +Colors.yellow("Display solution")+" to display solved board in separated window. " +
                "\n-Type " +Colors.yellow("Display board to solve")+" to display unsolved board on the screen" +
                "\n-Type " +Colors.yellow("Check")+" to check if solution is valid" +
                "\n-Type " +Colors.yellow("Load and check") +" to check solution to board by loadning a file with solution"+
                "\n-Type " +Colors.yellow("Save board to solve to pdf")+" to save unsolved board to pdf" +
                "\n-Type " +Colors.yellow("Save solution to txt")+" to save solution to txt" +
                "\n-Type " +Colors.yellow("Save board to solve to txt")+" to save unsolved board to txt" +
                "\n-Type " +Colors.yellow("Save progress to txt")+" to save progress uo txt" +
                "\n-Type " +Colors.yellow( "Change mode")+" to change mode" +
                "\n-Type " +Colors.yellow("Exit")+" to exit" +
                "\n-Type " +Colors.yellow("Help")+" to print available commands");
    }

}
