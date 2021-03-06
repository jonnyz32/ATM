package TopClasses;

import java.text.SimpleDateFormat;
import java.util.*;


public class ATM_machine{

    private static Calendar date = new GregorianCalendar();

    public static void main (String[] args){
        date.add(Calendar.DAY_OF_MONTH, 1);
        UserManager.instantiate();
        MainMenuGUI.main();
    }

    static void onExit() {
        new BillHandler().saveBills();
        new UserManager().checkInterest();
        new UserManager().saveUsers();
    }

    public static Calendar getTime(){return date;}

    public static void setTime(Calendar newDate){date = newDate;}

    public static String getTimeFormatted(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(ATM_machine.getTime().getTime());
    }
}
