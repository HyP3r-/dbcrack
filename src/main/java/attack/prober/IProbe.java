package attack.prober;

/**
 * Created by fendta on 08.07.14.
 */
public interface IProbe {

    boolean probe(byte[] password, byte[] hashToFind) throws Exception;

}
