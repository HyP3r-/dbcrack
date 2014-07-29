package attack.task;

/**
 * Created by fendta on 29.07.14.
 */
public class TaskBlock {

    private Task task;

    private TaskResult taskResult;

    private String hostname;

    public TaskBlock(Task task, TaskResult taskResult, String hostname) {
        this.task = task;
        this.taskResult = taskResult;
        this.hostname = hostname;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public TaskResult getTaskResult() {
        return taskResult;
    }

    public void setTaskResult(TaskResult taskResult) {
        this.taskResult = taskResult;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

}
