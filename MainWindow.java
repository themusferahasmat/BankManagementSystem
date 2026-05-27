package bank;

import java.awt.*;
import java.awt.event.*;

public class MainWindow extends Frame implements ActionListener 
{

    private BankDatabase database;
    private Button btnCreate, btnDeposit, btnWithdraw, btnView, btnExit;
    private Label lblTitle, lblWelcome;

    public MainWindow() 
    {
        database = new BankDatabase();
        setupWindow();
    }

    private void setupWindow() 
    {
        // Window settings
        setTitle(Account.getBankName() + " - Main Menu");
        setSize(400, 350);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        setBackground(new Color(240, 248, 255));

        // Title label
        lblTitle = new Label("NUML Bank", Label.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setForeground(new Color(0, 70, 140));

        // Welcome label
        lblWelcome = new Label("Please select an option below", Label.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.PLAIN, 13));

        // Buttons
        btnCreate   = createButton("Create New Account");
        btnDeposit  = createButton("Deposit Money");
        btnWithdraw = createButton("Withdraw Money");
        btnView     = createButton("View Account Details");
        btnExit     = createButton("Exit");

        // Add everything to window
        add(lblTitle);
        add(lblWelcome);
        add(btnCreate);
        add(btnDeposit);
        add(btnWithdraw);
        add(btnView);
        add(btnExit);

        // Close button (X)
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) 
            {
                System.exit(0);
            }
        });

        setLocationRelativeTo(null); // center on screen
        setVisible(true);
    }

    // Helper method to create styled buttons
    private Button createButton(String label) 
    {
        Button btn = new Button(label);
        btn.setFont(new Font("Arial", Font.PLAIN, 13));
        btn.setPreferredSize(new Dimension(220, 35));
        btn.addActionListener(this);
        return btn;
    }

    // Button click actions
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == btnCreate) 
        {
            new CreateAccountWindow(database);
        } 
        else if (e.getSource() == btnDeposit) 
        {
            new TransactionWindow(database, "Deposit");
        } 
        else if (e.getSource() == btnWithdraw) 
        {
            new TransactionWindow(database, "Withdraw");
        } 
        else if (e.getSource() == btnView) 
        {
            new ViewAccountWindow(database);
        } 
        else if (e.getSource() == btnExit) 
        {
            System.exit(0);
        }
    }
}