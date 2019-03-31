import java.io.Serializable;

public class LevelOneAccessHandler implements Serializable {

    public void addBills(int fives, int tens, int twenties, int fifties){
        new BillHandler().depositBills(fives, tens, twenties, fifties);
    }

    public boolean createNewUser(String username, String password, int type) {
        return UserManager.addUser(username, password, type);
    }
}
