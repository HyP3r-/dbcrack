package attack.task;

/**
 * Created by fendta on 28.07.14.
 */
public class TaskResult {

    private final boolean result;

    private byte[] password;

    public TaskResult(boolean result, byte[] password) {
        this.result = result;
        this.password = password;
    }

    public TaskResult(boolean result){
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public byte[] getPassword() {
        return password;
    }
}
