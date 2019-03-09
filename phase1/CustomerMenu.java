import java.util.ArrayList;

public class CustomerMenu extends TextInterface{
    private Customer customer;

    public CustomerMenu(Customer customer, TextInterface previous){
        super(previous);
        this.customer = customer;
        addAction(1, ()->getFullSummary(), "Get account summary");
        addAction(2, ()->requestAccount(), "Request account creation");
        addAction(3, ()->getNetTotal(), "Get net total");
        initAccounts(4);
    }

    private void initAccounts(int a){
        ArrayList<GenericAccount> accounts = customer.getAccounts();
        for(int i=0;i<accounts.size();i++) {
            final int f = i; //Because the input needs to be final.
            addAction(i+a, ()->viewAccount(f), "Account: "+accounts.get(i).name);
        }
    }

    private void getFullSummary() {
        String summary = customer.getFullSummary();
        System.out.println(summary);
        showMenu();
    }

    private void requestAccount(){
        System.out.println("Options: Chequing, Credit, CreditLine, Savings\n" +
                "Which type of account?");
        String accountType = nextLine();
        customer.requestAccount(accountType);
        showMenu();
    }

    private void getNetTotal(){
        double total = customer.getNetTotal();
        System.out.println("Your net total is :");
        System.out.println("$"+total);
        showMenu();
    }

    private void viewAccount(int i) {
        GenericAccount account = customer.getAccounts().get(i);
        new AccountMenu(account, this).showMenu();
        showMenu();
    }
}
