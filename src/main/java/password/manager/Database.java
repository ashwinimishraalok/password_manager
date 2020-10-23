package password.manager;

import java.sql.*;

public class Database {

    private String driver = "com.mysql.jdbc.Driver";
    private String url;
    private String user;
    private String password;
    private Connection connection = null;

    public Database(String driver, String url, String user, String password){
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
        makeCoonection();
    }

    public Database(String url, String user, String password){
        this.url = url;
        this.user = user;
        this.password = password;
        makeCoonection();
    }

    protected void makeCoonection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String query){
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int executeUpdate(String query){
        try {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
