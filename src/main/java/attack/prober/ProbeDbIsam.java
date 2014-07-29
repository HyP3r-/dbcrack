package attack.prober;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by fendta on 08.07.14.
 */
public class ProbeDbIsam implements IProbe {

    private MessageDigest messageDigest;

    private KeyGenerator blowfish;

    private Cipher cipher;

    public ProbeDbIsam() throws Exception {
        messageDigest = MessageDigest.getInstance("MD5");
        blowfish = KeyGenerator.getInstance("Blowfish");
        cipher = Cipher.getInstance("Blowfish");
    }

    @Override
    public boolean probe(String password, byte[] hashToFind) throws Exception {
        // First we have to create an hash of the password
        byte[] data = messageDigest.digest(password.getBytes("UTF-8"));

        // Then we have to encrypt the hash with the password
        SecretKey secretkey = blowfish.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);
        data = cipher.doFinal(data);

        // After that, hashToFind and created hash should be the same
        return Arrays.equals(data, hashToFind);
    }

}
