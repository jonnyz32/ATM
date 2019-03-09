import java.util.Calendar;

public class MainMenu extends TextInterface{
    public MainMenu(){
        super();
        addAction(1, ()->login(), "Login to an account");
    }

    private void login(){
        System.out.println("Input username:");
        String username = nextLine();
        System.out.println("Input password:");
        String password = nextLine();
        ATM_User user = ATM_machine.getUser(username);
        if (user!= null &&
                user.getPassword() == password){
            if (user instanceof Customer) {
                new CustomerMenu((Customer) user).showMenu();
            }
            else if (user instanceof BankManager) {
                new BankManagerMenu((BankManager) user).showMenu();
            }
        }
    }

    @Override
    public void exit() {
        ATM_machine.exit();
        active = false;
    }
}
