//ATM_User class

public class ATM_User{

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
    public void ATM_User(String username, String password){
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