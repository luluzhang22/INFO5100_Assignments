
public class User {

    public static int bankAccountNumberGenerate = 0;

    private String name;

    private String yearOfBirth;

    private String address;

    private String phoneNumber;

    private String bankAccountNumber;

    private String password;

    private double amount;

    private String recentTransactions = "";

    User(){

    }

    User(String name, String yearOfBirth, String phoneNumber, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bankAccountNumber = ++bankAccountNumberGenerate+"";
        this.yearOfBirth = yearOfBirth;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getRecentTransactions() {
        return recentTransactions;
    }

    public void setRecentTransactions(String recentTransactions) {
        this.recentTransactions = recentTransactions;
    }

    public void addRecentTransactions(String transaction) {
        StringBuilder tran = new StringBuilder(recentTransactions);
        if(recentTransactions.isEmpty()){
            tran.append(transaction);
        }else {
            String[] transactions = recentTransactions.split("\n");
            if (transactions.length >= 10) {
                tran = tran.delete(recentTransactions.lastIndexOf("\n"), recentTransactions.length());
            }
            tran.insert(0, transaction+"\n");
        }
        recentTransactions = tran.toString();
    }
}
