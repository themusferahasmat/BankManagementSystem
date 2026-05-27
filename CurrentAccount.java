package bank;

// CurrentAccount - inherits from Account (Inheritance)
public class CurrentAccount extends Account 
{

    private double overdraftLimit = 5000; // can go Rs.5000 below zero
    private double interestRate = 0.02;   // 2% interest

    // Constructor
    public CurrentAccount(String accountHolderName, double initialDeposit) 
    {
        super(accountHolderName, initialDeposit); // calls Account constructor
    }

    // Polymorphism - own version of calculateInterest()
    @Override
    public double calculateInterest() 
    {
        return balance * interestRate;
    }

    // Withdraw with overdraft allowance
    @Override
    public void withdraw(double amount) throws InsufficientFundsException 
    {
        if (amount > balance + overdraftLimit) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
    }

    public String getAccountType() 
    {
        return "Current Account";
    }

    @Override
    public String getDetails() 
    {
        return super.getDetails() +
               "\nAccount Type : Current Account" +
               "\nOverdraft    : Rs. " + overdraftLimit +
               "\nInterest Rate: 2%" +
               "\nInterest Due : Rs. " + calculateInterest();
    }
}
