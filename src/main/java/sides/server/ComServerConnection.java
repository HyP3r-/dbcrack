package sides.server;

import sides.communication.ComPackage;

import java.io.*;
import java.net.Socket;

/**
 * Created by fendta on 29.07.14.
 */
public class ComServerConnection implements Runnable {

    private Socket socket;

    private ComServer comServer;

    public ComServerConnection(Socket socket, ComServer comServer) {
        this.socket = socket;
        this.comServer = comServer;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ComPackage comPackage;

            while (true) {
                // Wait until Data is aviable
                if (inputStream.available() == 0) {
                    Thread.sleep(10);
                    continue;
                }

                // Get ComPackage
                comPackage = (ComPackage) objectInputStream.readObject();

                // Switch Mode
                switch (comPackage.getComMode()) {
                    case GET_TASK:

                        break;
                    case GET_CLASS_OF_PROBER:

                        break;
                    case GET_CLASS_OF_TESTER:

                        break;
                }


            }
        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
