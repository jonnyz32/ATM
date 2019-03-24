import java.text.SimpleDateFormat;
import java.util.*;


public class ATM_machine{

    //the final ints represent the indexes in an array of bill quantities, with the index corresponding to their
    //type.
    final static int FIVE = 0;
    final static int TEN = 1;
    final static int TWENTY = 2;
    final static int FIFTY = 3;
    private static int[] bills = new int[4];
    private static UserManager userManager;
    static FileManager fileManager;

    private static Calendar date = new GregorianCalendar();

    public static void main (String[] args){
        fileManager = new FileManager();
        date.add(Calendar.DAY_OF_MONTH, 1);
        bills = fileManager.retrieveBills();
        userManager = new UserManager();
        new MainMenu().showMenu();
    }

    static public void onExit() {
        fileManager.writeBills(bills);
        userManager.saveUsers();
        userManager.checkInterest();
    }

    static ATM_User getUser(String name){
        return userManager.getUser(name);
    }


    static boolean addCustomer(String username, String password){
        return userManager.addCustomer(username, password);
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

    static void withdrawBills(int[] billsToRemove){
        for(int i=0; i<4; i++) {
            bills[i] -= billsToRemove[i];
        }
        ATM_machine.fileManager.writeBills(bills);
        ATM_machine.fileManager.checkForAlert();
    }

    static void withdrawBills(int amount){
        int[] billsToRemove = get_bill_split(amount);
        for(int i=0; i<4; i++) {
            bills[i] -= billsToRemove[i];
        }
        ATM_machine.fileManager.writeBills(bills);
        ATM_machine.fileManager.checkForAlert();
    }

    static String getTimeFormatted(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(ATM_machine.getTime().getTime());
    }

    static int[] get_bill_split(int amount) {
        int[] bill_split = new int[4];
        int fifties = amount / 50;
        bill_split[0] = fifties;
        int rem_50 = amount % 50;
        int twenties = rem_50 / 20;
        bill_split[1] = twenties;
        int rem_10 = rem_50 % 20;
        int tens = rem_10 / 10;
        bill_split[2] = tens;
        int rem_5 = rem_10 % 10;
        int fives = rem_5 / 5;
        bill_split[3] = fives;
        return bill_split;
    }
}
