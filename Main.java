package bank;

public class Main 
{

    public static void main(String[] args) 
    {

        System.out.println("Welcome to " + Account.getBankName());
        System.out.println("Launching GUI...");

        new MainWindow();
    }
}
