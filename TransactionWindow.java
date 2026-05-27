package bank;

import java.awt.*;
import java.awt.event.*;

// TransactionWindow - handles both Deposit and Withdraw
public class TransactionWindow extends Frame implements ActionListener 
{

    private BankDatabase database;
    private String transactionType;
    private TextField txtId, txtAmount;
    private Button btnSubmit, btnCancel;
    private Label lblMessage;

    public TransactionWindow(BankDatabase database, String transactionType) 
    {
        this.database        = database;
        this.transactionType = transactionType;
        setupWindow();
    }

    private void setupWindow() 
    {
        setTitle(transactionType + " Money");
        setSize(350, 250);
        setLayout(new GridLayout(5, 2, 10, 10));
        setBackground(new Color(240, 248, 255));

        add(new Label("Account ID:"));
        txtId = new TextField();
        add(txtId);

        add(new Label("Amount (Rs.):"));
        txtAmount = new TextField();
        add(txtAmount);

        btnSubmit = new Button(transactionType);
        btnSubmit.addActionListener(this);
        btnCancel = new Button("Cancel");
        btnCancel.addActionListener(this);
        add(btnSubmit);
        add(btnCancel);

        lblMessage = new Label("", Label.CENTER);
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
            try {
                int accountId = Integer.parseInt(txtId.getText().trim());
                double amount = Double.parseDouble(txtAmount.getText().trim());
                Account acc   = database.findAccount(accountId);

                if (acc == null) 
                {
                    lblMessage.setForeground(Color.RED);
                    lblMessage.setText("Account not found!");
                    return;
                }

                if (transactionType.equals("Deposit")) 
                {
                    acc.deposit(amount);
                    lblMessage.setForeground(new Color(0, 128, 0));
                    lblMessage.setText("Deposited! New Balance: Rs." + acc.getBalance());
                } 
                else 
                {
                    acc.withdraw(amount); // may throw InsufficientFundsException
                    lblMessage.setForeground(new Color(0, 128, 0));
                    lblMessage.setText("Withdraw Successful! New Balance: Rs." + acc.getBalance());
                }

            } catch (InsufficientFundsException ex) {
                lblMessage.setForeground(Color.RED);
                lblMessage.setText(ex.getMessage());
            } catch (NumberFormatException ex) {
                lblMessage.setForeground(Color.RED);
                lblMessage.setText("Enter valid numbers!");
            }

        } 
        else if (e.getSource() == btnCancel) 
        {
            dispose();
        }
    }
}
