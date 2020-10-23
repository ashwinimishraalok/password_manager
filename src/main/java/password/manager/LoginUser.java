package password.manager;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = PasswordUtil.encrypt(req.getParameter("password"));

        Database database = new Database(DbUtil.url, DbUtil.user, DbUtil.password);
        ResultSet res = database.executeQuery(DbUtil.getUserId(username, password));
        int id = -1;
        try {
            while(res.next()) {
                id = res.getInt("id");
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        JsonObject status = new JsonObject();
        status.add("status", new JsonPrimitive("success"));
        status.add("userId", new JsonPrimitive(id));
        PrintWriter out = resp.getWriter();
        out.write(status.toString());
    }
}
