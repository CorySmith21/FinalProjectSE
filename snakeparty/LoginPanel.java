package snakeparty;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginPanel extends JFrame {
    private JTextField jtUsername;
    private JPasswordField passwordField;
    public LoginPanel() {
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setBounds(174, 0, 67, 48);
        lblNewLabel.setFont(new Font("Kokonor", Font.PLAIN, 28));
        getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(118, 74, 81, 16);
        getContentPane().add(lblNewLabel_1);
        
        jtUsername = new JTextField();
        jtUsername.setBounds(211, 69, 130, 26);
        getContentPane().add(jtUsername);
        jtUsername.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Password");
        lblNewLabel_2.setBounds(118, 126, 61, 16);
        getContentPane().add(lblNewLabel_2);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(211, 121, 130, 21);
        getContentPane().add(passwordField);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
