package sides.client;

import attack.prober.IProbe;
import attack.tester.TesterBruteForce;

import java.util.concurrent.Callable;

/**
 * Created by fendta on 08.07.14.
 */
public class SchedulerTask implements Callable<TaskResult> {

    private IProbe iProbe;

    private Task task;

    public SchedulerTask(IProbe iProbe, Task task) {
        this.iProbe = iProbe;
        this.task = task;
    }

    @Override
    public TaskResult call() throws Exception {
        TesterBruteForce testerBruteForce = new TesterBruteForce(iProbe);
        return testerBruteForce.startTest(task);
    }
}
