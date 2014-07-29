package sides.client;

import attack.prober.IProbe;
import attack.task.Task;
import attack.task.TaskResult;
import attack.tester.ITester;

import java.util.concurrent.Callable;

/**
 * Created by fendta on 08.07.14.
 */
public class SchedulerTask implements Callable<TaskResult> {

    private final ITester iTester;

    private final IProbe iProbe;

    private final Task task;

    public SchedulerTask(ITester iTester, IProbe iProbe, Task task) {
        this.iProbe = iProbe;
        this.task = task;
        this.iTester = iTester;
    }

    @Override
    public TaskResult call() throws Exception {
        return iTester.startTest(task, iProbe);
    }
}
