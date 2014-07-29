package sides.client;

import attack.prober.IProbe;
import attack.task.Task;
import attack.task.TaskResult;
import attack.tester.ITester;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by fendta on 07.07.14.
 */
public class Scheduler {

    private final int tasksQueue;

    private final int tasksWorking;

    private final int tasksThreshold;

    private Queue<Task> taskQueue;

    private Class<? extends IProbe> classOfProber;

    private Class<? extends ITester> classOfTester;

    private ComClient comClient;

    public Scheduler(Class<? extends ITester> classOfTester, Class<? extends IProbe> classOfProber, ComClient comClient) {
        this.classOfTester = classOfTester;
        this.classOfProber = classOfProber;
        this.taskQueue = new ArrayDeque<>();
        this.comClient = comClient;
        // Limits, could be optimized
        this.tasksQueue = Runtime.getRuntime().availableProcessors() * 4;
        this.tasksWorking = Runtime.getRuntime().availableProcessors() * 2;
        this.tasksThreshold = Runtime.getRuntime().availableProcessors();
    }

    public TaskResult start() {
        try {
            // Init Executor and Service
            ExecutorService executorService = Executors.newWorkStealingPool();
            ExecutorCompletionService<TaskResult> executorCompletionService = new ExecutorCompletionService<TaskResult>(executorService);
            IProbe iProbe = classOfProber.newInstance();
            ITester iTester = classOfTester.newInstance();

            // Prefill Queue and Executor
            comClient.fillTaskList(taskQueue, tasksQueue);
            for (int i = 0; i < tasksWorking; i++) {
                executorCompletionService.submit(new SchedulerTask(iTester, iProbe, taskQueue.poll()));
            }

            // Keep on working and refill
            while (true) {
                Future<TaskResult> take = executorCompletionService.take();
                comClient.tellServer(take.get());
                if (take.get().getResult()) {
                    return take.get();
                } else {
                    executorCompletionService.submit(new SchedulerTask(iTester, iProbe, taskQueue.poll()));
                }
                if (taskQueue.size() == tasksThreshold) {
                    comClient.fillTaskList(taskQueue, tasksQueue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // TODO return null is shit
        return null;
    }
}
