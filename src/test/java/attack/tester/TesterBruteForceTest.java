package attack.tester;

import attack.prober.IProbe;
import attack.prober.ProbeMD5;
import attack.task.Task;
import attack.task.TaskResult;
import org.junit.Test;

import java.security.MessageDigest;

public class TesterBruteForceTest {

    @Test
    public void testStartTest() throws Exception {
        String password = "1234";
        String charString = "0123456789";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] hash = messageDigest.digest(password.getBytes("UTF-8"));
        byte[] chars = charString.getBytes();

        Task task = new Task(0,10000,chars,hash);
        IProbe iProbe = new ProbeMD5();

        TesterBruteForce testerBruteForce = new TesterBruteForce();
        TaskResult taskResult = testerBruteForce.startTest(task, iProbe);


    }

}