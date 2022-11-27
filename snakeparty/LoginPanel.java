package snakeparty;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginPanel implements ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame;
    private JTextField jtUsername;
    private JPasswordField passwordField;
    private JButton loginBtn;
    private JButton returnToLoginBtn;
    
    public LoginPanel(JFrame frame) {
        this.frame = frame;
        this.initializePanel();
    }
    
    public void initializePanel() {
      frame.getContentPane().add(panel);
      panel.setLayout(null);
      
      JLabel lblNewLabel = new JLabel("Login");
      lblNewLabel.setBounds(174, 0, 67, 48);
      lblNewLabel.setFont(new Font("Kokonor", Font.PLAIN, 28));
      panel.add(lblNewLabel);
      
      JLabel lblNewLabel_1 = new JLabel("Username");
      lblNewLabel_1.setBounds(118, 74, 81, 16);
      panel.add(lblNewLabel_1);
      
      jtUsername = new JTextField();
      jtUsername.setBounds(211, 69, 130, 26);
      panel.add(jtUsername);
      jtUsername.setColumns(10);
      
      JLabel lblNewLabel_2 = new JLabel("Password");
      lblNewLabel_2.setBounds(118, 126, 61, 16);
      panel.add(lblNewLabel_2);
      
      passwordField = new JPasswordField();
      passwordField.setBounds(211, 121, 130, 21);
      panel.add(passwordField);
      
      loginBtn = new JButton("login");
      loginBtn.setBounds(81, 232, 140, 29);
      loginBtn.addActionListener(this);
      panel.add(loginBtn);
      
      returnToLoginBtn = new JButton("Back");
      returnToLoginBtn.setBounds(270, 232, 140, 29);
      returnToLoginBtn.addActionListener(this);
      panel.add(returnToLoginBtn);
      
    }
    
    public void display() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.validate();
        frame.repaint();
    }
    
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == loginBtn) {
            System.out.println("loging into game");
        }
        
        if (source == returnToLoginBtn) {
            System.out.println("returning to login");
        }
    }
}
