package client;

import prober.IProbe;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by fendta on 07.07.14.
 */
public class Scheduler {

    private final int numOfTasks = 20;

    private Future<TaskResult>[] futures;

    private Queue<Task> taskQueue;

    private Class<? extends IProbe> classOfProber;

    public Scheduler(Class<? extends IProbe> classOfProber) {
        this.classOfProber = classOfProber;
        this.taskQueue = new ArrayDeque<>();
        this.futures = new Future[numOfTasks];
    }

    public void addTask(Task task) {
        taskQueue.add(task);
    }

    public TaskResult start() {
        ExecutorService executorService = Executors.newWorkStealingPool();
        try {
            IProbe iProbe = classOfProber.newInstance();
            // TODO: this part should be optimized
            while (true) {
                Task task = taskQueue.poll();
                if (task == null) {
                    for (int i = 0; i < futures.length; i++) {
                        if (futures[i] != null && futures[i].isDone() && futures[i].get().getResult()) {
                            // TODO: we found the hash lol
                            executorService.shutdown();
                            return futures[i].get();
                        }
                    }
                    Thread.sleep(10);
                    continue;
                }
                for (int i = 0; i < futures.length; i++) {
                    if (futures[i] == null || (futures[i].isDone() && !futures[i].get().getResult())) {
                        futures[i] = executorService.submit(new SchedulerTask(iProbe, task));
                        break;
                    } else if (futures[i].isDone() && futures[i].get().getResult()) {
                        // TODO: we found the hash lol
                        executorService.shutdown();
                        return futures[i].get();
                    }
                }
            }
        } catch (InterruptedException | ExecutionException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // TODO return null is shit
        return null;
    }
}
