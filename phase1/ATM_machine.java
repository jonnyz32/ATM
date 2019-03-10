import java.text.SimpleDateFormat;
import java.util.*;


public class ATM_machine{

    static ArrayList<ATM_User> users = new ArrayList<ATM_User>();
    //the final ints represent the indexes in an array of bill quantities, with the index corresponding to their
    //type.
    final static int FIVE = 0;
    final static int TEN = 1;
    final static int TWENTY = 2;
    final static int FIFTY = 3;
    private static int[] bills = new int[4];

    private static Calendar date = new GregorianCalendar();

    public static void main (String[] args){
        date.add(Calendar.DAY_OF_MONTH, 1);
        bills = FileManager.retrieveBills();
        users = FileManager.retrieveUsers();
        if(users.size()==0) {
            users.add(new BankManager("manager","password"));
        }
        new MainMenu().showMenu();
    }

    static public void onExit() {
        FileManager.writeBills(bills);
        FileManager.saveUsers(users);
        checkInterest();
    }

    static void checkInterest(){
        if (date.get(Calendar.DAY_OF_MONTH) == 1){
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


    static Calendar getTime(){return date;}

    static void setTime(Calendar newDate){date = newDate;}

    static int getNumFifties(){return bills[FIFTY];}
    static int getNumTwenties(){return bills[TWENTY];}
    static int getNumTens(){return bills[TEN];}
    static int getNumFives(){return bills[FIVE];}

    //These methods will be called from inside the Manager class.
    static void setFifties(int numBills){
        bills[FIFTY] = numBills;
    }
    static void setTwenties(int numBills){
        bills[TWENTY] = numBills;
    }
    static void setTens(int numBills){
        bills[TEN] = numBills;
    }
    static void setFives(int numBills){
        bills[FIVE] = numBills;
    }

    static void addCustomer(String username, String password){
        users.add(new Customer(username, password));
    }

    static ATM_User getUser(String username){
        for (ATM_User user: users){
            if (user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    static void update_user (Customer customer) {
        String to_update = customer.getUsername();
        int i = 0;
        for (ATM_User u: users) {
            if (u.getUsername().equals(to_update)) {
                users.set(i, customer);
                break;
            }
            i += 1;
        }
    }

    static String getTimeFormatted(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(ATM_machine.getTime().getTime());
    }
}
