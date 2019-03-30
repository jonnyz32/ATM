//The bank manager class
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.GregorianCalendar;

public class BankManager extends ATM_User implements ILevelOneAccess, ILevelTwoAccess {

    /**
     * Requests are stored as a AccountCreationRequest<String username, String type, String accountName>
     */
    private static List<AccountCreationRequest> requests = new ArrayList<>();

    private LevelOneAccessHandler levelOneAccessHandler;

    BankManager(){
        super(null, null);
    }

    public BankManager(String username, String password){
        super(username, password);
        levelOneAccessHandler = new LevelOneAccessHandler();
    }

    public List<AccountCreationRequest> getRequests(){
        return requests;
    }

    public void addBills(int fives, int tens, int twenties, int fifties){
        levelOneAccessHandler.addBills(fives, tens, twenties, fifties);
    }

    public void createNewCustomer(String username, String password, int result) {
        levelOneAccessHandler.createNewCustomer(username, password, result);
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
    public void undoTransaction(String username, String account, int n_trans) throws BadInputException{
        if (ATM_machine.getUser(username) instanceof IAccountHolder){
            IAccountHolder target = (IAccountHolder) ATM_machine.getUser(username);
            GenericAccount targetacc = target.getAccountByName(account);
            if (targetacc.past_trans.size() < n_trans) {
                throw new BadInputException("This account does not have that many transactions");
            }
            else {
                //Get Right Reverters.
                int size = targetacc.past_reverters.size();
                List<Runnable> reverts = targetacc.past_reverters.subList(size - n_trans, size);
                for (int i = reverts.size() - 1; i >= 0; i--) {
                    reverts.get(i).run();
                }
            }
            for (int i = 1; i <= n_trans; i++) {
                targetacc.past_reverters.remove(targetacc.past_reverters.size() - 1);
            }
        }
        else{
            throw new BadInputException("User not accepted");
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
}