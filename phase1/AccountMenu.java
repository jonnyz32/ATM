import java.util.ArrayList;

public abstract class AccountMenu extends TextInterface{

    private GenericAccount account;

    public AccountMenu(GenericAccount account, TextInterface previous){
        super(previous);
        this.account = account;
        header = "Account Menu";
        footer = "Choose an option:";
    }

    void getLastTransaction(){
        System.out.println("Last Transaction:");
        String latest = account.getLatestTransaction();
        System.out.println(latest + "in" + account.name);
        showMenu();
    }

    void showBalance() {
        System.out.print("This account has a ");
        if(account.isAsset()) {
            System.out.print("debit");
        } else {
            System.out.print("credit");
        }
        System.out.println(" balance of:");
        System.out.println("$"+account.getBalance());
        showMenu();
    }

    void depositFromFile(){
        account.depositFromFile();
        showMenu();
    }

    void transferToSelf(){
        System.out.println("Amount to transfer?");
        double amount = nextDouble();
        System.out.println("Account to transfer to?");
        //TODO add options
        String other_acc_name = nextLine();
        account.transferToSelf(amount, other_acc_name);
        showMenu();
    }

    private IAccountHolder transferToOther_helper() {
        // Get the user and the account
        System.out.println("Who would you like to transfer to");
        String other_username = nextLine();
        boolean other_exists = false;
        ATM_User other_user = ATM_machine.getUser(other_username);
        if (other_user == null) {
            System.out.println("That username does not exist, please try another user.");
            transferToOther_helper();
        }
        if (other_user instanceof IAccountHolder) {
            return (IAccountHolder) other_user;
        }
        return null;
    }

    void transferToOther(){
        IAccountHolder other_user = transferToOther_helper();
        String message = "Which account of " + other_user.getUsername() + " would you like to transfer to?";
        System.out.println(message);
        String other_acc_name = nextLine();
        GenericAccount other_acc = other_user.getAccountByName(other_acc_name);
        System.out.println("How much would you like to transfer?");
        double amount = nextDouble();
        account.transferToOther(other_user, other_acc, amount);
        showMenu();
    }

    void transferToExternal(){
        System.out.println("What bill would you like to pay?");
        String name = nextLine();
        System.out.println("How much are you paying?");
        double amount = nextDouble();
        account.transferToExternal(name, amount);
        showMenu();
    }

    void withdraw(){
        System.out.println("How much would you like to withdraw?");
        System.out.println("Amount should be a multiple of 5");
        int amount = nextInt();
        account.withdraw(amount);
//        System.out.println("You don't have enough money to withdraw this much! Balance: " + account.getBalance());
        showMenu();
    }
}
