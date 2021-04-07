package Task_2;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class TaskImplementation_1 implements Task {
    private int id;
    private List<Task> tasks;

    public TaskImplementation_1() {
    }

    public TaskImplementation_1(int id) {
        this.id = id;
    }

    public TaskImplementation_1(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskImplementation_1(int id, List<Task> tasks) {
        this.id = id;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        System.out.println("Thread id: " + Thread.currentThread().getId() + " Task id: " + this.id);
        // TODO
    }

    @Override
    public Collection<Task> dependencies() {
        return this.tasks;
    }


    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
