package Task_2;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */

/**
 * The problem was solved with the precondition that there are no cycles
 * To solve this problem, we used the Kahn's algorithm for topological sorting
 */
public class TaskExecutor {
    private final ExecutorService service;
    private final Map<Task, HashSet<Task>> graph = new HashMap<>();
    private final Map<Task, Integer> inDegree = new HashMap<>();
    private final Map<Integer, HashSet<Task>> taskWithZeroInDegree = new HashMap<>();

    public TaskExecutor() {
        service = Executors.newCachedThreadPool();
    }

    public TaskExecutor(ExecutorService service) {
        this.service = service;
    }

    /**
     * The main executor which parallelize tasks
     *
     * @param tasks - list of tasks to be completed
     * @throws ExecutionException   - if the computation threw an exception
     * @throws InterruptedException - if the current thread was interrupted while waiting
     */
    public void execute(Collection<Task> tasks) throws ExecutionException, InterruptedException {
        List<Callable<Void>> callables = new ArrayList<>();
        List<Future<Void>> futures = new ArrayList<>();

        graphMaker(tasks);
        inDegreeMaker();
        levelAssigner();

        for (int i = graph.size() - 1; i >= 0; i--) {
            if (!taskWithZeroInDegree.get(i).isEmpty()) {
                for (Task task : taskWithZeroInDegree.get(i)) {
                    Callable<Void> c = () -> {
                        task.execute();
                        return null;
                    };
                    callables.add(c);
                }

                for (Callable<Void> callable : callables) {
                    futures.add(service.submit(callable));
                }
                for (Future<Void> future : futures) {
                    future.get();
                }

                callables.clear();
                futures.clear();
            }
        }
    }

    /**
     * Divides vertexes into dependent levels. Elements of the same level can be parallelized.
     */
    public void levelAssigner() {
        int counter = 0;
        for (Task ignored : inDegree.keySet()) {
            HashSet<Task> WithZeroInDegree = new HashSet<>();
            for (Map.Entry<Task, Integer> entry : inDegree.entrySet()) {
                if (entry.getValue() == 0) {
                    WithZeroInDegree.add(entry.getKey());
                    inDegree.put(entry.getKey(), -1);
                }
            }
            taskWithZeroInDegree.put(counter, WithZeroInDegree);

            for (Task task : taskWithZeroInDegree.get(counter)) {
                HashSet<Task> innerTasks = graph.get(task);
                for (Task innerTask : innerTasks) {
                    inDegree.put(innerTask, inDegree.get(innerTask) - 1);
                }
            }
            counter++;
        }
    }

    /**
     * From the collection of tasks, we will make an adjacency sheet
     *
     * @param tasks - the collection from which the adjacency list will be constructed
     */
    public void graphMaker(Collection<Task> tasks) {
        for (Task task : tasks) {
            if (!graph.containsKey(task)) {
                HashSet<Task> set = new HashSet<>();
                graph.put(task, set);
            }

            if (task.dependencies() != null) {
                if (!task.dependencies().isEmpty()) {
                    for (Task innerTask : task.dependencies()) {
                        graph.get(task).add(innerTask);
                    }
                    graphMaker(task.dependencies());
                }
            }
        }
    }

    /**
     * Calculates the degree of each vertex
     */
    public void inDegreeMaker() {
        for (Map.Entry<Task, HashSet<Task>> entry : graph.entrySet()) {
            if (!inDegree.containsKey(entry.getKey())) {
                inDegree.put(entry.getKey(), 0);
            }
            HashSet<Task> set = entry.getValue();
            for (Task task : set) {
                if (!inDegree.containsKey(task)) {
                    inDegree.put(task, 1);
                } else {
                    inDegree.put(task, inDegree.get(task) + 1);
                }
            }
        }
    }
}