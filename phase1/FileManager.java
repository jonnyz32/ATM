import java.io.*;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class FileManager {
    private File allTransactions;
    private File deposits;
    private File withdrawals;
    private File bills;
    private File balanceHistory;
    private static File accounts;
    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private String username;
    private double balance;

    FileManager(String username, String accountType){

        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        createAccount(username, accountType);
        this.balance = initializeBalance();
        this.bills = new File("group_0331\\phase1\\bills.txt");
        System.out.println(bills.exists());


    }

    public static ArrayList<ATM_User> retrieveUsers(){
        File userFile = new File("./phase1/users.txt");
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




    private double initializeBalance() {
        try {
            FileReader file = new FileReader(balanceHistory);
            BufferedReader reader = new BufferedReader(file);

            String balance = reader.readLine();
            String temp = "0.0";
            while (balance != null) {
                temp = balance;
                balance = reader.readLine();
            }
            return Double.parseDouble(temp);

        } catch (IOException e) {
            System.out.println("File could not be found");

            return 0.0;
        }
    }

//     ArrayList<Double> readDeposits(File depositFile){
//         ArrayList<Double> depositList = new ArrayList<>();
//
//         try {
//            FileReader file = new FileReader(depositFile);
//            BufferedReader reader = new BufferedReader(file);
//            double depositAmount = 0.0;
//            String temp = "0.0";
//            while (temp != null){
//                depositList.add(Double.parseDouble(temp));
//                temp = reader.readLine();
//            }
//
//        } catch (IOException e) {
//            System.out.println("File could not be found");
//
//            return null;
//        }
//        depositList.remove(0);
//        return depositList;
//    }

    private double getLatestTransactions(){
        try {
            FileReader file = new FileReader(allTransactions);
            BufferedReader reader = new BufferedReader(file);

            String transaction = reader.readLine();
            String temp = "0.0";
            while (transaction != null) {
                temp = transaction;
                transaction = reader.readLine();
            }
            return Double.parseDouble(temp.split(" ")[0]);

        } catch (IOException e) {
            System.out.println("File could not be found");

            return 0.0;
        }
    }




    double getBalance(){
            return this.balance;
        }


    public static void main(String[] args) {
        FileManager f = new FileManager("user2","chequing");
        Date d = new Date();
        double amount = 2745.635;
        System.out.println("Starting balance is " + f.getBalance());
        f.depositMoney(amount, d);
        f.withdrawMoney(123.45, d);
        System.out.println("Ending balance is " + f.getBalance());
        System.out.println(f.getLatestTransactions());
//        System.out.println(f.readDeposits(f.balanceHistory));
        System.out.println(f.bills.exists());
        System.out.println(f.bills.getAbsoluteFile());
        System.out.println(Arrays.toString(f.retrieveBills()));


    }

    private void createAccount(String username, String accountType){

            this.username = username;
            File user = new File("group_0331\\phase1\\accounts\\" + username + "\\" + accountType);
            user.mkdirs();


            this.allTransactions = new File("group_0331\\phase1\\accounts\\" + username + "\\" + accountType + "\\allTransactions.txt");
            this.deposits = new File("group_0331\\phase1\\accounts\\" + username + "\\" + accountType + "\\deposits.txt");
            this.withdrawals = new File("group_0331\\phase1\\accounts\\" + username + "\\" + accountType + "\\withdrawals.txt");
            this.balanceHistory = new File("group_0331\\phase1\\accounts\\" + username + "\\" + accountType + "\\balanceHistory.txt");

    }

    void depositMoney(double amount, Date date){
        try {
            balance += amount;

            Writer writer = new BufferedWriter(new FileWriter(deposits, true));
//            writer.write(String.format("Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.write(decimalFormat.format(amount) + " " + date + "\n");

            writer.close();

            writer = new BufferedWriter(new FileWriter(allTransactions, true));
//            writer.write(String.format("" +
//                    "Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));

            writer.write(decimalFormat.format(amount) + " " + date + "\n");
            writer.close();

            writer = new BufferedWriter(new FileWriter(balanceHistory, true));
//            writer.write(Double.toString(balance) + "\n" +"hello");
            writer.write(decimalFormat.format(balance) + "\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void withdrawMoney(double amount, Date date){
        try {
            balance -= amount;
            Writer writer = new BufferedWriter(new FileWriter(withdrawals, true));
//            writer.write(String.format("Withdrew %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.write(decimalFormat.format(amount * -1) +" " + date + "\n");

            writer.close();

            writer = new BufferedWriter(new FileWriter(allTransactions, true));
//            writer.write(String.format("" +
////                    "Withdrew %s dollars on %tc \n", decimalFormat.format(amount), date));

            writer.write(decimalFormat.format(amount * -1) + " " + date +"\n");

            writer.close();

            writer = new BufferedWriter(new FileWriter(balanceHistory, true));
            writer.write(decimalFormat.format(balance) + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // void CustomerRequestsBankAccountCreation(String username, String bankAccountType)


}
