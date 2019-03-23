// An abstract Class for Acounts

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GenericAccount implements Serializable {

    IAccountHolder owner;
    double balance;
    boolean asset;
    String name;
    ArrayList<String> past_trans;
    Calendar creation_date;
    String type;
    private StockFetcher stockFetcher = new StockFetcher("240UNLH6CSLKUUKH");
    private CurrencyConverter currencyConverter = new CurrencyConverter(stockFetcher);


    Runnable lastTransReverter;
    String lastTransText;

    void depositCash(int fives, int tens, int twenties, int fifties) {
        Double total = (double) (fives*5 + tens*10 + twenties*20 + fifties*50);
        if(asset) {
            balance += total;
        } else {
            balance -= total;
        }
        ATM_machine.setFives(ATM_machine.getNumFives()+fives);
        ATM_machine.setTens(ATM_machine.getNumTens()+tens);
        ATM_machine.setTwenties(ATM_machine.getNumTwenties()+twenties);
        ATM_machine.setFifties(ATM_machine.getNumFifties()+fifties);

        lastTransReverter = (Runnable & Serializable) this::revertDeposit;
        lastTransText = "Deposited cash amount of $"+total + " to: " + name;
        past_trans.add(lastTransText);

        int[] bills = ATM_machine.fileManager.retrieveBills();
        bills[0] = bills[0] + fives;
        bills[1] = bills[1] + tens;
        bills[2] = bills[2] + twenties;
        bills[3] = bills[3] + fifties;

        ATM_machine.fileManager.writeBills(bills);

    }


    void depositForeignCurrency(String currency, double amount) throws Exception{
        double amountInCanadian = currencyConverter.convertCurrency(currencyConverter.currencySymbolGetter(currency),
                amount);
        balance += amountInCanadian;
        lastTransText = String.format("Deposited $%f %s(%f Canadian) to %s",amount, currency, amountInCanadian, name);
        // TODO I don't know what this line below does or if it should be here
        lastTransReverter =  (Runnable & Serializable) this::revertDeposit;
    }

    void withdrawForeignCurrency(String currency, double amount) throws Exception{
        double amountInCanadian = currencyConverter.convertCurrency(currencyConverter.currencySymbolGetter(currency),
                amount);
        balance -= amountInCanadian;
        lastTransText = String.format("Withdrew $%f %s(%f Canadian) to %s",amount, currency, amountInCanadian, name);
    }



    void depositFromFile() {
        int[] deposit = ATM_machine.fileManager.readDeposits();
        if (deposit[1] == 0){
            depositCash(deposit[2], deposit[3], deposit[4], deposit[5]);
        }
        else {
                depositCheque((double) deposit[0]);
            }
        }

    void depositCheque(Double amount) {
        if(asset) {
            balance += amount;
        } else {
            balance -= amount;
        }
        lastTransText = "Deposited cheque of $"+ amount + " to: " + name;
        lastTransReverter = (Runnable & Serializable) this::revertDeposit ;
    }
    void revertDeposit() {
        if(asset) {
            balance -= getLastAmount();
        } else {
            balance += getLastAmount();
        }
        past_trans.remove(past_trans.size() - 1);
        lastTransText = getLatestTransaction();
    }

    void transferToExternal(String name, double amount) {
        if(asset) {balance-=amount;}
        else {balance+=amount;}
        ATM_machine.fileManager.writeOutgoing(owner.getUsername(), name, amount);
    }

    boolean withdraw(int amount) {
        int[] bills = get_bill_split(amount);
        int fifties = bills[0];
        int twenties = bills[1];
        int tens = bills[2];
        int fives = bills[3];
//        balance -= (fifties*50 + twenties*20 + tens*10 + fives*5);

        ATM_machine.setFifties(ATM_machine.getNumFifties() - fifties);
        ATM_machine.setTwenties(ATM_machine.getNumTwenties()- twenties);
        ATM_machine.setTens(ATM_machine.getNumTens() - tens);
        ATM_machine.setFives(ATM_machine.getNumFives()- fives);

        int[] billFile = ATM_machine.fileManager.retrieveBills();
        billFile[0] = billFile[0] - fives;
        billFile[1] = billFile[1] - tens;
        billFile[2] = billFile[2] - twenties;
        billFile[3] = billFile[3] - fifties;

        ATM_machine.fileManager.writeBills(billFile);
        ATM_machine.fileManager.checkForAlert();
        return true;
    }

    int[] get_bill_split(int amount) {
        int[] bill_split = new int[4];
        int fifties = amount / 50;
        bill_split[0] = fifties;
        int rem_50 = amount % 50;
        int twenties = rem_50 / 20;
        bill_split[1] = twenties;
        int rem_10 = rem_50 % 20;
        int tens = rem_10 / 10;
        bill_split[2] = tens;
        int rem_5 = rem_10 % 10;
        int fives = rem_5 / 5;
        bill_split[3] = fives;
        return bill_split;
    }

    // Return the amount the was last transferred.
    Double getLastAmount() {
        Matcher m = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+").matcher(getLatestTransaction());
        if (m.find()) {
            return Double.valueOf(m.group(0));
        }
        else {
            return 0.0;
        }
    }

    boolean isAsset() {
        return this.asset;
    }

    Double getBalance() {
        return this.balance;
    }

    // Set the balance of the account
    void setBalance(double balance) {
        this.balance = balance;
    }

    // Returns the latest transaction.
    String getLatestTransaction() {return past_trans.get(past_trans.size() - 1);}


    //Get a string representation
    String getSummary() {
        return "name: " + name + "\n" + "Owner: " + owner.getUsername() + "\n"
                + "Asset: " + asset + "\n" + "Balance: " + balance + "\n" + "Last Transaction: " + getLatestTransaction();
    }
}