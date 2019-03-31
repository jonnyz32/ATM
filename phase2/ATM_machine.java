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
        MainMenuGUI.main();
    }

    static void onExit() {
        fileManager.writeBills(bills);
        userManager.saveUsers();
        userManager.checkInterest();
    }

    static ATM_User getUser(String name){
        return userManager.getUser(name);
    }

    static UserManager getUserManager() {
        return userManager;
    }


    static void addUser(String username, String password, int type){
        userManager.addUser(username, password, type);
    }

    static Calendar getTime(){return date;}

    static void setTime(Calendar newDate){date = newDate;}

    static int getNumFifties(){return bills[FIFTY];}
    static int getNumTwenties(){return bills[TWENTY];}
    static int getNumTens(){return bills[TEN];}
    static int getNumFives(){return bills[FIVE];}

    static void depositBills(int fives, int tens, int twenties, int fifties) {
        BillHandler.depositBills(fives, tens, twenties, fifties);
    }

    static void withdrawBills(int amount){
        BillHandler.withdrawBills(amount);
    }

    static String getTimeFormatted(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(ATM_machine.getTime().getTime());
    }
}
