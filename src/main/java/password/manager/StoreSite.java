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

public class StoreSite extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("user"));
        String username = req.getParameter("username");
        String website = req.getParameter("website");
        String password = PasswordUtil.encrypt(req.getParameter("password"));
        Database database = new Database(DbUtil.url, DbUtil.user, DbUtil.password);
        database.executeUpdate(DbUtil.getStoreSiteQuery(id, website, username, password));

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        JsonObject status = new JsonObject();
        status.add("status", new JsonPrimitive("success"));
        PrintWriter out = resp.getWriter();
        out.write(status.toString());
    }
}
