package snakeparty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ClientMsgHandler {
    private Connection driver;

    public ClientMsgHandler() {
        String url = "jdbc:mysql://localhost:3306/snakeparty";
        String dusername = "root";
        String dpassword = "Testerp";

        try {
            driver = DriverManager.getConnection(url, dusername, dpassword);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }

    public ArrayList<LoginData> query(String query) {
        ArrayList<LoginData> results = new ArrayList<LoginData>();
        try {
            Statement statement = driver.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                LoginData user = new LoginData(username, password);
                results.add(user);
                System.out.println(results);
            }

            return results;
           
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void executeDML(String dml) {
        try {
            Statement statement = driver.createStatement();
            statement.executeUpdate(dml);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean verifyAccount(LoginData loginData) {
        String username = loginData.getUsername();
        String password = loginData.getPassword();
        

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/snakeparty", "root", "Testerp");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Snakeparty");

            Statement stm = connection.createStatement();
            String usern = username;
            String pass = password;
            String f = usern, s = pass;

            String queryString = "SELECT * FROM Snakeparty WHERE Username = '" + usern + "';";
            ResultSet results = stm.executeQuery(queryString);

            while (results.next()) {
                String qusername = results.getString("Username");
                String qpassword = results.getString("Password");
                // String password = results.getString(""+ pass +"");

                if ((f.equals(qusername)) && (s.equals(qpassword))) {
                    System.out.println("stuck here");
                    JOptionPane.showMessageDialog(null, "Successfull Login");
                    if (JOptionPane.showConfirmDialog(null, "Confirm if you want to join game", "Snake Party",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                       // gamePanel = new GamePanel(frame, client);
                        //gamePanel.display();
                    }
               }else 
                {
                    
                   JOptionPane.showMessageDialog(null, "Unsuccessfull Login");
                   //jtUsername.setText("");
                   //passwordField.setText("");
                   // connection.close();
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return true;

        
        
        
    
        
        
        
} //return false;




    public boolean createNewAccount(CreateAccountData createAccountData) {
        String username = createAccountData.getUsername();
        String password = createAccountData.getPassword();
       
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/snakeparty", "root", "Testerp");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Snakeparty");

            Statement stm = connection.createStatement();
            String usern = username;
            String pass = password;
            String sql = "INSERT INTO Snakeparty(Username ,Password )  VALUES('" + usern + "','" + pass + "')";
            // class5 in above queary is a table
            // EXECUTE STATEMENT
            stm.executeUpdate(sql); // here insert query apply

            connection.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            
            String look = "Duplicate entry";
            boolean val = ex.getMessage().contains(look);
            if(val) {
                System.out.println("String found: "+look);
                JOptionPane.showMessageDialog(null, "Username  exist");
               
            }
            
            else 
              System.out.println("string not found");
        }
        return true;
                
        
            
      

    }

}

       

    


