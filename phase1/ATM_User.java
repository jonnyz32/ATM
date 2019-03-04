//ATM_User class

import java.io.Serializable;

public class ATM_User extends TextInterface implements Serializable {

    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     *  Initializes ATM_User.
     */
    public ATM_User(String username, String password){
        super()
        this.username = username;
        this.password = password;
    }

    /**
     * Changes the user's password.
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Returns the user's password.
     */
    public String getPassword(){
        return password;
    }

    /**
     * Changes the user's password.
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Returns the user's username
     */
    public String getUsername(){
        return username;
    }

}