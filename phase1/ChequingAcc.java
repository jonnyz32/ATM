// A class for Chequing accounts

public class ChequingAcc implements AccountInterface {
    private int balance;
    private boolean owes;

    public ChequingAcc() {
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