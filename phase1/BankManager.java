//The bank manager class

public class BankManager extends ATM_User{

    /**
     * Sets the ATM's date to the one specified.
     */
    public void setSystemDate(int year, int month, int day){
    }

    /**
     * Adds num bills of the specified type to the machine.
     */
    public void addBills(int type, int num){
    }

    /**
     * Undoes the last transaction (excluding bill payments) performed by the indicated user.
     */
    public void undoTransaction(String username){
    }

    /**
     * Approves a customer's account creation request.
     * @param id The id of the request.
     */
    public void approveAccount(int id){
    }

    /**
     * Creates a new customer with the given login credentials.
     */
    public void createNewUser(String username, String password){
    }
}