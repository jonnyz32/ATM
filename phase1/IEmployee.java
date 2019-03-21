public interface IEmployee extends IUser{

    default int addBills(int type, int num){
        int temp;
        if (type==5){
            temp = ATM_machine.getNumFives();
            ATM_machine.setFives(temp + num);
            return temp+num;
        }
        if (type==10){
            temp = ATM_machine.getNumTens();
            ATM_machine.setTens(temp + num);
            return temp+num;
        }
        if (type==20){
            temp = ATM_machine.getNumTwenties();
            ATM_machine.setTwenties(temp + num);
            return temp+num;
        }
        if (type==50){
            temp = ATM_machine.getNumFifties();
            ATM_machine.setFifties(temp + num);
            return temp+num;
        }
        return -1;
    }

    default boolean createNewCustomer(String username, String password){
        try {
            ATM_machine.addCustomer(username, password);
            return true;
        }
        catch(Exception e){
            return false;
        }
    }

}
