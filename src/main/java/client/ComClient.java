package client;

import java.util.Queue;

/**
 * Created by fendta on 28.07.14.
 */
public class ComClient {

    private String hostname;

    private int port;

    public ComClient(String hostname, String port) {
        try {
            this.hostname = hostname;
            this.port = Integer.parseInt(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        // 1. init connection to server
        // 2. start scheduler (the scheduler gets task by itself)
    }

    public void fillTaskList(Queue<Task> taskQueue, int numberOfTasks) {
        // get new items form the server
    }

    public void tellServer(TaskResult taskResult) {
        // tell the server what we did (found or not)
    }

}
