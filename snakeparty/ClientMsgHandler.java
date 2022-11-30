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
        String url = "?autoReconnect=true&useSSL=false";
        String username = "user";
        String password = "password";

        try {
            driver = DriverManager.getConnection(url, username, password);
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

    public boolean verifyAccount(String username, String password) {
        return true;
    }

    public boolean createNewAccount(String username, String password) {
        return true;
    }

}
