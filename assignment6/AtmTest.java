
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AtmTest {
    Atm atm;
    @Before
    public void setUp() throws Exception {
        User.bankAccountNumberGenerate = 0;
        List<User> users = new ArrayList<>();
        User Luna = new User("Luna", "1992", "111-222-3333", "123456");
        User Cassie = new User("Cassie", "1995", "888-666-2222", "654321");
        users.add(Luna);
        users.add(Cassie);
        atm = new Atm(1000,2.0,users);
    }

    @Test
    public void addUser() throws Exception {
        atm.addUser("lily","1885","111","111");
    }

    @Test
    public void login() throws Exception {
        String bankAccountNumber = "1";
        String password = "123456";
        boolean output = atm.login(bankAccountNumber,password);
        Assert.assertTrue(output);

        password = "11";
        output = atm.login(bankAccountNumber,password);
        Assert.assertFalse(output);

        bankAccountNumber = "3";
        password = "123456";
        output = atm.login(bankAccountNumber,password);
        Assert.assertFalse(output);
    }

    @Test
    public void forgotPassword() throws Exception {
        String bankAccountNumber = "1";
        String name = "Luna";
        String yearOfBirth = "1992";
        String phoneNumber = "111-222-3333";
        String password = "123";
        boolean output = atm.forgotPassword(bankAccountNumber,name,yearOfBirth,phoneNumber,password);
        Assert.assertTrue(output);

        phoneNumber = "222";
        output = atm.forgotPassword(bankAccountNumber,name,yearOfBirth,phoneNumber,password);
        Assert.assertFalse(output);

        bankAccountNumber = "2";
        output = atm.forgotPassword(bankAccountNumber,name,yearOfBirth,phoneNumber,password);
        Assert.assertFalse(output);
    }

    @Test
    public void availableBalance() throws Exception {
        double output = atm.availableBalance();
        double eo = 0;
        Assert.assertEquals(eo,output,0.0);

        atm.login("1","123456");
        output = atm.availableBalance();
        Assert.assertEquals(eo,output,0.0);

        atm.deposit(100);
        output = atm.availableBalance();
        eo = 98;
        Assert.assertEquals(eo,output,0.0);

        atm.withDrawal(50);
        output = atm.availableBalance();
        eo = 46;
        Assert.assertEquals(eo,output,0.0);
    }

    @Test
    public void recentTransactions() throws Exception {
        String output = atm.recentTransactions();
        String eo = "";
        Assert.assertEquals(eo,output);

        atm.login("1","123456");
        output = atm.recentTransactions();
        eo = "No recent transactions";
        Assert.assertEquals(eo,output);

        atm.deposit(100);
        output = atm.recentTransactions();
        eo = "type - amount - balance\ndeposit - 100.0 - 98.0";
        Assert.assertEquals(eo,output);

        atm.withDrawal(50);
        output = atm.recentTransactions();
        eo = "type - amount - balance\nwithdrawal - 50.0 - 46.0\ndeposit - 100.0 - 98.0";
        Assert.assertEquals(eo,output);

        for(int i = 1; i<9;i++){
            atm.deposit(i*10);
        }

        output = atm.recentTransactions();
        eo = "type - amount - balance\n" +
                "deposit - 80.0 - 390.0\n" +
                "deposit - 70.0 - 312.0\n" +
                "deposit - 60.0 - 244.0\n" +
                "deposit - 50.0 - 186.0\n" +
                "deposit - 40.0 - 138.0\n" +
                "deposit - 30.0 - 100.0\n" +
                "deposit - 20.0 - 72.0\n" +
                "deposit - 10.0 - 54.0\n" +
                "withdrawal - 50.0 - 46.0\n" +
                "deposit - 100.0 - 98.0";
        Assert.assertEquals(eo,output);

        atm.withDrawal(100);
        output = atm.recentTransactions();
        eo = "type - amount - balance\n" +
                "withdrawal - 100.0 - 288.0\n" +
                "deposit - 80.0 - 390.0\n" +
                "deposit - 70.0 - 312.0\n" +
                "deposit - 60.0 - 244.0\n" +
                "deposit - 50.0 - 186.0\n" +
                "deposit - 40.0 - 138.0\n" +
                "deposit - 30.0 - 100.0\n" +
                "deposit - 20.0 - 72.0\n" +
                "deposit - 10.0 - 54.0\n" +
                "withdrawal - 50.0 - 46.0";
        Assert.assertEquals(eo,output);
    }

    @Test
    public void changePassword() throws Exception {
        atm.login("1","123456");
        String oldPassword = "123456";
        String newPassword = "789012";
        atm.changePassword(oldPassword,newPassword);
        boolean output = atm.login("1","789012");
        Assert.assertTrue(output);

        output = atm.login("1","123456");
        Assert.assertFalse(output);
    }

}