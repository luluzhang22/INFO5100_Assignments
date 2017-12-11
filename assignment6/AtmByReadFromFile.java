/* Good WOrk
 * Score 10 + extra credit 2; Total score 10
 */
import java.io.*;

public class AtmByReadFromFile extends Atm{

    private enum FileFormat {
        BANK(0), NAME(1), YEAROFBIRTH(2), PHONENUMBER(3),
        PASSWORD(4), AMOUNT(5), RECENTTRAN(6);

        int index;

        FileFormat(int index) {
            this.index = index;
        }

    }
    File userData;
    private static int bankAccountNumberGenerate = 0;
    private final String RECENT_TRANSACTION = "no recent transactions";

    AtmByReadFromFile(double availableAmountInMachine, double transactionFee, File file) throws IOException {
        super(availableAmountInMachine,transactionFee,null);
        userData = file;
        RandomAccessFile input = new RandomAccessFile(userData, "r");
        while (input.readLine()!=null)
            bankAccountNumberGenerate++;
        input.close();
    }

    @Override
    public void addUser(String name, String yearOfBirth, String phoneNumber, String password){
        Writer output = null;
        try {
            output = new BufferedWriter(new FileWriter(userData, true));
            output.append("\n"+(++bankAccountNumberGenerate)+","+name+","+yearOfBirth+","+phoneNumber+","+password+",0,"+RECENT_TRANSACTION);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Create new User successfully! Your bank account number is: "+bankAccountNumberGenerate);
    }

    @Override
    public boolean login(String bankAccountNumber, String password){
        String line;
        try {
            line = findBankAccount(bankAccountNumber);
            if(line==null) {
                System.out.println("This account dose not exist! Please try login again!");
                return false;
            }
            String pw = line.split(",")[FileFormat.PASSWORD.index];
            if (password.equals(pw)){
                loginedUser = covertFileToUser(line);
                System.out.println("Successful login! Current user:"+loginedUser.getName());
                return true;
            }else{
                System.out.println("Wrong password! Please try login again!");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String findBankAccount(String bankAccountNumber) throws IOException {
        RandomAccessFile input = null;
        try {
            input = new RandomAccessFile(userData, "r");
            String line;
            while ((line = input.readLine()) != null) {
                String bank = line.split(",")[FileFormat.BANK.index];
                if(bankAccountNumber.equals(bank)) {
                    return line;
                }
            }
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }

    @Override
    public boolean forgotPassword(String bankAccountNumber, String name, String yearOfBirth, String phoneNumber, String password){
        RandomAccessFile input;
        StringBuilder lines = new StringBuilder();
        String line;
        boolean find = false;
        try {
            input = new RandomAccessFile(userData, "r");
            while ((line = input.readLine()) != null) {
                if(!find) {
                    String bank = line.split(",")[FileFormat.BANK.index];
                    if (bankAccountNumber.equals(bank)) {
                        String[] user = line.split(",");
                        if (!user[FileFormat.NAME.index].equals(name) || !user[FileFormat.YEAROFBIRTH.index].equals(yearOfBirth)
                                || !user[FileFormat.PHONENUMBER.index].equals(phoneNumber)) {
                            System.out.println("Wrong user's information!");
                            return false;
                        }
                        find = true;
                        user[FileFormat.PASSWORD.index] = password;
                        StringBuilder sb = new StringBuilder();
                        for (String s : user) {
                            sb.append(s).append(",");
                        }
                        line = sb.substring(0, sb.length()-1).toString();
                    }
                }
                lines.append(line).append("\n");
            }
            if(!find){
                System.out.println("This account dose not exist!");
                return false;
            }
            input.close();
            if(lines.length()!=0) {
                Writer output = new BufferedWriter(new FileWriter(userData));
                output.write(lines.substring(0,lines.length()-1).toString());
                output.close();
            }
            System.out.println("Reset password successfully!");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void exit(){
        if(loginedUser == null) {
            System.out.println("Please login to start!");
            return;
        }
        RandomAccessFile input = null;
        StringBuilder lines = new StringBuilder();
        String line;
        boolean find = false;
        try {
            input = new RandomAccessFile(userData, "r");
            while ((line = input.readLine()) != null) {
                if(!find) {
                    String bank = line.split(",")[FileFormat.BANK.index];
                    if (loginedUser.getBankAccountNumber().equals(bank)) {
                        find = true;
                        line = loginedUser.getBankAccountNumber()+","+loginedUser.getName()+","+loginedUser.getYearOfBirth()+"" +
                                ","+loginedUser.getPhoneNumber()+","+loginedUser.getPassword()+"," + loginedUser.getAmount()+
                                ","+(loginedUser.getRecentTransactions().isEmpty() ? RECENT_TRANSACTION : loginedUser.getRecentTransactions().replaceAll("\n","@"));
                    }
                }
                lines.append(line).append("\n");
            }
            if(!find){
                System.out.println("exit failed!");
                return;
            }
            input.close();
            if(lines.length()!=0) {
                Writer output = new BufferedWriter(new FileWriter(userData));
                output.write(lines.substring(0,lines.length()-1).toString());
                output.close();
            }
            loginedUser = null;
            System.out.println("exit successfully!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User covertFileToUser(String line){
        User user = new User();
        String[] attributes = line.split(",");
        user.setBankAccountNumber(attributes[FileFormat.BANK.index]);
        user.setName(attributes[FileFormat.NAME.index]);
        user.setYearOfBirth(attributes[FileFormat.YEAROFBIRTH.index]);
        user.setPhoneNumber(attributes[FileFormat.PHONENUMBER.index]);
        user.setPassword(attributes[FileFormat.PASSWORD.index]);
        user.setAmount(Double.parseDouble(attributes[FileFormat.AMOUNT.index]));
        user.setRecentTransactions(attributes[FileFormat.RECENTTRAN.index].equals(RECENT_TRANSACTION)? "" : attributes[FileFormat.RECENTTRAN.index].replaceAll("@","\n"));
        return user;
    }
}
