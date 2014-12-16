package attack.tester;

import attack.prober.IProbe;
import attack.task.Task;
import attack.task.TaskResult;

/**
 * Created by fendta on 07.07.14.
 * All exceptions where here happens were given out
 */
public class TesterBruteForce implements ITester {

    @Override
    public TaskResult startTest(Task task, IProbe iProbe) throws Exception {
        // TODO: this have to optimized lol
        long currentNumber;
        long nextStep = 0;
        int pointer;
        int currentLength;
        int charLength = task.getChars().length;
        byte[] password = null;
        byte[] chars = task.getChars();

        for (long i = task.getStart(); i < task.getEnd(); i++) {
            // generate currentLength, nextStep and char Array
            if (nextStep == 0 || nextStep == i) {
                currentLength = (int) ((Math.log(i) / Math.log(charLength)) + 1);
                nextStep = (long) (Math.pow(charLength, currentLength) + 1);
                password = new byte[currentLength];
            }

            // first generate the password
            currentNumber = i;
            pointer = 0;
            while (currentNumber != 0) {
                password[pointer++] = chars[(int) (currentNumber % charLength)];
                currentNumber = currentNumber / charLength;
            }

            // then test the password
            if (iProbe.probe(password, task.getHashToFind())) {
                return new TaskResult(true, password);
            }
        }
        return new TaskResult(false);
    }


}
