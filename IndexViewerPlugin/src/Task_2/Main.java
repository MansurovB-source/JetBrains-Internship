package Task_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TaskImplementation task_1 = new TaskImplementation(1);
        TaskImplementation task_2 = new TaskImplementation(2);
        TaskImplementation task_3 = new TaskImplementation(3);
        TaskImplementation task_4 = new TaskImplementation(4);
        TaskImplementation task_5 = new TaskImplementation(5);
        TaskImplementation task_6 = new TaskImplementation(6);
        TaskImplementation task_7 = new TaskImplementation(7);
        TaskImplementation task_8 = new TaskImplementation(8);

        task_1.setDependencies(Arrays.asList(task_2, task_3));
        task_2.setDependencies(Arrays.asList(task_4));
        task_3.setDependencies(Arrays.asList(task_2));
        task_4.setDependencies(Arrays.asList(task_5));
        task_5.setDependencies(Arrays.asList(task_6));
        task_6.setDependencies(null);
        task_7.setDependencies(Arrays.asList(task_6, task_8));
        task_8.setDependencies(Arrays.asList(task_3));

        List<Task> tasks = new ArrayList<>();
        tasks.add(task_1);
        tasks.add(task_7);

        TaskExecutor taskExecutor = new TaskExecutor();
        taskExecutor.execute(tasks);
    }
}
