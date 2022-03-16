package sudoku;

/**
 * This exception is responsible for wrong number value, which user want to set in sudoku.
 */
public class NumberException extends Exception {

    public NumberException() {
        super();
    }
    public String getMessage(){
        return "Illegal number value! Must be 0-9.";
    }
}
