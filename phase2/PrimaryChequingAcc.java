import java.io.Serializable;

public class PrimaryChequingAcc extends ChequingAcc implements Serializable {
    public PrimaryChequingAcc(String name, IAccountHolder o) {
        super(name,o);
        type = "PRIMARYCHEQUING";
        primary=true;
    }
}
