public class TransferManager {
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
        if (originAcc.type.equalsIgnoreCase("CREDIT")) {
            System.out.println("Cannot transfer from a Credit Account");
        }
        if (originAcc.type.equalsIgnoreCase("CREDITLINE")) {
            originAcc.balance += amount;
        }
        else {
            originAcc.balance -= amount;
        }
        if (destAcc.asset) {
            destAcc.balance -= amount;
        }
        // Else it is a chequing or savings
        else {
            destAcc.balance += amount;
        }

        originAcc.lastTransText = "Transferred $" + Math.abs(amount) + " From:" + originAcc.name + " to " + destAcc.owner.getUsername() + ": " + destAcc.name;
        String s = originAcc.lastTransText;
        originAcc.past_trans.add(s);
        originAcc.lastTransReverter = this::revert_transfer;

    }

     void revert_transfer() {
        if (originAcc.type.equalsIgnoreCase("CREDIT")) {
            System.out.println("Cannot transfer from a Credit Account");
        }
        if (originAcc.type.equalsIgnoreCase("CREDITLINE")) {
            originAcc.balance -= amount;
        }
        else {
            originAcc.balance += amount;
        }
        if (destAcc.type.equalsIgnoreCase("CREDIT") || destAcc.type.equalsIgnoreCase("CREDITLINE")) {
            destAcc.balance += amount;
        }
        // Else it is a chequing or savings
        else {
            destAcc.balance -= amount;
        }
        originAcc.past_trans.remove(originAcc.past_trans.size() - 1);
        originAcc.lastTransText = originAcc.getLatestTransaction();
    }
}
