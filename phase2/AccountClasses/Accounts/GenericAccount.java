package AccountClasses.Accounts;// An abstract Class for Acounts

import AccountClasses.StocksAndCurrencies.CurrencyConverter;
import AccountClasses.Withdrawals.IWithdrawable;
import AccountClasses.StocksAndCurrencies.StockFetcher;
import TopClasses.ATM_machine;
import TopClasses.BillHandler;
import TopClasses.FileManager;
import UserClasses.Users.IAccountHolder;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class GenericAccount implements Serializable {

    public IAccountHolder owner;
    IWithdrawable withdrawable;
    public double balance;
    boolean asset;
    public String name;
    public ArrayList<String> past_trans;
    Calendar creation_date;
    public String type;
    private StockFetcher stockFetcher = new StockFetcher("240UNLH6CSLKUUKH");
    private CurrencyConverter currencyConverter = new CurrencyConverter(stockFetcher);
    public ArrayList<Runnable> past_reverters;


    public Runnable lastTransReverter;
    public String lastTransText;

    GenericAccount(String name, IAccountHolder o){
        this.name = name;
        owner = o;
        past_trans = new ArrayList<>();
        balance = 0;
        creation_date = ATM_machine.getTime();
        lastTransText = "No transactions have been made";
        past_trans.add(lastTransText);
        past_reverters = new ArrayList<Runnable>();
    }

    public void depositCash(int fives, int tens, int twenties, int fifties) {
        Double total = (double) (fives*5 + tens*10 + twenties*20 + fifties*50);
        if(asset) {
            balance += total;
        } else {
            balance -= total;
        }
        new BillHandler().depositBills(fives, tens, twenties, fifties);

        lastTransReverter = (Runnable & Serializable) this::revertDeposit;
        past_reverters.add(lastTransReverter);
        lastTransText = "Deposited cash amount of $"+total + " to: " + name;
        past_trans.add(lastTransText);

    }


    public void depositForeignCurrency(String currency, double amountInForeignCurrency){
        double amountInCanadian;
        amountInCanadian = currencyConverter.convertCurrency(currencyConverter.currencySymbolGetter(currency),
                    amountInForeignCurrency);
        balance += amountInCanadian;
        lastTransText = String.format("Deposited $%f %s(%f Canadian) to %s",amountInForeignCurrency, currency, amountInCanadian, name);
        // TODO I don't know what this line below does or if it should be here
        lastTransReverter =  (Runnable & Serializable) this::revertDeposit;
    }

    public void withdrawForeignCurrency(String currency, double amountInForeignCurrency){
        double amountInCanadian;
        amountInCanadian = currencyConverter.convertCurrency(currencyConverter.currencySymbolGetter(currency), amountInForeignCurrency);

        balance -= amountInCanadian;
        lastTransText = String.format("Withdrew $%f %s(%f Canadian) to %s",amountInForeignCurrency, currency, amountInCanadian, name);
    }



    public void depositFromFile() {
        int[] deposit = new FileManager().readDeposits();
        if (deposit[1] == 0){
            depositCash(deposit[2], deposit[3], deposit[4], deposit[5]);
        }
        else {
                depositCheque((double) deposit[0]);
            }
        }

    public void depositCheque(Double amount) {
        if(asset) {
            balance += amount;
        } else {
            balance -= amount;
        }
        lastTransText = "Deposited cheque of $"+ amount + " to: " + name;
        lastTransReverter = (Runnable & Serializable) this::revertDeposit ;
        past_reverters.add(lastTransReverter);
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

    public void transferToExternal(String name, double amount) {
        if(asset) {balance-=amount;}
        else {balance+=amount;}
        new FileManager().writeOutgoing(owner.getUsername(), name, amount);
    }

    public void withdraw(int amount) {
        if(asset) {
            balance = withdrawable.withdraw(amount, balance);
        } else {
            balance = withdrawable.withdraw(-amount, balance);
        }
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

    public boolean isAsset() {
        return this.asset;
    }

    public Double getBalance() {
        return this.balance;
    }

    // Set the balance of the account
    void setBalance(double balance) {
        this.balance = balance;
    }

    // Returns the latest transaction.
    public String getLatestTransaction() {return past_trans.get(past_trans.size() - 1);}


    //Get a string representation
    public abstract String getSummary();

    public String getCreationDate() {
        return ( new SimpleDateFormat( "yyyy-MM-dd" ) ).format( creation_date.getInstance().getTime() );
    }
}