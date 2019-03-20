import java.util.List;

public interface IBankManager {
    boolean setSystemDate(int year, int month, int day);

    void undoTransaction(String username, String account);

    boolean approveAccount(int id);

    }
