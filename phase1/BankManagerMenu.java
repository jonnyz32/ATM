import java.util.List;

public class BankManagerMenu extends TextInterface{

    private BankManager bankManager;

    public BankManagerMenu(BankManager bankManager, TextInterface previous){
        super(previous);
        this.bankManager = bankManager;
        header = "Bank Manager Menu";
        footer = "Choose an option:";
        addAction(1, ()->setSystemDate(), "Set system date");
        addAction(2, ()->addBills(), "Restock bills");
        addAction(3, ()->undoTransaction(), "Undo a customer transaction");
        addAction(4, ()->approveAccount(), "Approve an account request");
        addAction(5, ()->createNewCustomer(), "Create a new customer");
    }

    private void setSystemDate(){
        System.out.println("Input year:");
        int year = Integer.parseInt(nextLine());
        System.out.println("Input month:");
        int month = Integer.parseInt(nextLine());
        System.out.println("Input day:");
        int day = Integer.parseInt(nextLine());
        bankManager.setSystemDate(year, month, day);
        showMenu();
    }

    private void addBills(){
        System.out.println("What kind?");
        int type = Integer.parseInt(nextLine());
        System.out.println("How many?");
        int num = Integer.parseInt(nextLine());
        if(bankManager.addBills(type, num)==-1){
            System.out.println("ERROR: Invalid input");
        }
        showMenu();
    }

    private void undoTransaction(){
        System.out.println("Input target user:");
        String username = nextLine();
        System.out.println("Input account name:");
        String account = nextLine();
        bankManager.undoTransaction(username, account);
        showMenu();
    }

    private void approveAccount(){
        List<Pair<String,String>> requests = BankManager.getRequests();
        int i = 0;
        for(Pair<String, String> request: requests){
            System.out.println(i + ": " + request.getLeft() + " requests a " + request.getRight() + " account");
            i++;
        }
        System.out.println("Input id to approve:");
        int target = Integer.parseInt(nextLine());
        bankManager.approveAccount(target);
        showMenu();
    }

    private void createNewCustomer(){
        System.out.println("Input username:");
        String username = nextLine();
        System.out.println("Input password:");
        String password = nextLine();
        bankManager.createNewCustomer(username, password);
        showMenu();
    }
}
