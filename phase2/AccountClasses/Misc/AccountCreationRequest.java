package AccountClasses.Misc;

import java.io.Serializable;

public class AccountCreationRequest implements Serializable {
    private final String user;
    private final String type;
    private final String name;

    public AccountCreationRequest(String user, String type, String name) {
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
