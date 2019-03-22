public class SavingsMenu extends AccountMenu{

    public SavingsMenu(GenericAccount account, TextInterface previous){
        super(account, previous);
        addAction(1, ()->showBalance(), "View Balance");
        addAction(2, ()->getLastTransaction(), "See Last Transaction");
        addAction(3, ()->depositFromFile(), "Deposit from file");
        addAction(4, ()->transferToSelf(), "Transfer to another account");
        addAction(5, ()->transferToOther(), "Transfer to another user");
        addAction(6, ()->transferToExternal(), "Pay external bill");
        addAction(7, ()->withdraw(), "Withdraw money");
    }
}
