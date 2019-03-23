import java.util.ArrayList;

public interface IAccountHolder extends IUser{

    ArrayList<GenericAccount> accounts = new ArrayList<GenericAccount>();

    default void requestAccount(String type, String name) {
        new BankManager("temp", "temp").requestAccount(this.getUsername(), type, name);
    }

    default boolean addAccount(String type, String name) {
        System.out.println("ADDING ACCOUNT");
        System.out.println(this);
        /*return*/ new AccountHandler(this).addAccount(type, name);
        System.out.println(this.getUsername());
        System.out.println(this.getAccountByName(name));
        System.out.println(this.getFullSummary());
        return true;
    }

    default ArrayList<GenericAccount> getAccounts() {
        return new AccountHandler(this).getAccounts();
    }

    //Summary of account balances
    default String getFullSummary() {
        System.out.println(this);
        return new AccountHandler(this).getFullSummary();
    }

    //Net total of all accounts
    default double getNetTotal() {
        return new AccountHandler(this).getNetTotal();
    }

    default GenericAccount getAccountByName(String name) {
        return new AccountHandler(this).getAccountByName(name);
    }
}
