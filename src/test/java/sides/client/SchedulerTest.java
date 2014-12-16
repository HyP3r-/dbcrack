package sides.client;

import attack.prober.ProbeMD5;
import attack.task.Task;
import attack.tester.TesterBruteForce;

import java.security.MessageDigest;

public class SchedulerTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void testStart() throws Exception {
        // 81dc9bdb52d04dc20036dbd8313ed055 == 1234
        String password = "1234";
        String charString = "0123456789";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
        byte[] chars = charString.getBytes();

        Task task = new Task(0, 10000, chars, hash);

        // Start the test
        Scheduler scheduler = new Scheduler(TesterBruteForce.class,ProbeMD5.class,);
        scheduler.addTask(task);
        TaskResult start = scheduler.start();
        Assert.assertEquals(start.getPassword(), password);*/
    }
}