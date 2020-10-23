package password.manager;

public class DbUtil {

    public final static String user = "root";
    public static final String password = "asdf0987";
    public static final String url = "jdbc:mysql://localhost:3306/password_manager";

    public static String getRegisterUserQuery(String username, String password){
        return String.format("Insert into USER (username, password) values ('%s', '%s')", username, password);
    }

    public static String getUserId(String username, String password){
        return String.format("SELECT id FROM USER where username='%s' AND password='%s'", username, password);
    }

    public static String getSiteList(int user){
        return "SELECT website, username, password FROM password_manager.password where user_id=" + user;
    }

    public static String getStoreSiteQuery(int id, String website, String username, String password){
        return String.format("Insert into password (user_id, website, username, password) values (%d, '%s', '%s', '%s')", id, website, username, password);
    }
}
