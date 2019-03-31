import java.io.Serializable;

public class PrimaryChequingAcc extends ChequingAcc implements Serializable {
    public PrimaryChequingAcc(String name, IAccountHolder o) {
        super(name, o);
        this.primary = true;
        this.type = "PRIMARY CHEQUING";
    }
}
