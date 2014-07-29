package attack.tester;

import sides.client.Task;
import sides.client.TaskResult;
import attack.prober.IProbe;

/**
 * Created by fendta on 29.07.14.
 */
public interface ITester {

    public TaskResult startTest(Task task, IProbe iProbe) throws Exception;

}
