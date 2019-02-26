// A class for savings accounts

public class SavingAcc {
    private int balance;

    public SavingAcc() {
        balance = 0;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void transfer_in(int amount) {
        this.balance += amount;
    }

    public void transfer_out(int amount) {
        this.balance -= amount;
    }
}