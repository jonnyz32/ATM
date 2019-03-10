//The bank manager class
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.GregorianCalendar;

public class BankManager extends ATM_User{

    /**
     * Requests are stored as a AccountRequest<String username, String type, String accountName>
     */
    private static List<AccountRequest> requests = new ArrayList<>();

    public BankManager(String username, String password){
        super(username, password);
    }

    static List<AccountRequest> getRequests(){
        return requests;
    }

    /**
     * Adds a request for the account of the specified type.
     */
    static void requestAccount(String username, String type, String accountName){
        requests.add(new AccountRequest(username, type, accountName));
    }

    /**
     * Sets the ATM's date to the one specified, at 12:00 exactly.
     */
    void setSystemDate(int year, int month, int day){
        Calendar time = new GregorianCalendar(year, month-1, day);
        ATM_machine.setTime(time);
    }

    /**
     * Adds num bills of the specified type to the machine.
     * @return Returns the new number of bills, or -1 if the bill type cannot be found.
     */
    int addBills(int type, int num){
        int temp;
        if (type==5){
            temp = ATM_machine.getNumFives();
            ATM_machine.setFives(temp + num);
            return temp+num;
        }
        if (type==10){
            temp = ATM_machine.getNumTens();
            ATM_machine.setTens(temp + num);
            return temp+num;
        }
        if (type==20){
            temp = ATM_machine.getNumTwenties();
            ATM_machine.setTwenties(temp + num);
            return temp+num;
        }
        if (type==50){
            temp = ATM_machine.getNumFifties();
            ATM_machine.setFifties(temp + num);
            return temp+num;
        }
        return -1;
    }

    /**
     * Undoes the last transaction (excluding bill payments) performed by the indicated user in the given account.
     */
    void undoTransaction(String username, String account){
        if (ATM_machine.getUser(username) instanceof Customer){
            Customer target = (Customer) ATM_machine.getUser(username);
            GenericAccount targetacc = target.getAccountByName(account);
        }
    }

    /**
     * Approves a customer's account creation request.
     */
    boolean approveAccount(int id){
        String username = requests.get(id).getUser();
        String type = requests.get(id).getType();
        String name = requests.get(id).getName();
        if (ATM_machine.getUser(username) instanceof Customer){
            Customer user = (Customer) ATM_machine.getUser(username);
            if(user.addAccount(type, name)) {
                requests.remove(id);
                return true;
            }
        }
        requests.remove(id);
        return false;
    }

    /**
     * Creates a new customer with the given login credentials.
     */
    void createNewCustomer(String username, String password){
        ATM_machine.addCustomer(username, password);
    }
}