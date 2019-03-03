import javax.xml.soap.Text;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

public class ATM_machine extends TextInterface{

    public static ArrayList<ATM_User> users = new ArrayList<ATM_User>();
    private int numFifties = 100;
    private int numTwenties = 250;
    private int numTens = 500;
    private int numFives = 1000;

    public Date date = new Date();

    //protected file_manager FileManager;
    public static void main (String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Input your username");
        String user = s.nextLine();
        System.out.println("Input your password");
        String pass = s.nextLine();
        for (int i = 0; i < users.size(); i++){
            if(users.get(i).getUsername().equals(user) && users.get(i).getPassword().equals(pass)){
                System.out.println("Success!");
                users.get(i).showMenu();
            }
        }
    }

    public void checkForAlert(){
        try{
            FileWriter writer = new FileWriter("alerts.txt");

            if (getNumFifties() < 20){
                writer.write("numFifties has fallen below 20. Current number: "+getNumFifties());
            }
            if (getNumTwenties() < 20){
                writer.write("numTwenties has fallen below 20. Current number: "+getNumTwenties());
            }
            if (getNumTens() < 20){
                writer.write("numTens has fallen below 20. Current number: "+getNumTens());
            }
            if (getNumFives() < 20) {
                writer.write("numFives has fallen below 20. Current number: "+getNumFives());
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Date getTime(){return date;}

    public void setTime(Date newDate){this.date = newDate;}

    public int getNumFifties(){return numFifties;}

    public int getNumTwenties(){return numTwenties;}

    public int getNumTens(){return numTens;}

    public int getNumFives(){return numFives;}

    public void setFifties(int numBills){
        numFifties = numBills;
    }

    public void setTwenties(int numBills){
        numTwenties = numBills;
    }

    public void setTens(int numBills){
        numTens = numBills;
    }

    public void setFives(int numBills){
        numFives = numBills;
    }
}
