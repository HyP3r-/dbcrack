package server;

import prober.IProbe;

/**
 * Created by fendta on 28.07.14.
 */
public class ComServer {

    private int port;

    private Class<? extends IProbe> classOfProber;

    private byte[] hash;

    public ComServer(String port, String prober, String hash) {
        try {
            this.port = Integer.parseInt(port);
            this.classOfProber = (Class<? extends IProbe>) Class.forName(prober);
            this.hash = hexStringToByteArray(hash);
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
