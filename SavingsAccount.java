package bank;

// SavingsAccount - inherits from Account (Inheritance)
public class SavingsAccount extends Account 
{

    private double interestRate = 0.05; // 5% interest

    // Constructor
    public SavingsAccount(String accountHolderName, double initialDeposit) 
    {
        super(accountHolderName, initialDeposit); // calls Account constructor
    }

    // Polymorphism - own version of calculateInterest()
    @Override
    public double calculateInterest() 
    {
        return balance * interestRate;
    }

    // Withdraw with exception handling
    @Override
    public void withdraw(double amount) throws InsufficientFundsException 
    {
        if (amount > balance) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
    }

    // Account type label
    public String getAccountType() 
    {
        return "Savings Account";
    }

    @Override
    public String getDetails() 
    {
        return super.getDetails() +
               "\nAccount Type : Savings Account" +
               "\nInterest Rate: 5%" +
               "\nInterest Due : Rs. " + calculateInterest();
    }
}
