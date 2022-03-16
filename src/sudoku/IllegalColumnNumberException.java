package sudoku;

/**
 * This exception is responsible for wrong number of columns in actually reading sudoku from file.
 */
public class IllegalColumnNumberException extends Exception {
    public  IllegalColumnNumberException () {
        super();
    }
    public String getMessage(){
        return "Błędna ilość kolumn we wczytywanej planszy!";
    }




}
