package UserClasses.Users;

import TopClasses.BillHandler;
import TopClasses.UserManager;

import java.io.Serializable;

public class LevelOneAccessHandler implements Serializable {

    public void addBills(int fives, int tens, int twenties, int fifties){
        new BillHandler().depositBills(fives, tens, twenties, fifties);
    }

    public boolean createNewUser(String username, String password, int type) {
        return new UserManager().addUser(username, password, type);
    }
}
