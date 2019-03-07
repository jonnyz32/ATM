//The bank manager class
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.GregorianCalendar;
//
//public class BankManager extends ATM_User{
//
//    private static List<Pair<String, String>> requests = new ArrayList<>();
//
//    public BankManager(String username, String password){
//        super(username, password);
//    }
//
//    /**
//     * Adds a request for the account of the specified type.
//     */
//    static void requestAccount(String username, String type){
//        requests.add(new Pair<>(username, type));
//    }
//
//    /**
//     * Sets the ATM's date to the one specified, at 12:00 exactly.
//     */
//    public void setSystemDate(int year, int month, int day){
//        Calendar time = new GregorianCalendar(year, month-1, day);
//        ATM_machine.setTime(time);
//    }
//
//    /**
//     * Adds num bills of the specified type to the machine.
//     * @return Returns the new number of bills, or -1 if the bill type cannot be found.
//     */
//    public int addBills(int type, int num){
//        int temp;
//        if (type==5){
//            temp = ATM_machine.getNumFives();
//            ATM_machine.setFives(temp + num);
//            return temp+num;
//        }
//        if (type==10){
//            temp = ATM_machine.getNumTens();
//            ATM_machine.setTens(temp + num);
//            return temp+num;
//        }
//        if (type==20){
//            temp = ATM_machine.getNumTwenties();
//            ATM_machine.setTwenties(temp + num);
//            return temp+num;
//        }
//        if (type==50){
//            temp = ATM_machine.getNumFifties();
//            ATM_machine.setFifties(temp + num);
//            return temp+num;
//        }
//        return -1;
//    }
//
//    /**
//     * Undoes the last transaction (excluding bill payments) performed by the indicated user in the given account.
//     */
//    public void undoTransaction(String username, String account){
//
//    }
//
//    /**
//     * Approves a customer's account creation request.
//     */
//    public void approveAccount(int id){
//        String username = requests.get(id).getLeft();
//        String account = requests.get(id).getRight();
//        if (ATM_machine.getUser(username) instanceof Customer){
//            Customer user = (Customer) ATM_machine.getUser(username);
//            user.addAccount(account);
//        }
//    }
//
//    /**
//     * Creates a new customer with the given login credentials.
//     */
//    public void createNewCustomer(String username, String password){
//        ATM_machine.addCustomer(username, password);
//    }
//}