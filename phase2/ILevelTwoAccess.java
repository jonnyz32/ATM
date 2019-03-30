import java.util.List;

public interface ILevelTwoAccess extends ILevelOneAccess {
    List<AccountCreationRequest> getRequests();

    void setSystemDate(int year, int month, int day);

    void undoTransaction(String username, String account, int n_trans) throws BadInputException;

    void approveAccount(int id) throws BadInputException;

    }
