package bank;

public abstract class Account implements Transactable 
{

    // Static variables - belong to the bank, not individual accounts
    private static String bankName = "NUML Bank";
    private static int accountCounter = 1000;

    // Private fields - Encapsulation (nobody can access these directly)
    private int accountId;
    private String accountHolderName;
    protected double balance;

    // Constructor - runs when a new account is created
    public Account(String accountHolderName, double initialDeposit) 
    {
        this.accountId = ++accountCounter;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    // Abstract method - every subclass MUST implement this differently
    public abstract double calculateInterest();

    // Deposit method
    public void deposit(double amount) 
    {
        balance += amount;
    }

    // Getters - controlled access to private fields
    public int getAccountId()           { return accountId; }
    public String getAccountHolderName(){ return accountHolderName; }
    public double getBalance()          { return balance; }
    public static String getBankName()  { return bankName; }

    // Returns account info as text
    public String getDetails() 
    {
        return "Account ID   : " + accountId +
               "\nHolder Name  : " + accountHolderName +
               "\nBalance      : Rs. " + balance;
    }
}
