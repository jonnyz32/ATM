public interface IBankManager extends IEmployee {
    void setSystemDate(int year, int month, int day);

    void undoTransaction(String username, String account);

    void approveAccount(int id) throws IllegalArgumentException;

    }
