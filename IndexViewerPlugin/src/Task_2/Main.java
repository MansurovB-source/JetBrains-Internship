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
        TaskImplementation_1 task_1 = new TaskImplementation_1(1);
        TaskImplementation_1 task_2 = new TaskImplementation_1(2);
        TaskImplementation_1 task_3 = new TaskImplementation_1(3);
        TaskImplementation_1 task_4 = new TaskImplementation_1(4);
        TaskImplementation_1 task_5 = new TaskImplementation_1(5);
        TaskImplementation_1 task_6 = new TaskImplementation_1(6);
        TaskImplementation_1 task_7 = new TaskImplementation_1(7);
        TaskImplementation_1 task_8 = new TaskImplementation_1(8);

        task_1.setTasks(Arrays.asList(task_2, task_3));
        task_2.setTasks(Arrays.asList(task_4));
        task_3.setTasks(Arrays.asList(task_2));
        task_4.setTasks(Arrays.asList(task_5));
        task_5.setTasks(Arrays.asList(task_6));
        task_6.setTasks(null);
        task_7.setTasks(Arrays.asList(task_6, task_8));
        task_8.setTasks(Arrays.asList(task_3));

        List<Task> tasks = new ArrayList<>();
        tasks.add(task_1);
        tasks.add(task_7);
        TaskExecutor taskExecutor = new TaskExecutor();
        taskExecutor.execute(tasks);
    }
}
