import java.io.Serializable;

public class SavingAcc extends GenericAccount implements Serializable {

    public SavingAcc(String name, IAccountHolder o) {
        super(name, o);
        asset = true;
        type = "SAVINGS";
        withdrawable = new WithdrawCapped(0,2000000000);
    }

    void increase_interest() {
        balance += balance * 0.001;
    }

    @Override
    String getSummary() {
        return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
                + "Asset: " + asset + "\n" + "Balance: " + balance + "\n" + "Interest: 0.001" + "\n" +
                "Last Transaction: " + getLatestTransaction();
    }
}