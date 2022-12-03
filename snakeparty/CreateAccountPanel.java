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
//    public CreateAccountPanel() {
//        getContentPane().setLayout(null);
//        
//        JPanel panel = new JPanel();
//        panel.setBounds(6, 6, 634, 98);
//        getContentPane().add(panel);
//        
//        JLabel lblCreateAccounttitle = new JLabel("Create Account");
//        lblCreateAccounttitle.setFont(new Font("Kohinoor Gujarati", Font.PLAIN, 35));
//        panel.add(lblCreateAccounttitle);
//        
//        JLabel lblNewLabel = new JLabel("Enter Username");
//        lblNewLabel.setBounds(171, 135, 105, 25);
//        getContentPane().add(lblNewLabel);
//        
//        jtUsername = new JTextField();
//        jtUsername.setBounds(287, 134, 185, 26);
//        getContentPane().add(jtUsername);
//        jtUsername.setColumns(10);
//        
//        JLabel lblEnterPassword = new JLabel("Enter Password");
//        lblEnterPassword.setBounds(171, 184, 105, 25);
//        getContentPane().add(lblEnterPassword);
//        
//        passwordField = new JPasswordField();
//        passwordField.setBounds(287, 183, 185, 25);
//        getContentPane().add(passwordField);
//        
//        JLabel lblVerifyPassword = new JLabel("Verify Password");
//        lblVerifyPassword.setBounds(171, 234, 105, 25);
//        getContentPane().add(lblVerifyPassword);
//        
//        verifypasswordField = new JPasswordField();
//        verifypasswordField.setBounds(287, 233, 185, 25);
//        getContentPane().add(verifypasswordField);
//        
//        JButton btnNewButton = new JButton("Create Account");
//        btnNewButton.addActionListener(new ActionListener() {
//            private String pass;
//
//            @SuppressWarnings("deprecation")
//            public void actionPerformed(ActionEvent e) {
//                
//                    
//                    
//                    try {
//                        Class.forName("com.mysql.cj.jdbc.Driver");
//                        Connection connection = DriverManager.getConnection(
//                                "jdbc:mysql://localhost:3306/snakeparty", "root", "Testerp"
//                        );
//                        Statement statement = connection.createStatement();
//                        
//                        ResultSet resultSet = statement.executeQuery("select * from Snakeparty");
//                       
//                        Statement stm = connection.createStatement();
//                       String usern= jtUsername.getText();
//                       
//                       
//                       if( passwordField.getText() == verifypasswordField.getText()) {
//                        
//                          String pass = passwordField.getText();
//                           System.out.println(pass);
//                           
//                           String sql = "INSERT INTO Snakeparty(Username ,Password )  VALUES('"+usern+"','"+pass+"')"  ;
//                        //class5 in above queary is a table
//                        //EXECUTE STATEMENT
//                        stm.executeUpdate(sql); //here insert query apply
//                       
//                       
//                        connection.close();
//                       
//                       
//                    
//                    
//                       }else {
//                           
//                           JOptionPane.showMessageDialog(null,"Password Doesn't Match!" );
//                       }
//                       
//                       
//                      }catch(Exception ex){
//                        System.out.println(ex.getMessage());
//                    }
//                       
//            
//                        
//                   
//                }
//                  
//                    
//                
//        
//            });
//            
//
//        btnNewButton.setBounds(267, 292, 140, 29);
//        getContentPane().add(btnNewButton);
//    }

}
