package sudoku;

/**
 * This exception is responsible for wrong level value.
 */
public class WrongLevelException extends Exception {
    public WrongLevelException() {
        super();
    }
    public String getMessage(){
        return "Incorrect value of level. Changing the difficulty level to Easy";
    }
}
