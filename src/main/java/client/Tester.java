package client;

import prober.IProbe;

/**
 * Created by fendta on 07.07.14.
 * All exceptions where here happens were given out
 */
public class Tester {

    private IProbe prober;

    public Tester(IProbe prober) {
        this.prober = prober;
    }

    public boolean startTest(Task task) throws Exception {
        // TODO: this have to optimized lol
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
            if (prober.probe(stringBuilder.toString(), task.getHashToFind())) {
                return true;
            }
        }
        return false;
    }

}
