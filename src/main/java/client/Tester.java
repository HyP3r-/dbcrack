package client;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by fendta on 07.07.14.
 * All exceptions where here happens were given out
 */
public class Tester {

    private MessageDigest messageDigest;

    private KeyGenerator blowfish;

    private Cipher cipher;

    public Tester() throws NoSuchAlgorithmException, NoSuchPaddingException {
        messageDigest = MessageDigest.getInstance("MD5");
        blowfish = KeyGenerator.getInstance("Blowfish");
        cipher = Cipher.getInstance("Blowfish");
    }

    public boolean run(Task task) throws UnsupportedEncodingException, BadPaddingException,
            InvalidKeyException, IllegalBlockSizeException {
        long currentPasswordNumber;
        StringBuilder stringBuilder;
        for (long i = task.getStart(); i < task.getEnd(); i++) {
            // first generate the password
            currentPasswordNumber = i;
            stringBuilder = new StringBuilder();
            for (long l = 0; l < currentPasswordNumber; l++) {
                stringBuilder.append(task.getChars()[((int) (currentPasswordNumber % task.getChars().length))]);
            }
            currentPasswordNumber = currentPasswordNumber / task.getChars().length;
            // then test the password
            if (probe(stringBuilder.toString(), task.getHashToFind())) {
                return true;
            }
        }
        return false;
    }

    /*
     * Maybe we externalize sometime this prober so we can attack not only dbisam
     */
    private boolean probe(String password, byte[] hashToFind) throws UnsupportedEncodingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
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
