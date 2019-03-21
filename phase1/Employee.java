import java.util.ArrayList;

public class Employee extends ATM_User implements IEmployee, IAccountHolder{
    private AccountHandler accountHandler;

    Employee(String username, String password){
        super(username, password);
        accountHandler = new AccountHandler(this);
    }

    public void requestAccount(String type, String name){
        BankManager.requestAccount(this.getUsername(), type, name);
    }

    public boolean addAccount(String type, String name) {
        return accountHandler.addAccount(type, name);
    }

    public ArrayList<GenericAccount> getAccounts(){
        return accountHandler.getAccounts();
    }

    //Summary of account balances
    public String getFullSummary(){
        return accountHandler.getFullSummary();
    }

    //Net total of all accounts
    public double getNetTotal() {
        return accountHandler.getNetTotal();
    }

    public GenericAccount getAccountByName(String name) {
        return accountHandler.getAccountByName(name);
    }

    public int addBills(int type, int num){
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

    public boolean createNewCustomer(String username, String password){
        try {
            ATM_machine.addCustomer(username, password);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
}
