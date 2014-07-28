package cli;

import client.ComClient;
import server.ComServer;

/**
 * Created by fendta on 28.07.14.
 */
public class CliController {

    public void start(String[] args) {
        // TODO: Client und Server mit einem Interface versehen und args vorort auslesen!
        // Server: -s <Port> <Prober> <Hash>
        if (args.length == 4 && args[0].equals("-s")) {
            ComServer comServer = new ComServer(args[1], args[2], args[3]);
            comServer.start();
        }
        // Client: -c <Server-IP>
        if (args.length == 2 && args[0].equals("-c")) {
            ComClient comClient = new ComClient(args[1]);
            comClient.start();
        }
    }


}
