import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment6 {

    //6.2 Modify the parse() method
    public static void parse(File file) throws IOException {
        RandomAccessFile input = null;
        String line = null;

        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        //6.1 Test MyIndexOutOfBoundException
        System.out.println("-----Assignment6.1-----");
        try{
            int[] ints = new int[]{1,2,3,4,5};
            for (int i = 0;i<10;i++){
                if(i>ints.length-1)
                    throw new MyIndexOutOfBoundException(0,ints.length-1,i);
                else
                    System.out.print(ints[i] + "   ");
            }
        }catch (MyIndexOutOfBoundException e){
            System.out.println();
            System.out.println(e);
        }

        //6.3 ATM machine
        System.out.println("-----Assignment6.3-----");
        System.out.println("Choose to use which kind of Atm:\n'1' --> Atm with dataStructure;\nothers --> Atm with file");
        Scanner scanner = new Scanner(System.in);
        String in = scanner.next();
        Atm atm;
        if(in.equals("1")){
            List<User> users = new ArrayList<>();
            User Luna = new User("Luna", "1992", "111-222-3333", "123456");
            User Cassie = new User("Cassie", "1995", "888-666-2222", "654321");
            users.add(Luna);
            users.add(Cassie);
            atm = new Atm(1000,2.0,users);
            System.out.println("---Welcome to use Atm with dataStructure!---");
        }else {
            //extra credit
            atm = new AtmByReadFromFile(1000, 2.0, new File("userData.txt"));
            System.out.println("---Welcome to use Atm with file!---");
        }
        while (true) {
            /*start*/
            System.out.println("\nWelcome!\n'Q' --> exit\nothers --> start!");
            in = scanner.next();
            if(in.equalsIgnoreCase("Q")){
                break;
            }
            boolean isCurrentUser = atm.isCurrentUser();

            /*add user*/
            if (!isCurrentUser) {
                System.out.println("\n---New User---\n'Q' --> exit\nothers --> create your account!");
                in = scanner.next();
                if(!in.equalsIgnoreCase("Q")) {
                    System.out.println("Please enter your name:");
                    String name = scanner.next();
                    System.out.println("Please enter your year of birth:");
                    String yearOfBirth = scanner.next();
                    System.out.println("Please enter your Phone Number:");
                    String phoneNumber = scanner.next();
                    System.out.println("Please enter your password:");
                    String password = scanner.next();
                    atm.addUser(name, yearOfBirth, phoneNumber, password);
                }
            }

            /*current user*/
            else{
                boolean isLogin  = false;

                while(!isLogin){
                    System.out.println("\n---Current User---\n'Q' --> exit\n'F' --> reset password if you forget it\nothers --> login!");
                    in = scanner.next();
                    if(in.equalsIgnoreCase("Q")){
                        break;
                    }else if(in.equalsIgnoreCase("F")){
                        /*forget password*/
                        boolean validate =false;
                        while (!validate) {
                            System.out.println("\n****forget password****");
                            System.out.println("Please enter your bank Account Number:");
                            String bankAccountNumber = scanner.next();
                            System.out.println("Please enter your name:");
                            String name = scanner.next();
                            System.out.println("Please enter your year of birth:");
                            String yearOfBirth = scanner.next();
                            System.out.println("Please enter your Phone Number:");
                            String phoneNumber = scanner.next();
                            System.out.println("Please enter your new password:");
                            String password = scanner.next();
                            validate = atm.forgotPassword(bankAccountNumber,name,yearOfBirth,phoneNumber,password);
                            if(!validate){
                                System.out.println("Validate failed!!\n'Q' --> exit\nothers --> try again!");
                                in = scanner.next();
                                if(in.equalsIgnoreCase("Q")){
                                    break;
                                }
                            }
                        }
                    }else{
                        /*login*/
                        System.out.println("\n****LOGIN****");
                        System.out.println("Please enter your bank Account Number:");
                        String bankAccountNumber = scanner.next();
                        System.out.println("Please enter your password:");
                        String password = scanner.next();
                        isLogin = atm.login(bankAccountNumber,password);

                        while (isLogin) {
                            /*After loggin the user should be able to use availableBalance, withDrawal, deposit, recentTransactions, changePassword and exit*/
                            System.out.println("\n****choose operation****\n" +
                                    "'1' --> available balance.\n" +
                                    "'2' --> withdrawal.\n" +
                                    "'3' --> deposit.\n" +
                                    "'4' --> recent transactions.\n" +
                                    "'5' --> change password.\n" +
                                    "others --> exit!");
                            in = scanner.next();
                            if(in.equalsIgnoreCase("1")) {
                                System.out.println("Your available balance:" + atm.availableBalance());
                            }else if(in.equalsIgnoreCase("2")){
                                System.out.println("press amount you want to withdrawal:");
                                double money = scanner.nextDouble();
                                atm.withDrawal(money);
                            }else if(in.equalsIgnoreCase("3")){
                                System.out.println("press amount you want to deposit:");
                                double money = scanner.nextDouble();
                                atm.deposit(money);
                            }else if(in.equalsIgnoreCase("4")){
                                System.out.println("Your recent transactions:\n"+atm.recentTransactions());
                            }else if(in.equalsIgnoreCase("5")){
                                System.out.println("please enter your old password:");
                                String oldPassword = scanner.next();
                                System.out.println("please enter your new password:");
                                String newPassword = scanner.next();
                                atm.changePassword(oldPassword,newPassword);
                            }else{
                                atm.exit();
                                isLogin = false;
                            }
                        }
                    }
                }
            }
        }
        scanner.close();
    }
}
