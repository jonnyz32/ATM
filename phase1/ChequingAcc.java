// A class for Chequing accounts

public class ChequingAcc {
    private int balance;
    private boolean negative;

    public ChequingAcc() {
        balance = 0;
        negative = false;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setNegative(boolean negative) {
        this.negative = negative;
    }

    public int getBalance() {
        return this.balance;
    }

    public boolean getNegative() {
        return this.negative;
    }

    public void transfer_in(int amount) {
        this.balance += amount;
    }

    public void transfer_out(int amount) {
        this.balance -= amount;
    }
}