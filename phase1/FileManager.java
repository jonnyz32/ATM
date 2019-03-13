import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class FileManager {

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static int lineCount = 0;
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

    static void saveUsers(List<ATM_User> users){
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


//    public static void main(String[] args) {
//        FileManager f = new FileManager();
////        ArrayList<int[]> testArray = new ArrayList<>();
////        testArray.add(new int[]{5,15});
////        testArray.add(new int[]{10,23});
////        testArray.add(new int[]{20,12});
////        testArray.add(new int[]{50,65});
//
//        Date d = new Date();
//        double amount = 2745.635;
////        System.out.println("Starting balance is " + f.getBalance());
//////        f.depositMoney(amount, d);
//////        f.withdrawMoney(123.45, d);
////        System.out.println("Ending balance is " + f.getBalance());
////        System.out.println(f.getLatestTransactions());
////        System.out.println(f.readDeposits(f.balanceHistory));
//        System.out.println(f.bills.exists());
//        System.out.println(f.bills.getAbsoluteFile());
//        System.out.println(Arrays.toString(FileManager.retrieveBills()));
//        FileManager.writeOutgoing("user1", "uofT", 1223);
////        FileManager.writeAlerts(testArray);
//
//
//        ATM_User user1 = new ATM_User("user1", "pass1");
//        ATM_User user2 = new ATM_User("user2", "pass2");
//        ArrayList<ATM_User> userlist = new ArrayList<>();
//        userlist.add(user1);
//        userlist.add(user2);
//        FileManager.saveUsers(userlist);
//        System.out.println(new File("phase1/users.txt").exists());
//        System.out.println(FileManager.retrieveUsers());
////        System.out.println(FileManager.retrieveUsers().get(0).getUsername());
//        System.out.println(System.getProperty("user.dir"));
//        File depositFile = new File("phase1/depositFile.txt");
//        System.out.println(depositFile.exists());
//        System.out.println(Arrays.toString(FileManager.readDeposits()));
////        for(int i = 0; i< testdepostist.size(); i++){
////            System.out.println(Arrays.toString(testdepostist.get(i)));
////        }
//
//        FileManager.writeBills(new int[]{10,1598,1,25});
//
//    }
}
