public interface IEmployee extends IUser{

    default void addBills(int fives, int tens, int twenties, int fifties){
        ATM_machine.depositBills(fives, tens, twenties, fifties);
    }

    default boolean createNewCustomer(String username, String password){
        try {
            ATM_machine.addCustomer(username, password);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

}
