package bank;

import java.awt.*;
import java.awt.event.*;

// ViewAccountWindow - displays full account details
public class ViewAccountWindow extends Frame implements ActionListener 
{

    private BankDatabase database;
    private TextField txtId;
    private TextArea txtDetails;
    private Button btnSearch, btnClose;

    public ViewAccountWindow(BankDatabase database) 
    {
        this.database = database;
        setupWindow();
    }

    private void setupWindow() 
    {
        setTitle("View Account Details");
        setSize(380, 320);
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
        setBackground(new Color(240, 248, 255));

        add(new Label("Enter Account ID:"));
        txtId = new TextField(15);
        add(txtId);

        btnSearch = new Button("Search");
        btnSearch.addActionListener(this);
        add(btnSearch);

        txtDetails = new TextArea(8, 35);
        txtDetails.setEditable(false);
        txtDetails.setFont(new Font("Courier New", Font.PLAIN, 12));
        add(txtDetails);

        btnClose = new Button("Close");
        btnClose.addActionListener(this);
        add(btnClose);

        addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent e) { dispose(); }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == btnSearch) 
        {
            try {
                int accountId = Integer.parseInt(txtId.getText().trim());
                Account acc   = database.findAccount(accountId);

                if (acc == null) 
                {
                    txtDetails.setText("No account found with ID: " + accountId);
                } 
                else 
                {
                    txtDetails.setText(acc.getDetails());
                }

            } 
            catch (NumberFormatException ex) 
            {
                txtDetails.setText("Please enter a valid Account ID!");
            }

        } 
        else if (e.getSource() == btnClose) 
        {
            dispose();
        }
    }
}
