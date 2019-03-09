import java.util.ArrayList;

public class AccountMenu extends TextInterface{

    private GenericAccount account;

    public AccountMenu(GenericAccount account, TextInterface previous){
        super(previous);
        this.account = account;
        addAction(1, ()->showBalance(), "View Balance");
        addAction(2, ()->getLastTransaction(), "See Last Transaction");
        addAction(3, ()->depositCash(), "Deposit Cash");
        addAction(4, ()->transferToSelf(), "Transfer to another account");
        addAction(5, ()->transferToOther(), "Transfer to another user");
        addAction(6, ()->transferToExternal(), "Pay external bill");
        addAction(7, ()->withdraw(), "Withdraw money");
    }

    private void getLastTransaction(){
        System.out.println("Last Transaction:");
        String latest = account.getLatestTransaction().toString();
        System.out.println(latest + "in" + account.name);
        showMenu();
    }

    private void showBalance() {
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

    private void depositCash(){
        System.out.println("How many Fives?");
        int fives = nextInt();
        System.out.println("How many Tens?");
        int tens = nextInt();
        System.out.println("How many Twenties?");
        int twenties = nextInt();
        System.out.println("How many Fifties?");
        int fifties = nextInt();
        account.depositCash(fives, tens, twenties, fifties);
        showMenu();
    }

    private void transferToSelf(){
        System.out.println("Amount to transfer?");
        double amount = nextDouble();
        System.out.println("Account to transfer to?");
        String other_acc_name = nextLine();
        account.transferToSelf(amount, other_acc_name);
        showMenu();
    }

    private Customer transferToOther_helper() {
        // Get the user and the account
        System.out.println("Who would you like to transfer to");
        String other_username = nextLine();
        boolean other_exists = false;
        Customer other_user = null;
        ArrayList<ATM_User> all_users = ATM_machine.users;
        for (ATM_User u: all_users) {
            if (u.getUsername().equals(other_username)) {
                other_user = (Customer) u;
                other_exists = true;
                break;
            }
        }
        if (!other_exists) {
            System.out.println("That username does not exist, please try another user.");
            transferToOther_helper();
        }
        return other_user;
    }

    private void transferToOther(){
        Customer other_user = transferToOther_helper();
        String message = "Which account of " + other_user.getUsername() + " would you like to transfer to?";
        System.out.println(message);
        String other_acc_name = nextLine();
        GenericAccount other_acc = other_user.getAccountByName(other_acc_name);
        System.out.println("How much would you like to transfer?");
        double amount = nextDouble();
        account.transferToOther(other_user, other_acc, amount);
        showMenu();
    }

    private void transferToExternal(){
        System.out.println("What bill would you like to pay?");
        String name = nextLine();
        System.out.println("How much are you paying?");
        double amount = nextDouble();
        account.transferToExternal(name, amount);
        showMenu();
    }

    private void withdraw(){
        System.out.println("How much would you like to withdraw?");
        System.out.println("Amount should be a multiple of 5");
        int amount = nextInt();
        account.withdraw(amount);
        showMenu();
    }
}
