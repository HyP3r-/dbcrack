package sides.communication;

/**
 * Created by fendta on 29.07.14.
 */
public class ComPackage {

    private ComMode comMode;

    private Object subObject;

    public ComPackage(ComMode comMode) {
        this.comMode = comMode;
    }

    public ComPackage(ComMode comMode, Object subObject) {
        this.subObject = subObject;
        this.comMode = comMode;
    }

    public ComMode getComMode() {
        return comMode;
    }

    public void setComMode(ComMode comMode) {
        this.comMode = comMode;
    }

    public Object getSubObject() {
        return subObject;
    }

    public void setSubObject(Object subObject) {
        this.subObject = subObject;
    }

}
