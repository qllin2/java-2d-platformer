import java.util.Random;

/**
 * tool class of generate random
 */
public class RandomUtils {
    private static final Random RANDOM = new Random();

    /**
     * generate a random true or false
     *
     * @return boolean random true or false
     */
    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }
}
