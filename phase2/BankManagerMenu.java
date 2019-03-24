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
        int year = nextInt();
        System.out.println("Input month:");
        int month = nextInt();
        System.out.println("Input day:");
        int day = nextInt();
        if (bankManager.setSystemDate(year, month, day)) {
            System.out.println("System time set to: " + ATM_machine.getTimeFormatted());
        }
        else{
            System.out.println("Invalid date entry");
        }
        showMenu();
    }

    private void addBills(){
        System.out.println("How many fives?");
        int fives = nextInt();
        System.out.println("How many tens?");
        int tens = nextInt();
        System.out.println("How many twenties?");
        int twenties = nextInt();
        System.out.println("How many fifties?");
        int fifties = nextInt();
        bankManager.addBills(fives, tens, twenties, fifties);
        System.out.println("Add success");
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
        List<AccountCreationRequest> requests = BankManager.getRequests();
        int i = 0;
        if (requests.size() < 1){
            System.out.println("No requests available");
            showMenu();
            return;
        }

        for(AccountCreationRequest request: requests){
            String type = request.getType();
            System.out.println(i + ": " + request.getUser() + " requests a " + type + " account");
            i++;
        }

        System.out.println("Input id to approve:");
        int target = nextInt();
        while (target>=requests.size()){
            System.out.println("Invalid id, try again.");
            target = nextInt();
        }

        if(bankManager.approveAccount(target)){
            System.out.println("Request approved");
            showMenu();
        }
        else{
            System.out.println("Error: Request not valid");
            showMenu();
        }
    }

    private void createNewCustomer(){
        System.out.println("Input username:");
        String username = nextLine();
        System.out.println("Input password:");
        String password = nextLine();
        try {
            bankManager.createNewCustomer(username, password);
            System.out.println("Account created");
        }
        catch(Exception e){
            System.out.println("Error: Account not available");
            e.printStackTrace();
        }
        showMenu();
    }
}
