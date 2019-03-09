import sun.applet.Main;

import javax.xml.soap.Text;
import java.io.*;


import java.lang.reflect.Array;
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
        //TODO: increment date
        bills = FileManager.retrieveBills();
        users = FileManager.retrieveUsers();
        if(users.size()==0) {
            users.add(new BankManager("manager","password"));
        }
        new MainMenu().showMenu();
    }

    static public void exit() {
        FileManager.writeBills(bills);
        FileManager.saveUsers(users);
        if (date.get(Calendar.DAY_OF_MONTH) == 1){
            for(int i = 0; i < users.size(); i++){
                //user.interest();
            }
        }
        //TODO: Add proper on-exit behaviors.
    }

    static void checkForAlert(){
        HashMap<Integer, Integer> billsMap = new HashMap<>();
        billsMap.put(0,5);
        billsMap.put(1,10);
        billsMap.put(2, 20);
        billsMap.put(3, 50);

        int[] bills = FileManager.retrieveBills();
        ArrayList<int[]> alerts = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            if (bills[i] < 20){
                alerts.add(new int[]{billsMap.get(i),bills[i]});
            }
        }
        FileManager.writeAlerts(alerts);

//        if (getNumFifties() < 20){
//            w.write("numFifties has fallen below 20. Current number: "+getNumFifties());
//        }
//        if (getNumTwenties() < 20){
//            writer.write("numTwenties has fallen below 20. Current number: "+getNumTwenties());
//        }
//        if (getNumTens() < 20){
//            writer.write("numTens has fallen below 20. Current number: "+getNumTens());
//        }
//        if (getNumFives() < 20) {
//            writer.write("numFives has fallen below 20. Current number: "+getNumFives());
//        }
//
//        //SHOULD NOT HAVE FileWriters HERE! THAT'S FileManager's JOB!
//        try{
//            FileWriter writer = new FileWriter("alerts.txt");
//
//            if (getNumFifties() < 20){
//                writer.write("numFifties has fallen below 20. Current number: "+getNumFifties());
//            }
//            if (getNumTwenties() < 20){
//                writer.write("numTwenties has fallen below 20. Current number: "+getNumTwenties());
//            }
//            if (getNumTens() < 20){
//                writer.write("numTens has fallen below 20. Current number: "+getNumTens());
//            }
//            if (getNumFives() < 20) {
//                writer.write("numFives has fallen below 20. Current number: "+getNumFives());
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
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
}
