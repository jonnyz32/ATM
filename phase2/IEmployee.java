public interface IEmployee extends IUser{

    default void addBills(int fives, int tens, int twenties, int fifties){
        ATM_machine.depositBills(fives, tens, twenties, fifties);
    }

    default void createNewCustomer(String username, String password) {
        ATM_machine.addCustomer(username, password);
    }
}
