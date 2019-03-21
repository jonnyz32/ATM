public interface IBankManager extends IEmployee {
    boolean setSystemDate(int year, int month, int day);

    void undoTransaction(String username, String account);

    boolean approveAccount(int id);

    }
