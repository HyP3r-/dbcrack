package client;

import prober.IProbe;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fendta on 07.07.14.
 */
public class Scheduler {

    private Queue<Task> taskQueue;

    private Class<IProbe> classOfProber;

    public Scheduler(Class<IProbe> classOfProber) {
        this.classOfProber = classOfProber;
        this.taskQueue = new ArrayDeque<>();
    }

    public void start() {
        ExecutorService executorService = Executors.newWorkStealingPool();
        SchedulerTask schedulerTask = new SchedulerTask(classOfProber, taskQueue);
        executorService.execute(schedulerTask);
    }

    public void addTask(Task task) {
        taskQueue.add(task);
    }

}
