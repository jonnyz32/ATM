//The bank manager class
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.GregorianCalendar;

public class BankManager extends ATM_User implements IBankManager{

    /**
     * Requests are stored as a AccountCreationRequest<String username, String type, String accountName>
     */
    private static List<AccountCreationRequest> requests = new ArrayList<>();

    public BankManager(String username, String password){
        super(username, password);
    }

    static List<AccountCreationRequest> getRequests(){
        return requests;
    }

    /**
     * Adds a request for the account of the specified type.
     */
    void requestAccount(String username, String type, String accountName){
        requests.add(new AccountCreationRequest(username, type, accountName));
    }

    /**
     * Sets the ATM's date to the one specified, at 12:00 exactly.
     */
    public void setSystemDate(int year, int month, int day) {
        Calendar time = new GregorianCalendar(year, month - 1, day);
        ATM_machine.setTime(time);
    }

    /**
     * Undoes the last transaction (excluding bill payments) performed by the indicated user in the given account.
     */
    public void undoTransaction(String username, String account){
        if (ATM_machine.getUser(username) instanceof IAccountHolder){
            IAccountHolder target = (IAccountHolder) ATM_machine.getUser(username);
            GenericAccount targetacc = target.getAccountByName(account);
            Thread t = new Thread(targetacc.lastTransReverter);
            t.start();
        }
    }

    /**
     * Approves a customer's account creation request.
     */
    public void approveAccount(int id) throws BadInputException {
        String username = requests.get(id).getUser();
        String type = requests.get(id).getType();
        String name = requests.get(id).getName();
        requests.remove(id);
        if (ATM_machine.getUser(username) instanceof IAccountHolder){
            IAccountHolder user = (IAccountHolder) ATM_machine.getUser(username);
            user.addAccount(type, name);
        }
        else {
            throw new BadInputException("Username not accepted");
        }
    }

    /**
     * Creates a new customer with the given login credentials.
     */
    public void createNewCustomer(String username, String password){
        ATM_machine.addCustomer(username, password);
    }
}