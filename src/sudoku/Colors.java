package sudoku;

/**
 * Class which provides color printing in console.
 */
public class Colors {
    /**
     * Prints message in red color.
     * @param message Printing message
     */
    public static void red(String message){
        System.out.println("\u001B[31m"+message+"\u001B[0m");
    }
    /**
     * Prints message in green color.
     * @param message Printing message
     */
    public static void green(String message){
        System.out.println("\u001B[32m"+message+"\u001B[0m");
    }
    /**
     * Return message in yellow color.
     * @param message  message to color
     * @return yellow message
     */
    public static String yellow(String message){
        return "\u001B[33m"+message+"\u001B[0m";
    }
}
