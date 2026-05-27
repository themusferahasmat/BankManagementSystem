package bank;

import java.awt.*;
import java.awt.event.*;

// CreateAccountWindow - form to create a new bank account
public class CreateAccountWindow extends Frame implements ActionListener 
{

    private BankDatabase database;
    private TextField txtName, txtDeposit;
    private Choice choiceType;
    private Button btnSubmit, btnCancel;
    private Label lblMessage;

    public CreateAccountWindow(BankDatabase database) 
    {
        this.database = database;
        setupWindow();
    }

    private void setupWindow() 
    {
        setTitle("Create New Account");
        setSize(380, 300);
        setLayout(new GridLayout(6, 2, 10, 10));
        setBackground(new Color(240, 248, 255));

        // Name field
        add(new Label("Account Holder Name:"));
        txtName = new TextField();
        add(txtName);

        // Initial deposit field
        add(new Label("Initial Deposit (Rs.):"));
        txtDeposit = new TextField();
        add(txtDeposit);

        // Account type dropdown
        add(new Label("Account Type:"));
        choiceType = new Choice();
        choiceType.add("Savings Account");
        choiceType.add("Current Account");
        add(choiceType);

        // Buttons
        btnSubmit = new Button("Create Account");
        btnSubmit.addActionListener(this);
        btnCancel = new Button("Cancel");
        btnCancel.addActionListener(this);
        add(btnSubmit);
        add(btnCancel);

        // Message label for feedback
        lblMessage = new Label("", Label.CENTER);
        lblMessage.setForeground(Color.RED);
        add(new Label(""));
        add(lblMessage);

        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) { dispose(); }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == btnSubmit) 
        {
            String name    = txtName.getText().trim();
            String depText = txtDeposit.getText().trim();

            // Basic validation
            if (name.isEmpty() || depText.isEmpty()) 
            {
                lblMessage.setText("Please fill in all fields!");
                return;
            }

            try 
            {
                double deposit = Double.parseDouble(depText);
                Account account;

                if (choiceType.getSelectedItem().equals("Savings Account")) 
                {
                    account = new SavingsAccount(name, deposit);
                } 
                else 
                {
                    account = new CurrentAccount(name, deposit);
                }

                database.addAccount(account);
                lblMessage.setForeground(new Color(0, 128, 0));
                lblMessage.setText("Account created! ID: " + account.getAccountId());
                txtName.setText("");
                txtDeposit.setText("");

            } catch (NumberFormatException ex) 
            {
                lblMessage.setForeground(Color.RED);
                lblMessage.setText("Enter a valid deposit amount!");
            }

        } 
        else if (e.getSource() == btnCancel) 
        {
            dispose();
        }
    }
}
