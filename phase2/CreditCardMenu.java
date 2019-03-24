public class CreditCardMenu extends AccountMenu{

    public CreditCardMenu(GenericAccount account, TextInterface previous){
        super(account, previous);
        addAction(1, ()->showBalance(), "View Balance");
        addAction(2, ()->getLastTransaction(), "See Last Transaction");
        addAction(3, ()->depositFromFile(), "Deposit from file");
    }
}
