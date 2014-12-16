package attack.prober;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by fendta on 28.07.14.
 */
public class ProbeMD5 implements IProbe {

    private MessageDigest messageDigest;

    public ProbeMD5() throws Exception {
        messageDigest = MessageDigest.getInstance("MD5");
    }

    @Override
    public boolean probe(byte[] password, byte[] hashToFind) throws Exception {
        // First we have to create an hash of the password
        byte[] data = messageDigest.digest(password);

        // After that, hashToFind and created hash should be the same
        return Arrays.equals(data, hashToFind);
    }

}
