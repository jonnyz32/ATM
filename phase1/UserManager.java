import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserManager {

    /**
     * Manages ATM_users
     */
    public UserManager(){
        users = FileManager.retrieveUsers();
        if(users.size()==0) {
            users.add(new BankManager("manager","password"));
        }
    }

    private static List<ATM_User> users = new ArrayList<>();

    ATM_User getUser(String username){
        for (ATM_User user: users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    void saveUsers(){
        FileManager.saveUsers(users);
    }

    void checkInterest(){
        if (ATM_machine.getTime().get(Calendar.DAY_OF_MONTH) == 1){
            for(int i = 0; i < users.size(); i++){
                if (users.get(i) instanceof Customer){
                    for(int j = 0; j < ((Customer) users.get(i)).getAccounts().size(); j++){
                        if (((Customer) users.get(i)).getAccounts().get(j) instanceof SavingAcc){
                            ((SavingAcc) ((Customer) users.get(i)).getAccounts().get(j)).increase_interest();
                        }
                    }
                }
            }
        }
    }

    void addCustomer(String username, String password){
        users.add(new Customer(username, password));
    }
}
