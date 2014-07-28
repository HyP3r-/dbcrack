package client;

import prober.IProbe;
import java.util.Queue;

/**
 * Created by fendta on 08.07.14.
 */
public class SchedulerTask implements Runnable {

    private Class<IProbe> classOfProber;

    private Queue<Task> taskQueue;

    public SchedulerTask(Class<IProbe> classOfProber, Queue<Task> taskQueue) {
        this.classOfProber = classOfProber;
        this.taskQueue = taskQueue;
    }

    @Override
    public void run() {
        try {
            Task task;
            IProbe iProbe = classOfProber.newInstance();
            Tester tester = new Tester(iProbe);
            while (true) {
                while ((task = taskQueue.peek()) != null) {
                    Thread.sleep(100);
                }
                if (tester.startTest(task)) {
                    break;
                }
            }
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
