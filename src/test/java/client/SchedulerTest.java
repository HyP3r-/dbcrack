package client;

import org.junit.Assert;
import prober.ProbeMD5;

import java.security.MessageDigest;

public class SchedulerTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }

    @org.junit.Test
    public void testStart() throws Exception {
        // 81dc9bdb52d04dc20036dbd8313ed055 == 1234
        String password = "1234";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        Task task = new Task(0, 10000, chars, hash);

        // Start the test
        Scheduler scheduler = new Scheduler(ProbeMD5.class);
        scheduler.addTask(task);
        TaskResult start = scheduler.start();
        Assert.assertEquals(start.getPassword(), password);
    }
}