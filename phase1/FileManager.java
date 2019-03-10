import jdk.internal.org.objectweb.asm.Handle;

import java.io.*;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

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

    private static int lineCount = 0;
    FileManager(){

//        decimalFormat.setRoundingMode(RoundingMode.CEILING);
//        createAccount(username, accountType);
//        this.balance = initializeBalance();
        bills = new File("phase1/bills.txt");
        userFile = new File("phase1/users.txt");
        outgoing = new File("phase1/outgoing.txt");
        alerts = new File("phase1/alerts.txt");
        //       System.out.println(bills.exists());

    }

    static ArrayList<ATM_User> retrieveUsers(){
        try {
            FileInputStream file = new FileInputStream(new File("phase1/users.txt"));
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

            Writer writer = new BufferedWriter(new FileWriter(new File("phase1/outgoing.txt"), true));
//            writer.write(String.format("Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.write(String.format("%s, %s, %s\n", username, destination, decimalFormat.format(amount)));

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void saveUsers(ArrayList<ATM_User> users){
        File userFile = new File("phase1/users.txt");
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

            Writer writer = new BufferedWriter(new FileWriter(new File("phase1/alerts.txt"), true));

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
            FileReader file = new FileReader(new File("phase1/bills.txt"));
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
            HashMap<Integer, Integer> billMap = new HashMap<>();
            billMap.put(0, 5);
            billMap.put(1, 10);
            billMap.put(2, 20);
            billMap.put(3, 50);


            FileWriter file = new FileWriter("phase1/bills.txt");
            BufferedWriter writer = new BufferedWriter(file);

            for(int i = 0; i < billList.length; i++) {
                String newLine = String.format("%d:%d\n",billMap.get(i), billList[i]);
                writer.write(newLine);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File could not be found");
        }
    }




    static int[] readDeposits(){

        int[] depositArrayNum = new int[6];
        try {
            FileReader file = new FileReader(new File("phase1/deposits.txt"));
            BufferedReader reader = new BufferedReader(file);
            String current = reader.readLine();
            int count = 0;
            String prev ="";
            while (current != null || count == lineCount) {
                prev = current;
                current = reader.readLine();

                count += 1;
            }
                String[] depositArrayString = prev.split(",");
                for (int x = 0; x < 6; x++){
                    depositArrayNum[x] = Integer.parseInt(depositArrayString[x]);
                }
                return depositArrayNum;
            } catch (IOException e) {
            return new int[6];
        }
    }


    public static void main(String[] args) {
        FileManager f = new FileManager();
//        ArrayList<int[]> testArray = new ArrayList<>();
//        testArray.add(new int[]{5,15});
//        testArray.add(new int[]{10,23});
//        testArray.add(new int[]{20,12});
//        testArray.add(new int[]{50,65});

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
        System.out.println(Arrays.toString(FileManager.retrieveBills()));
        FileManager.writeOutgoing("user1", "uofT", 1223);
//        FileManager.writeAlerts(testArray);


        ATM_User user1 = new ATM_User("user1", "pass1");
        ATM_User user2 = new ATM_User("user2", "pass2");
        ArrayList<ATM_User> userlist = new ArrayList<>();
        userlist.add(user1);
        userlist.add(user2);
        FileManager.saveUsers(userlist);
        System.out.println(new File("phase1/users.txt").exists());
        System.out.println(FileManager.retrieveUsers());
//        System.out.println(FileManager.retrieveUsers().get(0).getUsername());
        System.out.println(System.getProperty("user.dir"));
        File depositFile = new File("phase1/depositFile.txt");
        System.out.println(depositFile.exists());
        System.out.println(Arrays.toString(FileManager.readDeposits()));
//        for(int i = 0; i< testdepostist.size(); i++){
//            System.out.println(Arrays.toString(testdepostist.get(i)));
//        }

        FileManager.writeBills(new int[]{10,1598,1,25});
        ATM_machine.checkForAlert();



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
