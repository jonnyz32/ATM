import java.util.List;

interface IAccountHolder extends IUser{

    void requestAccount(String type, String name);

    boolean addAccount(String type, String name);

    List<GenericAccount> getAccounts();

    //Summary of account balances
    String getFullSummary();

    //Net total of all accounts
    double getNetTotal();

    GenericAccount getAccountByName(String name);
}
