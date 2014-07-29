package sides.server;

import sides.client.Task;
import attack.prober.IProbe;

/**
 * Created by fendta on 28.07.14.
 */
public class ComServer {

    private Class<? extends IProbe> classOfProber;

    private Task mainTask;

    private int port;

    public ComServer(String port, String prober, String hash, String chars, String range) {
        try {
            this.port = Integer.parseInt(port);
            String[] split = range.split("-");
            mainTask = new Task(Long.parseLong(split[0]), Long.parseLong(split[1]), chars.toCharArray(),
                    hexStringToByteArray(hash));
            this.classOfProber = (Class<? extends IProbe>) Class.forName(prober);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        // Start here Server and distribute the Tasks

    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

}
