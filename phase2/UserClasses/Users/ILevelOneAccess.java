package UserClasses.Users;

public interface ILevelOneAccess extends IUser{

    void addBills(int fives, int tens, int twenties, int fifties);

    boolean createNewUser(String username, String password, int type);
}
