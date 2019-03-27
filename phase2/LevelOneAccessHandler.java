import java.io.Serializable;

public class LevelOneAccessHandler implements Serializable {

    public void addBills(int fives, int tens, int twenties, int fifties){
        ATM_machine.depositBills(fives, tens, twenties, fifties);
    }

    public void createNewCustomer(String username, String password) {
        ATM_machine.addCustomer(username, password);
    }
}
