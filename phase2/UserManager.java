import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserManager {

    /**
     * Manages ATM_users
     */
    public UserManager(){
        List<ATM_User> users = new FileManager().retrieveUsers();
        if(users.size()==0) {
            users.add(new BankManager("manager","password"));
        }
    }

    private static List<ATM_User> users = new ArrayList<>();

    ATM_User getUser(String username){
        ATM_User found = null;
        for (ATM_User user: users){
            if (user.getUsername().equals(username)){
                saveUsers();
                found = user;
            }
        }
        return found;
    }

    void saveUsers(){
        new FileManager().saveUsers(users);
    }

    void checkInterest(){
        if (ATM_machine.getTime().get(Calendar.DAY_OF_MONTH) == 1){
            for(int i = 0; i < users.size(); i++){
                if (users.get(i) instanceof IAccountHolder){
                    for(int j = 0; j < ((IAccountHolder) users.get(i)).getAccounts().size(); j++){
                        if (((IAccountHolder) users.get(i)).getAccounts().get(j) instanceof SavingAcc){
                            ((SavingAcc) ((IAccountHolder) users.get(i)).getAccounts().get(j)).increase_interest();
                        }
                    }
                }
            }
        }
        saveUsers();
    }

    boolean addCustomer(String username, String password){
        boolean isFree = getUser(username)==null;
        if(isFree) {
            users.add(new Customer(username, password));
            saveUsers();
        }
        return isFree;
    }
}