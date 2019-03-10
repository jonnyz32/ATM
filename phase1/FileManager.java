import java.io.*;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class FileManager {
    //    private File allTransactions;
//    private File deposits;
//    private File withdrawals;
//    private File balanceHistory;
//    private static File accounts;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    //    private String username;
//    private double balance;
    private static File bills;
    private static File userFile;
    private static File outgoing;
    private static File alerts;

    FileManager(){

//        decimalFormat.setRoundingMode(RoundingMode.CEILING);
//        createAccount(username, accountType);
//        this.balance = initializeBalance();
        bills = new File("./bills.txt");
        userFile = new File("./users.txt");
        outgoing = new File("./outgoing.txt");
        alerts = new File("./alerts.txt");
        //       System.out.println(bills.exists());

    }

    static ArrayList<ATM_User> retrieveUsers(){
        try {
            FileInputStream file = new FileInputStream(new File("./users.txt"));
            ObjectInputStream objectStream = new ObjectInputStream(file);

            ArrayList users = (ArrayList) objectStream.readObject();
            objectStream.close();
            return users;
        }
        catch (IOException | ClassNotFoundException x){
            System.out.println("Users can't be located");
            return new ArrayList<>();
        }
    }

    static void writeOutgoing(String username, String destination, double amount){
        try {
//            balance += amount;

            Writer writer = new BufferedWriter(new FileWriter(new File("./outgoing.txt"), true));
//            writer.write(String.format("Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.write(String.format("%s, %s, %s\n", username, destination, decimalFormat.format(amount)));

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveUsers(ArrayList<ATM_User> users){
        File userFile = new File("./users.txt");
        try {
            FileOutputStream file = new FileOutputStream(userFile);
            ObjectOutputStream objectStream = new ObjectOutputStream(file);
            objectStream.writeObject(users);
            objectStream.close();
        }
        catch (IOException x){
            x.printStackTrace();
        }
    }
    static void writeAlerts(ArrayList<int[]> alertList){
        try {

            Writer writer = new BufferedWriter(new FileWriter(new File("./alerts.txt"), true));

            for (int[] x: alertList){

                writer.write(String.format("Number of %d dollar bills has fallen below 20. Current number: %d\n", x[0], x[1]));
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    static int[] retrieveBills(){
        try {
            int i = 0;
            int[] billList = new int[4];
            FileReader file = new FileReader(new File("./bills.txt"));
            BufferedReader reader = new BufferedReader(file);

            String numBillsText = reader.readLine();
            while (numBillsText != null) {
                int numBills = Integer.parseInt(numBillsText.split(":")[1]);
                billList[i] = numBills;
                numBillsText = reader.readLine();
                i += 1;

            }
            return billList;

        } catch (IOException e) {
            System.out.println("File could not be found");

            return new int[4];
        }
    }

    static void writeBills(int[] billList){
        try {
            FileWriter file = new FileWriter("./bills.txt");
            BufferedWriter writer = new BufferedWriter(file);

            for(int i = 0; i < billList.length; i++) {
                String newLine = String.valueOf(billList[i]);
                writer.write(newLine);
            }
        } catch (IOException e) {
            System.out.println("File could not be found");
        }
    }




    static ArrayList<int[]> readDeposits(String depositFile){

        ArrayList<int[]> allDeposits = new ArrayList<>();
        try {
            FileReader file = new FileReader(depositFile);
            BufferedReader reader = new BufferedReader(file);
            String temp = reader.readLine();
            while (temp != null){
                String[] depositArrayString = temp.split(",");
                int[] depositArrayNum = new int[7];
                for (int x = 0; x < 7; x++){
                    depositArrayNum[x] = Integer.parseInt(depositArrayString[x]);
                }
                allDeposits.add(depositArrayNum);
                temp = reader.readLine();
            }

        } catch (IOException e) {
            System.out.println("File could not be found");

            return null;
        }

        return allDeposits;
    }


    public static void main(String[] args) {
        FileManager f = new FileManager();
        ArrayList<int[]> testArray = new ArrayList<>();
        testArray.add(new int[]{5,15});
        testArray.add(new int[]{10,23});
        testArray.add(new int[]{20,12});
        testArray.add(new int[]{50,65});

        Date d = new Date();
        double amount = 2745.635;
//        System.out.println("Starting balance is " + f.getBalance());
////        f.depositMoney(amount, d);
////        f.withdrawMoney(123.45, d);
//        System.out.println("Ending balance is " + f.getBalance());
//        System.out.println(f.getLatestTransactions());
//        System.out.println(f.readDeposits(f.balanceHistory));
        System.out.println(f.bills.exists());
        System.out.println(f.bills.getAbsoluteFile());
        System.out.println(Arrays.toString(f.retrieveBills()));
        FileManager.writeOutgoing("user1", "uofT", 1223);
        FileManager.writeAlerts(testArray);


        ATM_User user1 = new ATM_User("user1", "pass1");
        ATM_User user2 = new ATM_User("user2", "pass2");
        ArrayList<ATM_User> userlist = new ArrayList<>();
        userlist.add(user1);
        userlist.add(user2);
//        FileManager.saveUsers(userlist);
        System.out.println(FileManager.retrieveUsers());
        System.out.println(FileManager.retrieveUsers().get(0).getUsername());
        System.out.println(System.getProperty("user.dir"));
        ArrayList<int[]> testdepostist = f.readDeposits("phase1/depositFile.txt");
        for(int i = 0; i< testdepostist.size(); i++){
            System.out.println(Arrays.toString(testdepostist.get(i)));
        }



    }

//    private void createAccount(String username, String accountType){
//
//            this.username = username;
//            File user = new File("phase1\\accounts\\" + username + "\\" + accountType);
//            user.mkdirs();
//
//
//            this.allTransactions = new File("phase1\\accounts\\" + username + "\\" + accountType + "\\allTransactions.txt");
//            this.deposits = new File("phase1\\accounts\\" + username + "\\" + accountType + "\\deposits.txt");
//            this.withdrawals = new File("phase1\\accounts\\" + username + "\\" + accountType + "\\withdrawals.txt");
//            this.balanceHistory = new File("phase1\\accounts\\" + username + "\\" + accountType + "\\balanceHistory.txt");
//
//    }

//    void depositMoney(double amount, Date date){
//        try {
//            balance += amount;
//
//            Writer writer = new BufferedWriter(new FileWriter(deposits, true));
////            writer.write(String.format("Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));
//            writer.write(decimalFormat.format(amount) + " " + date + "\n");
//
//            writer.close();
//
//            writer = new BufferedWriter(new FileWriter(allTransactions, true));
////            writer.write(String.format("" +
////                    "Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));
//
//            writer.write(decimalFormat.format(amount) + " " + date + "\n");
//            writer.close();
//
//            writer = new BufferedWriter(new FileWriter(balanceHistory, true));
////            writer.write(Double.toString(balance) + "\n" +"hello");
//            writer.write(decimalFormat.format(balance) + "\n");
//            writer.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    void withdrawMoney(double amount, Date date){
//        try {
//            balance -= amount;
//            Writer writer = new BufferedWriter(new FileWriter(withdrawals, true));
////            writer.write(String.format("Withdrew %s dollars on %tc \n", decimalFormat.format(amount), date));
//            writer.write(decimalFormat.format(amount * -1) +" " + date + "\n");
//
//            writer.close();
//
//            writer = new BufferedWriter(new FileWriter(allTransactions, true));
////            writer.write(String.format("" +
//////                    "Withdrew %s dollars on %tc \n", decimalFormat.format(amount), date));
//
//            writer.write(decimalFormat.format(amount * -1) + " " + date +"\n");
//
//            writer.close();
//
//            writer = new BufferedWriter(new FileWriter(balanceHistory, true));
//            writer.write(decimalFormat.format(balance) + "\n");
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // void CustomerRequestsBankAccountCreation(String username, String bankAccountType)


//
//    private double initializeBalance() {
//        try {
//            FileReader file = new FileReader(balanceHistory);
//            BufferedReader reader = new BufferedReader(file);
//
//            String balance = reader.readLine();
//            String temp = "0.0";
//            while (balance != null) {
//                temp = balance;
//                balance = reader.readLine();
//            }
//            return Double.parseDouble(temp);
//
//        } catch (IOException e) {
//            System.out.println("File could not be found");
//
//            return 0.0;
//        }
//    }

    //    private double getLatestTransactions(){
//        try {
//            FileReader file = new FileReader(allTransactions);
//            BufferedReader reader = new BufferedReader(file);
//
//            String transaction = reader.readLine();
//            String temp = "0.0";
//            while (transaction != null) {
//                temp = transaction;
//                transaction = reader.readLine();
//            }
//            return Double.parseDouble(temp.split(" ")[0]);
//
//        } catch (IOException e) {
//            System.out.println("File could not be found");
//
//            return 0.0;
//        }
//    }
}
