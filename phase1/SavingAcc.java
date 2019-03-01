// A class for savings accounts

public class SavingAcc implements AccountInterface {
    private int balance;

    public SavingAcc() {
        balance = 0;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setOwes(boolean owes) {}

    public int getBalance() {
        return this.balance;
    }

    public boolean getOwes() {return false;}

    public void transfer_in(int amount) {
        this.balance += amount;
    }

    public void transfer_out(int amount) {
        this.balance -= amount;
    }
}