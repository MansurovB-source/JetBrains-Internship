package Task_2;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class TaskExecutor {
    public ExecutorService service;
    private Set<Integer> used = new HashSet<>();
    private List<Task> tasks = new ArrayList<>();

    public TaskExecutor() {
        service = Executors.newCachedThreadPool();
    }

    public TaskExecutor(ExecutorService service) {
        this.service = service;
    }

    void execute(Collection<Task> tasks) throws InterruptedException, ExecutionException {
        List<Callable<Void>> callables = new ArrayList<>();
        List<Future<Void>> results = new ArrayList<>();
        for (Task task : tasks) {
            Callable<Void> c = () -> {
                if (task.dependencies() != null) {
                    execute(task.dependencies());
                }
                task.execute();
                return null;
            };

            callables.add(c);
        }

        for (Callable<Void> callable : callables) {
            synchronized (this) {
                results.add(service.submit(callable));
            }
        }

        for (Future<Void> result : results) {
            result.get();
        }
    }

//    public void dfs(Task task) {
//        used.add(task.hashCode());
//        Collection<Task> innerTasks = task.dependencies();
//        if (task.dependencies() != null || !innerTasks.isEmpty()) {
//            for (Task t : innerTasks) {
//                if (!used.contains(t.hashCode())) {
//                    dfs(t);
//                }
//            }
//        }
//
//        tasks.add(task);
//    }
//
//    public void topological_sort(List<Task> tasks) {
//        for (Task task : tasks) {
//            if (!used.contains(task.hashCode())) {
//                dfs(task);
//            }
//        }
//    }

    public static void main(String[] args) {
        TaskExecutor taskExecutor = new TaskExecutor();
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            List<Task> innerTasks = new ArrayList<>();
            for (int j = 0; j < 10; j++) {
                innerTasks.add(new TaskImplementation_1(i * 10 + j));
            }
            tasks.add(new TaskImplementation_1(i * 100000, innerTasks));
        }
        try {
            taskExecutor.execute(tasks);
            taskExecutor.service.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
    }
}
