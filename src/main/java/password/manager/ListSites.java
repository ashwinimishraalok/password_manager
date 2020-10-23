package password.manager;

import com.google.gson.JsonArray;
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

public class ListSites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("user"));
        Database database = new Database(DbUtil.url, DbUtil.user, DbUtil.password);
        ResultSet res = database.executeQuery(DbUtil.getSiteList(id));
        JsonArray arr = new JsonArray();
        try {
            while(res.next()) {
                JsonObject json = new JsonObject();
                json.add("website", new JsonPrimitive(res.getString("website")));
                json.add("username", new JsonPrimitive(res.getString("username")));
                json.add("password", new JsonPrimitive(PasswordUtil.decrypt(res.getString("password"))));
                arr.add(json);
            }
            res.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        JsonObject status = new JsonObject();
        status.add("status", new JsonPrimitive("success"));
        status.add("list", arr);
        PrintWriter out = resp.getWriter();
        out.write(status.toString());
    }
}
