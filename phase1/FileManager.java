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
//    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
//    private String username;
//    private double balance;
      private static File bills;
      private static File userFile;

    FileManager(){

//        decimalFormat.setRoundingMode(RoundingMode.CEILING);
//        createAccount(username, accountType);
//        this.balance = initializeBalance();
        bills = new File("phase1\\bills.txt");
        userFile = new File("phase1/users.txt");//        System.out.println(bills.exists());

    }

    static ArrayList<ATM_User> retrieveUsers(){
        try {
            FileInputStream file = new FileInputStream(userFile);
            ObjectInputStream objectStream = new ObjectInputStream(file);

            ArrayList users = (ArrayList) objectStream.readObject();
            objectStream.close();
            return users;
        }
        catch (IOException | ClassNotFoundException x){
            x.printStackTrace();
        }
        return null;
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

    static int[] retrieveBills(){
        try {
            int i = 0;
            int[] billList = new int[4];
            FileReader file = new FileReader(bills);
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

            return null;
        }
    }

    static void writeBills(int[] billList){
        try {
            FileWriter file = new FileWriter(bills);
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


        ATM_User user1 = new ATM_User("user1", "pass1");
        ATM_User user2 = new ATM_User("user2", "pass2");
        ArrayList<ATM_User> userlist = new ArrayList<>();
        userlist.add(user1);
        userlist.add(user2);
        FileManager.saveUsers(userlist);
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
