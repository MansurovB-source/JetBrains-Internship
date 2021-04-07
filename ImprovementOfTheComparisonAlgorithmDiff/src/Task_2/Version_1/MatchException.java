package Task_2.Version_1;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class MatchException extends RuntimeException {

    public MatchException() {
        super("The matching process takes a very long time to complete.");
    }

    public MatchException(String message) {
        super(message);
    }
}
