// A class for the credit line

public class CreditLineAcc {
    private int balance;
    private boolean owes;

    public CreditLineAcc() {
        balance = 0;
        owes = false;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setOwes(boolean owes) {
        this.owes = owes;
    }

    public int getBalance() {
        return this.balance;
    }

    public boolean getOwes() {
        return this.owes;
    }

    public void transfer_in(int amount) {
        this.balance += amount;
    }

    public void transfer_out(int amount) {
        this.balance -= amount;
    }
}