import java.util.ArrayList;

public class CustomerMenu extends TextInterface{
    private Customer customer;

    public CustomerMenu(Customer customer, TextInterface previous){
        super(previous);
        this.customer = customer;
        header = "Customer Menu";
        footer = "Choose an option:";
        addAction(1, ()->getFullSummary(), "Get account summary");
        addAction(2, ()->requestAccount(), "Request account creation");
        addAction(3, ()->getNetTotal(), "Get net total");
        initAccounts(4);
    }

    private void initAccounts(int a){
        ArrayList<GenericAccount> accounts = customer.getAccounts();
        for(int i=0;i<accounts.size();i++) {
            final int f = i; //Because the input needs to be final.
            addAction(i+a, ()->viewAccount(f), "Account: "+accounts.get(i).name + accounts.get(i).type);
        }
    }

    private void getFullSummary() {
        String summary = customer.getFullSummary();
        System.out.println(summary);
        showMenu();
    }

    private void requestAccount(){
        System.out.println("Options: chequing, credit, creditline, savings\n" +
                "Which type of account?");
        String accountType = "InvalidAccount";
        boolean loop = true;
        String[] validAccounts = {"chequing","credit","creditline","savings"};
        while(loop) {
            accountType = nextLine().toLowerCase();
            for(String va : validAccounts) {
                if(va.equals(accountType)) {
                    loop=false;
                }
            }
        }
        if (accountType.equals("chequing")) {
            System.out.println("Would you like to make this your primary account (yes or no)");
            String ans = nextLine();
            if (ans.equals("yes")) {
                accountType += "(primary)";
            }
        }
        System.out.println("What would you like to name your " + accountType + " account? (alphanumeric no spaces)");
        String accountName = nextLine();
        customer.requestAccount(accountType, accountName);
        System.out.println("Request sent!");
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
        if (account instanceof ChequingAcc) {
            new ChequingMenu(account, this).showMenu();
        }
        else if(account instanceof SavingAcc){
            new SavingsMenu(account, this).showMenu();
        }
        else if(account instanceof CreditCardAcc){
            new CreditCardMenu(account, this).showMenu();
        }
        else if(account instanceof CreditLineAcc){
            new CreditLineMenu(account, this).showMenu();
        }
    }
}
