package sides.server;

import attack.prober.IProbe;
import attack.task.Task;
import attack.task.TaskBlock;
import attack.task.TaskResult;
import attack.tester.ITester;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by fendta on 28.07.14.
 */
public class ComServer {

    private Class<? extends IProbe> classOfProber;

    private Class<? extends ITester> classOfTester;

    private Task mainTask;

    private List<TaskBlock> taskBlocks;

    private int port;

    private final long blockSize = 3000000000L;

    public ComServer(String port, String prober, String tester, String hash, String chars, String range) {
        try {
            this.port = Integer.parseInt(port);
            String[] split = range.split("-");
            mainTask = new Task(Long.parseLong(split[0]), Long.parseLong(split[1]), chars.toCharArray(),
                    hexStringToByteArray(hash));
            this.classOfProber = (Class<? extends IProbe>) Class.forName(prober);
            this.classOfTester = (Class<? extends ITester>) Class.forName(tester);
            this.taskBlocks = new CopyOnWriteArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        // Start here Server and distribute the Tasks
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ComServerConnection(socket, this));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public Class<? extends IProbe> getClassOfProber() {
        return classOfProber;
    }

    public Class<? extends ITester> getClassOfTester() {
        return classOfTester;
    }

    public Task getTask() {
        throw new NotImplementedException();
    }

    public void setTaskResult(TaskResult taskResult) {
        throw new NotImplementedException();
    }

}
