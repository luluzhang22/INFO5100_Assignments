
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Atm {
    private double availableAmountInMachine;
    private double transactionFee;
    private Map<String, User> userData = new HashMap<>();//store bankAccountNumber and user(user has password attribute)
    protected User loginedUser;

    Atm(double availableAmountInMachine, double transactionFee, List<User> users){
        this.availableAmountInMachine = availableAmountInMachine;
        this.transactionFee = transactionFee;
        if(users != null){
            for(User user : users){
                if(userData.containsKey(user.getBankAccountNumber())){
                    System.out.println(user.getName()+"'s bank account number: "+user.getBankAccountNumber()+" is already exist! Cannot add this user on this machine!");
                }
                userData.put(user.getBankAccountNumber(),user);
            }
        }
    }

    //Ask for NEW USER or CURRENT USER as the start
    public boolean isCurrentUser(){
        System.out.println("New user please type 'N', current user please type 'C!");
        Scanner scanner = new Scanner(System.in);
        String userType = scanner.next();
        if (userType.equalsIgnoreCase("N")) {
            return false;
        }else if (userType.equalsIgnoreCase("C")){
            return true;
        }else {
            return isCurrentUser();
        }
    }

    //Create a NEW USER with a unique bankAccountNumber and password.
    public void addUser(String name, String yearOfBirth, String phoneNumber, String password){
        User user = new User(name,yearOfBirth,phoneNumber,password);
        userData.put(user.getBankAccountNumber(),user);
        System.out.println("Create new User successfully! Your bank account number is: "+user.getBankAccountNumber());
    }

    //CURRENT USER should be able to login using bankAccountNumber and password.
    public boolean login(String bankAccountNumber, String password){
        if(!userData.containsKey(bankAccountNumber)){
            System.out.println("This account dose not exist! Please try login again!");
            return false;
        }else{
            User user = userData.get(bankAccountNumber);
            if(!user.getPassword().equals(password)){
                System.out.println("Wrong password! Please try login again!");
                return false;
            }else{
                loginedUser = user;
                System.out.println("Successful login! Current user:"+loginedUser.getName());
                return true;
            }
        }
    }

    //PASSWORD can be resetted by forgotPassword the name, age and phoneNumber of the user.
    public boolean forgotPassword(String bankAccountNumber, String name, String yearOfBirth, String phoneNumber, String password){
        if(!userData.containsKey(bankAccountNumber)){
            System.out.println("This bank account dose not exist!");
            return false;
        }
        User user = userData.get(bankAccountNumber);
        if(!user.getName().equals(name)||!user.getYearOfBirth().equals(yearOfBirth)||!user.getPhoneNumber().equals(phoneNumber)){
            System.out.println("Wrong user's information!");
            return false;
        }
        user.setPassword(password);
        System.out.println("Reset password successfully!");
        return true;
    }

    public double availableBalance(){
        if(loginedUser == null) {
            System.out.println("Please login to start!");
            return 0;
        }
        return loginedUser.getAmount();
    }

    public void withDrawal(double money){
        if(loginedUser == null) {
            System.out.println("Please login to start!");
            return;
        }
        double withdrawal = money + transactionFee;
        double userBalance = availableBalance();
        if(money > availableAmountInMachine) {
            System.out.println("This machine does not have enough money!");
        }else if(withdrawal > userBalance){
            System.out.println("Your account does not have enough money!");
        }else{
            loginedUser.setAmount(userBalance-withdrawal);
            availableAmountInMachine = availableAmountInMachine - money;
            loginedUser.addRecentTransactions("withdrawal - "+money+" - "+availableBalance());
            System.out.println("Withdrawal $" + money + " successfully, transaction fee is $"+ transactionFee+" ! Your current balance:"+availableBalance());
        }
    }

    public void deposit(double money){
        if(loginedUser == null) {
            System.out.println("Please login to start!");
            return;
        }
        if(money<=transactionFee){
            System.out.println("Deposit amount should bigger than $"+transactionFee);
            return;
        }
        loginedUser.setAmount(loginedUser.getAmount() + money - transactionFee);
        availableAmountInMachine = availableAmountInMachine + money;
        loginedUser.addRecentTransactions("deposit - "+money + " - " + availableBalance());
        System.out.println("Deposit $" + money + " successfully, transaction fee is $"+ transactionFee+" !Your current balance:"+availableBalance());
    }

    public String recentTransactions(){
        if(loginedUser == null) {
            System.out.println("Please login to start!");
            return "";
        }
        return loginedUser.getRecentTransactions().isEmpty()?"No recent transactions":"type - amount - balance\n"+loginedUser.getRecentTransactions();
    }

    public void changePassword(String oldPassword, String newPassword){
        if(loginedUser == null) {
            System.out.println("Please login to start!");
            return;
        }
        if(!loginedUser.getPassword().equals(oldPassword)) {
            System.out.println("Wrong old password!");
        }else{
            System.out.println("Change password successfully!");
            loginedUser.setPassword(newPassword);
        }
    }

    public void exit(){
        loginedUser = null;
    }
}
