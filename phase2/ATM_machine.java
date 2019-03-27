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

    static void onExit() {
        fileManager.writeBills(bills);
        userManager.saveUsers();
        userManager.checkInterest();
    }

    static ATM_User getUser(String name){
        return userManager.getUser(name);
    }


    static void addCustomer(String username, String password){
        userManager.addCustomer(username, password);
    }

    static Calendar getTime(){return date;}

    static void setTime(Calendar newDate){date = newDate;}

    static int getNumFifties(){return bills[FIFTY];}
    static int getNumTwenties(){return bills[TWENTY];}
    static int getNumTens(){return bills[TEN];}
    static int getNumFives(){return bills[FIVE];}
    static int[] getNumBills(){return bills;}

    static void depositBills(int fives, int tens, int twenties, int fifties) {
        int[] billsToAdd = {fives, tens, twenties, fifties};
        for(int i=0; i<4; i++) {
            bills[i] += billsToAdd[i];
        }
        ATM_machine.fileManager.writeBills(bills);
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

    private static int[] get_bill_split(int amount) {
        int fifties = amount / 50;
        amount -= fifties*50;
        int twenties = amount / 20;
        amount -= twenties*20;
        int tens = amount / 10;
        amount -= tens*10;
        int fives = amount / 5;
        return new int[] {fives, tens, twenties, fifties};
    }
}
