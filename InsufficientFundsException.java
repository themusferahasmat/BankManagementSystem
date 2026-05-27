package bank;

// Custom Exception - thrown when withdrawal amount exceeds balance
public class InsufficientFundsException extends Exception {

    public InsufficientFundsException(double amount) {
        super("Insufficient funds! You tried to withdraw Rs. " + amount +
              " but your balance is too low.");
    }
}
