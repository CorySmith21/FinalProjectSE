package snakeparty;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextField;



import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class CreateAccountPanel  extends JFrame {
    private JTextField jtUsername;
    private JPasswordField passwordField ;
    private JPasswordField verifypasswordField;
   
    
    public CreateAccountPanel() {
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(6, 6, 634, 98);
        getContentPane().add(panel);
        
        JLabel lblCreateAccounttitle = new JLabel("Create Account");
        lblCreateAccounttitle.setFont(new Font("Kohinoor Gujarati", Font.PLAIN, 35));
        panel.add(lblCreateAccounttitle);
        
        JLabel lblNewLabel = new JLabel("Enter Username");
        lblNewLabel.setBounds(171, 135, 105, 25);
        getContentPane().add(lblNewLabel);
        
        jtUsername = new JTextField();
        jtUsername.setBounds(287, 134, 185, 26);
        getContentPane().add(jtUsername);
        jtUsername.setColumns(10);
        
        JLabel lblEnterPassword = new JLabel("Enter Password");
        lblEnterPassword.setBounds(171, 184, 105, 25);
        getContentPane().add(lblEnterPassword);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(287, 183, 185, 25);
        getContentPane().add(passwordField);
        
        JLabel lblVerifyPassword = new JLabel("Verify Password");
        lblVerifyPassword.setBounds(171, 234, 105, 25);
        getContentPane().add(lblVerifyPassword);
        
        verifypasswordField = new JPasswordField();
        verifypasswordField.setBounds(287, 233, 185, 25);
        getContentPane().add(verifypasswordField);
        
        JButton btnNewButton = new JButton("Create Account");
        btnNewButton.addActionListener(new ActionListener() {
            private String pass;

            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {
                
                    
                    
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection(
                                "jdbc:mysql://localhost:3306/snakeparty", "root", "Testerp"
                        );
                        Statement statement = connection.createStatement();
                        
                        ResultSet resultSet = statement.executeQuery("select * from Snakeparty");
                       
                        Statement stm = connection.createStatement();
                       String usern= jtUsername.getText();
                       
                       
                       if( passwordField.getText() == verifypasswordField.getText()) {
                        
                          String pass = passwordField.getText();
                           System.out.println(pass);
                           
                           String sql = "INSERT INTO Snakeparty(Username ,Password )  VALUES('"+usern+"','"+pass+"')"  ;
                        //class5 in above queary is a table
                        //EXECUTE STATEMENT
                        stm.executeUpdate(sql); //here insert query apply
                       
                       
                        connection.close();
                       
                       
                    
                    
                       }else {
                           
                           JOptionPane.showMessageDialog(null,"Password Doesn't Match!" );
                       }
                       
                       
                      }catch(Exception ex){
                        System.out.println(ex.getMessage());
                    }
                       
            
                        
                   
                }
                  
                    
                
        
            });
            

        btnNewButton.setBounds(267, 292, 140, 29);
        getContentPane().add(btnNewButton);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   CreateAccountPanel frame = new CreateAccountPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        
        });
}}
