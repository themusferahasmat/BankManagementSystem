package bank;

public interface Transactable 
{
	void deposit(double amount);
    void withdraw(double amount) throws InsufficientFundsException;
}
