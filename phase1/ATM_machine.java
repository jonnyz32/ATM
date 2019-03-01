import javax.xml.soap.Text;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class ATM_machine extends TextInterface{

    public static ArrayList<ATM_User> users = new ArrayList<ATM_User>();

    //protected file_manager FileManager;
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Input your username");
        String user = s.nextLine();
        System.out.println("Input your password");
        String pass = s.nextLine();
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername() == user && users.get(i).getPassword() == pass){
                System.out.println("Success!");
                user.get(i).showMenu();
            }
        }
    }





    public void sendAlert(){} // stores a warning that the amount of a certain bill is too low in a text file, alerts.txt

    public void getTime(){}

    public void setTime(){}
}
