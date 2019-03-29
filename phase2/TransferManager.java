import java.io.Serializable;

public class TransferManager implements Serializable {
    GenericAccount originAcc;
    double amount;
    GenericAccount destAcc;

    public TransferManager(GenericAccount o, double a, GenericAccount d) {
        originAcc = o;
        amount = a;
        destAcc = d;
    }

    // Generic method that takes money out of origin account and deposits to destination account.
    // Assume amount is positive
    void make_transfer() {
        if (originAcc.type.equalsIgnoreCase("CREDIT CARD")) {
            System.out.println("Cannot transfer from a Credit Account");
        }
        if (originAcc.type.equalsIgnoreCase("CREDIT LINE")) {
            originAcc.balance += amount;
            creditLinePoints(originAcc, amount);
        }
        else {
            originAcc.balance -= amount;
        }
        if (destAcc.asset) {
            destAcc.balance += amount;
        }
        // Else it is a debt account
        else {
            destAcc.balance -= amount;
        }

        originAcc.lastTransText = "Transferred $" + Math.abs(amount) + " From:" + originAcc.name + " to " + destAcc.owner.getUsername() + ": " + destAcc.name;
        String s = originAcc.lastTransText;
        originAcc.past_trans.add(s);
        originAcc.lastTransReverter = (Runnable & Serializable) this::revert_transfer;
        originAcc.past_reverters.add(originAcc.lastTransReverter);

    }

     void revert_transfer() {
        if (originAcc.type.equalsIgnoreCase("CREDIT CARD")) {
            System.out.println("Cannot transfer from a Credit Account");
        }
        if (originAcc.type.equalsIgnoreCase("CREDIT LINE")) {
            originAcc.balance -= amount;
        }
        else {
            originAcc.balance += amount;
        }
        if (destAcc.isAsset()) {
            destAcc.balance -= amount;
        }
        // Else it is a debt account
        else {
            destAcc.balance += amount;
        }
        originAcc.past_trans.remove(originAcc.past_trans.size() - 1);
    }

    private void creditLinePoints(GenericAccount acc, double amount) {
        ((CreditLineAcc) acc).points += amount * 0.01;
    }
}
