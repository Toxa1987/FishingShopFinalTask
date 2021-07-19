package by.toxa.fishingshop._main;

import by.toxa.fishingshop.connection.CustomConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Base64;

public class ImageTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection =CustomConnectionPool.getInstance().getConnection();
        try {
            Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery("select image from products where id=1");

            if(resultSet.next()){
            byte [] imageData =resultSet.getBytes("image");
                            String encode = Base64.getEncoder().encodeToString(imageData);
            req.setAttribute("image",encode);
            }
            req.getRequestDispatcher("/index.jsp").forward(req,resp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        CustomConnectionPool.getInstance().destroyPool();
    }
}
