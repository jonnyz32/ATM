import java.io.Serializable;
import java.util.ArrayList;

public class Employee extends ATM_User implements Serializable, IEmployee, IAccountHolder{

    private AccountHandler accountHandler = new AccountHandler(this);
    public AccountHandler getHandler() {
        return accountHandler;
    }

    Employee(String username, String password){
        super(username, password);
    }
}
