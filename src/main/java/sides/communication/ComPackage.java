package sides.communication;

/**
 * Created by fendta on 29.07.14.
 */
public class ComPackage {

    private ComMode comMode;

    public ComPackage(ComMode comMode) {
        this.comMode = comMode;
    }

    public ComMode getComMode() {
        return comMode;
    }

    public void setComMode(ComMode comMode) {
        this.comMode = comMode;
    }

}
