package Task_2;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public interface Task {

    void execute();

    Collection<Task> dependencies();
}
