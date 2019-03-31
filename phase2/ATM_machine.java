import java.text.SimpleDateFormat;
import java.util.*;


public class ATM_machine{

    //the final ints represent the indexes in an array of bill quantities, with the index corresponding to their
    //type.

    private static Calendar date = new GregorianCalendar();

    public static void main (String[] args){
        date.add(Calendar.DAY_OF_MONTH, 1);
        MainMenuGUI.main();
    }

    static void onExit() {
        new BillHandler().saveBills();
        new UserManager().saveUsers();
        new UserManager().checkInterest();
    }

    static Calendar getTime(){return date;}

    static void setTime(Calendar newDate){date = newDate;}

    static String getTimeFormatted(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(ATM_machine.getTime().getTime());
    }
}
