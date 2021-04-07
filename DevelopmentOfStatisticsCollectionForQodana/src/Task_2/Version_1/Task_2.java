package Task_2.Version_1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Task_2 {

    public static void main(String[] args) {
        Task_2 task_2 = new Task_2();
        try {
            task_2.matches("00000000000000000000000000000000", "((0*)*)*1");
        } catch (InterruptedException | MatchException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean matches(String text, String regex) throws InterruptedException, MatchException {
        AtomicBoolean result = new AtomicBoolean(false);
        Runnable r = () -> result.set(Pattern.compile(regex).matcher(new InterruptableCharSequence(text)).matches());
        Thread t = new Thread(r);
        t.start();
        Thread.sleep(1000);
        t.interrupt();
        return result.get();
    }
}
