package snakeparty;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import ocsf.client.AbstractClient;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class CreateAccountPanel implements ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private JTextField jtUsername;
    private JPasswordField passwordField;
    private JPasswordField verifypasswordField;
    private JButton createAccountBtn;
    private JButton returnToLoginBtn;
    private LoginPanel loginPanel;
    private AbstractClient client;

    public CreateAccountPanel(JFrame frame, AbstractClient client) {
        this.frame = frame;
        this.client = client;
        this.loginPanel = new LoginPanel(frame, client);
        this.initializePanel();
    }

    void initializePanel() {
        System.out.print("!!! initializing createAccountPanel");
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblCreateAccounttitle = new JLabel("Create Account");
        lblCreateAccounttitle.setBounds(102, 6, 240, 53);
        lblCreateAccounttitle.setFont(new Font("Kohinoor Gujarati", Font.PLAIN, 35));
        panel.add(lblCreateAccounttitle);

        JLabel lblNewLabel = new JLabel("Enter Username");
        lblNewLabel.setBounds(53, 109, 98, 16);
        panel.add(lblNewLabel);

        jtUsername = new JTextField();
        jtUsername.setBounds(178, 104, 160, 26);
        panel.add(jtUsername);
        jtUsername.setColumns(10);

        JLabel lblEnterPassword = new JLabel("Enter Password");
        lblEnterPassword.setBounds(53, 147, 95, 16);
        panel.add(lblEnterPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(178, 142, 160, 26);
        panel.add(passwordField);

        JLabel lblVerifyPassword = new JLabel("Verify Password");
        lblVerifyPassword.setBounds(53, 175, 99, 16);
        panel.add(lblVerifyPassword);

        verifypasswordField = new JPasswordField();
        verifypasswordField.setBounds(178, 170, 160, 26);
        panel.add(verifypasswordField);

        createAccountBtn = new JButton("Create Account");
        createAccountBtn.setBounds(165, 206, 140, 29);
        createAccountBtn.addActionListener(this);
        panel.add(createAccountBtn);
        
        returnToLoginBtn = new JButton("Back");
        returnToLoginBtn.setBounds(197, 247, 75, 29);
        returnToLoginBtn.addActionListener(this);
        panel.add(returnToLoginBtn);
        
    }

    void display() {
        System.out.println("Displaying createAccountPanel");
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.validate();
        frame.repaint();
    }
    
    public void success() {
        loginPanel.display();
    }
    
    public void failure() {
        this.display();
    }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == createAccountBtn) {
          CreateAccountData createAccountData = new CreateAccountData(jtUsername.getText(), passwordField.getText());
            try {
                client.sendToServer(createAccountData);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        
        if (source == returnToLoginBtn) {
            InitialPanel initialPanel = new InitialPanel(frame, client);
            initialPanel.display();
        }
            
    }

}
