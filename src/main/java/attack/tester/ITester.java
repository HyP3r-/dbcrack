package attack.tester;

import attack.task.Task;
import attack.task.TaskResult;
import attack.prober.IProbe;

/**
 * Created by fendta on 29.07.14.
 */
public interface ITester {

    public TaskResult startTest(Task task, IProbe iProbe) throws Exception;

}
