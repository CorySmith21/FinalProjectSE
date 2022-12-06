package snakeparty;

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

        ArrayList<LoginData> results = query("select * from users where username = '" + username + "';");

        if (results.size() > 0) {
            String qpassword = results.get(0).getPassword();
            if (password.equals(qpassword)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Boolean createNewAccount(CreateAccountData createAccountData) {
        String username = createAccountData.getUsername();
        String password = createAccountData.getPassword();
        
        String queryString = String.format("select * from users where username = \"%s\"; ", username);
        ArrayList<LoginData> results = query(queryString);
        
        if (results.size() > 0) {
            return false;
        } else {
            String encryptedPassword = String.format("%d", password.hashCode());
            String insertString = String.format("insert into users(username, password) values (\"%s\", \"%s\");", username, encryptedPassword);
            executeDML(insertString);
            return true;
        }   
    }
}
