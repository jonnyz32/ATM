import java.util.ArrayList;

public interface IAccountHolder extends IUser{

    void requestAccount(String type, String name);

    void addAccount(String type, String name);

    void addAccount(GenericAccount acc);

    ArrayList<GenericAccount> getAccounts();

    //Summary of account balances
    String getFullSummary();

    //Net total of all accounts
    double getNetTotal();

    GenericAccount getAccountByName(String name);
}
