import java.util.Scanner;

public class CustomerMenu extends TextInterface{
    private Customer customer;

    public CustomerMenu(Customer customer){
        this.customer = customer;
        addAction(1, ()->getFullSummary(), "Get account summary");
        addAction(2, ()->requestAccount(), "Request account creation");
        for(int i=0;i<accounts.size();i++) {
            final int f = i; //Because the input needs to be final.
            addAction(i+2, ()->viewAccount(f), "Account: "+accounts.get(i).name);
        }
        showMenu();
    }

    public void getFullSummary() {
        customer.getFullSummary();
    }

    public void requestAccount(){
        System.out.println("" +
                "Which type of account?"); //TODO: list their options
        String accountType = nextLine();
        customer.requestAccount(accountType);
    }
}
