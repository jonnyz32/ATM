import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

public class FileManager {
    private File allTransactions;
    private static File accounts;
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    String username;

    FileManager(String username){

        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        createAccount(username);
    }

    public static void main(String[] args) {
        FileManager f = new FileManager("user2");
        Date d = new Date();
        double amount = 2745.635;
        f.depositMoney(amount, d);
        f.withdrawMoney(123.45, d);

    }

    private void createAccount(String username){

            this.username = username;
            File user = new File("group_0331\\phase1\\accounts\\" + username);
            user.mkdirs();

    }

    void depositMoney(double amount, Date date){
        try {
            File deposits = new File("group_0331\\phase1\\accounts\\" + username + "\\deposits.txt");
            this.allTransactions = new File("group_0331\\phase1\\accounts\\" + username + "\\allTransactions.txt");
            Writer writer = new BufferedWriter(new FileWriter(deposits, true));
            writer.write(String.format("Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.close();

            writer = new BufferedWriter(new FileWriter(this.allTransactions, true));
            writer.write(String.format("" +
                    "Deposited %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void withdrawMoney(double amount, Date date){
        try {
            File withdrawals = new File("group_0331\\phase1\\accounts\\" + username + "\\withdrawals.txt");
            this.allTransactions = new File("group_0331\\phase1\\accounts\\" + username + "\\allTransactions.txt");
            Writer writer = new BufferedWriter(new FileWriter(withdrawals, true));
            writer.write(String.format("Withdrew %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.close();

            writer = new BufferedWriter(new FileWriter(this.allTransactions, true));
            writer.write(String.format("" +
                    "Withdrew %s dollars on %tc \n", decimalFormat.format(amount), date));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
