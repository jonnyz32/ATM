import java.io.Serializable;

public class AccountRequest implements Serializable {
    private final String user;
    private final String type;
    private final String name;

    public AccountRequest(String user, String type, String name) {
        this.user = user;
        this.type = type;
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
