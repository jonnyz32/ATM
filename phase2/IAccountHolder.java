import java.util.ArrayList;

public interface IAccountHolder extends IUser{


    abstract AccountHandler getHandler();

    default void requestAccount(String type, String name) {
        new BankManager("temp", "temp").requestAccount(this.getUsername(), type, name);
    }

    default boolean addAccount(String type, String name) {
        return getHandler().addAccount(type, name);
    }

    default ArrayList<GenericAccount> getAccounts() {
        return getHandler().getAccounts();
    }

    //Summary of account balances
    default String getFullSummary() {
        return getHandler().getFullSummary();
    }

    //Net total of all accounts
    default double getNetTotal() {
        return getHandler().getNetTotal();
    }

    default GenericAccount getAccountByName(String name) {
        return getHandler().getAccountByName(name);
    }
}
