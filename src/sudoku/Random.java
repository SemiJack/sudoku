package sudoku;

/**
 * Class which implements methods for random generating values.
 */
public class Random {

    /**
     * Randomly generates int value in specified range.
     *
     * @param min Minimal value of range.
     * @param max Maximal value of range.
     * @return Randomly generated int value.
     */
    public static int generateRandomInt(int min, int max) {
        int randomValue;
        int range = (max - min) + 1;
        randomValue = (int) (Math.random() * range) + min;
        return randomValue;
    }

}
