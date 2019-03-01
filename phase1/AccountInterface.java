// An abstract Class for Acounts

public interface AccountInterface {


    // Set the balance of the account
    void setBalance(int balance);

    // True if customer owes money, false otherwise
    void setOwes(boolean owes);

    int getBalance();

    boolean getOwes();

    // Add to the balance
    void transfer_in(int amount);

    // Subtract from the balance
    void transfer_out(int amount);


}