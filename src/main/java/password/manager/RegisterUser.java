package password.manager;
 
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class RegisterUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = PasswordUtil.encrypt(req.getParameter("password"));

        //TODO check if username already exists
        Database database = new Database(DbUtil.url, DbUtil.user, DbUtil.password);
        String query = DbUtil.getRegisterUserQuery(username, password);
        System.out.println(query);
        database.executeUpdate(query);

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        JsonObject status = new JsonObject();
        status.add("status", new JsonPrimitive("account created"));
        PrintWriter out = resp.getWriter();
        out.write(status.toString());
    }

}
