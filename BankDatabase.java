package bank;

import java.util.ArrayList;

// BankDatabase - stores and manages all accounts using ArrayList
public class BankDatabase 
{

    private ArrayList<Account> accounts = new ArrayList<>();

    // Add a new account
    public void addAccount(Account account) 
    {
        accounts.add(account);
    }

    // Find account by ID
    public Account findAccount(int accountId) 
    {
        for (Account acc : accounts) 
        {
            if (acc.getAccountId() == accountId) 
            {
                return acc;
            }
        }
        return null; // not found
    }

    // Get all accounts
    public ArrayList<Account> getAllAccounts() 
    {
        return accounts;
    }

    // Total number of accounts
    public int getTotalAccounts() 
    {
        return accounts.size();
    }
}
