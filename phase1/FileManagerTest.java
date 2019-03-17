import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class FileManagerTest {

    @Before
    public void setUp() throws Exception {
        try {

            Writer writer = new BufferedWriter(new FileWriter(new File("phase1/users.txt")));
            writer.write("");
            writer.close();

            writer = new BufferedWriter(new FileWriter(new File("phase1/alerts.txt")));
            writer.write("");
            writer.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void checkForAlert() {
    }

    @Test
    public void retrieveUsers() {
        ATM_User user1 = new ATM_User("user1", "pass1");
        ATM_User user2 = new ATM_User("user2", "pass2");
        ArrayList<ATM_User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        FileManager.saveUsers(userList);
        ArrayList<ATM_User> retrievedUsers = FileManager.retrieveUsers();
        assertEquals("user1", retrievedUsers.get(0).getUsername());
        assertEquals("user2", retrievedUsers.get(1).getUsername());



    }

    @Test
    public void writeOutgoing() {
    }


    @Test
    public void writeAlerts() {
        ArrayList<int[]> alerts = new ArrayList<>();
        alerts.add(new int[]{5, 15});
        alerts.add(new int[]{20, 19});
        alerts.add(new int[]{50, 10});
        FileManager.writeAlerts(alerts);
        try {
            FileReader file = new FileReader(new File("phase1/deposits.txt"));
            BufferedReader reader = new BufferedReader(file);
            String line = reader.readLine();
            assertNotNull(line);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void retrieveBills() {
        int[] testBills = new int[]{14,335,23,47};
        FileManager.writeBills(testBills);
        int[] bills = FileManager.retrieveBills();
        assertArrayEquals(testBills, bills);
    }


    @Test
    public void readDeposits() {
    }
}