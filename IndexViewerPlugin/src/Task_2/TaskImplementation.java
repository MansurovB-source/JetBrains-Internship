package Task_2;

import java.util.Collection;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class TaskImplementation implements Task {
    private int id;
    private Collection<Task> dependencies;

    public TaskImplementation() {
    }

    public TaskImplementation(int id) {
        this.id = id;
    }

    public TaskImplementation(Collection<Task> dependencies) {
        this.dependencies = dependencies;
    }

    public TaskImplementation(int id, Collection<Task> dependencies) {
        this.id = id;
        this.dependencies = dependencies;
    }


    @Override
    public void execute() {
        System.out.println(" Task id: " + this.id);
        // TODO
    }

    @Override
    public Collection<Task> dependencies() {
        return this.dependencies;
    }

    public void setDependencies(List<Task> tasks) {
        this.dependencies = dependencies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
