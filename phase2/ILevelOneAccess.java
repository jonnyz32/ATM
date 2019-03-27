public interface ILevelOneAccess extends IUser{

    void addBills(int fives, int tens, int twenties, int fifties);

    void createNewCustomer(String username, String password);
}
