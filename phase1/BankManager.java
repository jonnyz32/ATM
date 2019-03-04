//The bank manager class
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BankManager extends ATM_User{

    public BankManager(String username, String password){
        super(username, password);
    }

    /**
     * Sets the ATM's date to the one specified, at 12:00 exactly.
     */
    public void setSystemDate(int year, int month, int day){
        Calendar time = new GregorianCalendar(year, month-1, day);
        ATM_machine.setTime(time);
    }

    /**
     * Adds num bills of the specified type to the machine.
     * @return Returns the new number of bills, or -1 if the bill type cannot be found.
     */
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