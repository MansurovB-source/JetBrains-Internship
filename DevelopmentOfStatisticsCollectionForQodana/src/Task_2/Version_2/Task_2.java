package Task_2.Version_2;

import java.util.concurrent.*;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Task_2 {

    public static void main(String[] args) {
        Task_2 task_2 = new Task_2();
        task_2.matches("00000000000000000000000000000000", "((0*)*)*1");
    }

    public boolean matches(String text, String regex) {
        boolean answer = false;
        Callable<Boolean> task = () -> {
            Thread.currentThread().interrupt();
            return Pattern.compile(regex).matcher(text).matches();
        };
        FutureTask<Boolean> futureTask = new FutureTask<>(task);
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            answer = futureTask.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            thread.stop();
            System.out.println("The matching process takes a very long time to complete.");
        }
        return answer;
    }
}
