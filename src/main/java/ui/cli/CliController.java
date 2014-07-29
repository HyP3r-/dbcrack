package ui.cli;

import sides.client.ComClient;
import sides.server.ComServer;

/**
 * Created by fendta on 28.07.14.
 */
public class CliController {

    public void start(String[] args) {
        // TODO: Client und Server mit einem Interface versehen und args vorort auslesen!
        if (args.length == 4 && args[0].equals("-s")) {
            // Server: -s <Port> <Prober> <Hash> <Chars> <Range>
            // (String port, String chars, String range, String prober, String hash)
            ComServer comServer = new ComServer(args[1], args[2], args[3], args[4], args[5]);
            comServer.start();
        } else if (args.length == 2 && args[0].equals("-c")) {
            // Client: -c <Hostname> <Server-IP>
            ComClient comClient = new ComClient(args[1], args[2]);
            comClient.start();
        }
    }


}
