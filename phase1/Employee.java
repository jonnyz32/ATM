import java.io.Serializable;
import java.util.ArrayList;

public class Employee extends ATM_User implements Serializable, IEmployee, IAccountHolder{

    Employee(String username, String password){
        super(username, password);
    }
}
