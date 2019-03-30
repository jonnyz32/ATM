public interface ILevelOneAccess extends IUser{

    void addBills(int fives, int tens, int twenties, int fifties);

    void createNewUser(String username, String password, int type);
}
