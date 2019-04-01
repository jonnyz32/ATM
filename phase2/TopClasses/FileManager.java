package TopClasses;

import UserClasses.Users.ATM_User;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class FileManager {

    private static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private static int lineCount = 0;

    void checkForAlert(){
        Map<Integer, Integer> billsMap = new HashMap<>();
        billsMap.put(0,5);
        billsMap.put(1,10);
        billsMap.put(2, 20);
        billsMap.put(3, 50);

        int[] bills = retrieveBills();
        List<int[]> alerts = new ArrayList<>();
        for (int i = 0; i < 4; i++){
            if (bills[i] < 20){
                alerts.add(new int[]{billsMap.get(i),bills[i]});
            }
        }
        writeAlerts(alerts);
    }


    void writeErrors(String error){
        try {

            Writer writer = new BufferedWriter(new FileWriter(new File("phase2/errorLog.txt"), true));
            writer.write(error +"\n");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<ATM_User> retrieveUsers(){
        try {
            FileInputStream file = new FileInputStream(new File("phase2/users.txt"));
            ObjectInputStream objectStream = new ObjectInputStream(file);

            ArrayList<ATM_User> users = (ArrayList) objectStream.readObject();
            objectStream.close();
            return users;
        }
        catch (IOException | ClassNotFoundException x){
            System.out.println("Users can't be located");
            return new ArrayList<>();
        }
    }

    public void writeOutgoing(String username, String destination, double amount){
        try {
//            balance += amount;

            Writer writer = new BufferedWriter(new FileWriter(new File("phase2/outgoing.txt"), true));
//            writer.write(String.format("Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.write(String.format("%s, %s, %s\n", username, destination, decimalFormat.format(amount)));

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUsers(List<ATM_User> users){
        File userFile = new File("phase2/users.txt");
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
    public void writeAlerts(List<int[]> alertList){
        try {

            Writer writer = new BufferedWriter(new FileWriter(new File("phase2/alerts.txt"), true));

            for (int[] x: alertList){

                writer.write(String.format("Number of %d dollar bills has fallen below 20. Current number: %d\n", x[0], x[1]));
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public int[] retrieveBills(){
        try {
            int i = 0;
            int[] billList = new int[4];
            FileReader file = new FileReader(new File("phase2/bills.txt"));
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

    public void writeBills(int[] billList){
        try {
            Map<Integer, Integer> billMap = new HashMap<>();
            billMap.put(0, 5);
            billMap.put(1, 10);
            billMap.put(2, 20);
            billMap.put(3, 50);


            FileWriter file = new FileWriter("phase2/bills.txt");
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


    public int[] readDeposits(){

        int[] depositArrayNum = new int[6];
        try {
            FileReader file = new FileReader(new File("phase2/deposits.txt"));
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
}
