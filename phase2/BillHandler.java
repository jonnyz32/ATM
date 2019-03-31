class BillHandler {

    int[] calculateBillTotals(int fives, int tens, int twenties, int fifties, boolean add){
        if (!add){
            fives *= -1;
            tens *= -1;
            twenties *= -1;
            fifties *= -1;
        }

        int[] bills = new FileManager().retrieveBills();
        bills[0] = bills[0] + fives;
        bills[1] = bills[1] + tens;
        bills[2] = bills[2] + twenties;
        bills[3] = bills[3] + fifties;
        return bills;
    }

    int[] get_bill_split(int amount) {
        int fifties = amount / 50;
        amount -= fifties*50;
        int twenties = amount / 20;
        amount -= twenties*20;
        int tens = amount / 10;
        amount -= tens*10;
        int fives = amount / 5;
        return new int[] {fives, tens, twenties, fifties};
    }

    void depositBills(int fives, int tens, int twenties, int fifties){
        int[] newBills = calculateBillTotals(fives, tens, twenties, fifties, true);
        new FileManager().writeBills(newBills);
    }

    void withdrawBills(int amount){
        int[] billsToRemove = get_bill_split(amount);
        int[] newBillValues = calculateBillTotals( billsToRemove[0],
                billsToRemove[1],billsToRemove[2],billsToRemove[3], false);

        new FileManager().writeBills(newBillValues);
        new FileManager().checkForAlert();
    }
}
