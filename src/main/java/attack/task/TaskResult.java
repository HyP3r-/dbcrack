package attack.task;

/**
 * Created by fendta on 28.07.14.
 */
public class TaskResult {

    private final boolean result;

    private String password;

    public TaskResult(boolean result, String password) {
        this.result = result;
        this.password = password;
    }

    public TaskResult(boolean result){
        this.result = result;
    }

    public boolean getResult() {
        return result;
    }

    public String getPassword() {
        return password;
    }
}
