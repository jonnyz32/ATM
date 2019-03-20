import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class EmployeeTest {
    Employee e1;

    @Before
    public void setUp() throws Exception {
        e1 = new Employee("employee1", "pass1");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addBills(){
        int[] bills = new int[]{ATM_machine.getNumFives(), ATM_machine.getNumTens(), ATM_machine.getNumTwenties(),
        ATM_machine.getNumFifties()};
        e1.addBills(20, 17);
        e1.addBills(10, 0);
        e1.addBills(5,100000);
        assertArrayEquals(new int[]{bills[0] + 100000, bills[1], bills[2] + 17, bills[3]}, new
                int[]{ATM_machine.getNumFives(), ATM_machine.getNumTens(), ATM_machine.getNumTwenties(),
                ATM_machine.getNumFifties()});

    }

    @Test
    public void createNewCustomer(){
        ATM_machine machine = new ATM_machine();
        e1.createNewCustomer("customer1","pass1");
        assertEquals("customer1", machine.getUser("customer1").getUsername());
    }

    @Test
    public void requestAccount(){
        e1.requestAccount("chequing", "C");
        assertFalse(BankManager.getRequests().isEmpty());
    }
}