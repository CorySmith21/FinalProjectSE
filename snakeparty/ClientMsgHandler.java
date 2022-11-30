package snakeparty;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

        String queryString = String.format("select * from Snakeparty where Username = \"%s\"; ", username);

        ArrayList<LoginData> results = query(queryString);

        if (results.size() > 0) {
            String enteredPassword = String.format("%d", password.hashCode());
            String storedPassword = results.get(0).getPassword();

            if (enteredPassword.equals(storedPassword)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean createNewAccount(String username, String password) {
        
        
        String url = "jdbc:mysql://localhost:3306/snakeparty";
            String dusername = "root";
            String dpassword = "Testerp";

            try {
                driver = DriverManager.getConnection(url, dusername, dpassword);
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
        
        
        String queryString = String.format("select * from Snakeparty where Username = \"%s\"; ", username);
        ArrayList<LoginData> results = query(queryString);

        if (results.size() > 0) {
            return false;
        } else {
            
            Statement stm = null;
            try {
                stm = driver.createStatement();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            String encryptedPassword = String.format("%d", password.hashCode());
            String insertString = "INSERT INTO Snakeparty(Username ,Password )  VALUES('" + username + "','" +  encryptedPassword + "')";
            System.out.println(insertString);
           // executeDML(insertString);
            try {
                stm.executeUpdate(insertString);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return true;
        }

    }

}
