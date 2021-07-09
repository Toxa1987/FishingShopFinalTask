package by.toxa.fishingshop.controller;

import by.toxa.fishingshop.connection.CustomConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.UUID;


@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class TestServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "temp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String applicationDir = req.getServletContext().getRealPath("");
        String uploadFileDir = applicationDir + File.separator + UPLOAD_DIR + File.separator;
     /*   int counter[] = {0};
        String randFilename = null;
        for (Part part : req.getParts()) {
            try {

                if (counter[0] == 0) {
                    String path = part.getSubmittedFileName();
                    randFilename = UUID.randomUUID() + path.substring(path.lastIndexOf("."));
                    counter[0] = 1;
                }
                part.write(uploadFileDir + randFilename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Connection connection = CustomConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try (FileInputStream fileInputStream =  new FileInputStream(uploadFileDir + "/" + randFilename)){
            statement = connection.prepareStatement("UPDATE products SET image = ? where id = 1");
            statement.setBinaryStream(1, fileInputStream);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
*/
        OutputStream outputStream = new ByteArrayOutputStream();
        for (Part part : req.getParts()) {
            outputStream.write(part.getInputStream().readAllBytes());
        }
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        PreparedStatement statement = null;
        try (InputStream inputStream = new ByteArrayInputStream(((ByteArrayOutputStream) outputStream).toByteArray())) {
            statement = connection.prepareStatement("UPDATE products SET image = ? where id = 1");
            statement.setBinaryStream(1, inputStream);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);

        }
    }

    @Override
    public void destroy() {
        CustomConnectionPool.getInstance().destroyPool();
    }
}
