package by.toxa.fishingshop._main;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.ManufactureType;
import by.toxa.fishingshop.entity.Product;
import by.toxa.fishingshop.entity.ProductType;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.EnumSet;


@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class EditNewProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/editProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vendor = req.getParameter("vendor");
        String manufacture = req.getParameter("manufacture");
        String type = req.getParameter("type");
        String description = req.getParameter("description");
        byte[] imageData;
        try (OutputStream outputStream = new ByteArrayOutputStream()) {
            for (Part part : req.getParts()) {
                outputStream.write(part.getInputStream().readAllBytes());
            }
            imageData = ((ByteArrayOutputStream) outputStream).toByteArray();
        }

        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(req.getParameter("price")));
        int numberInStock = Integer.parseInt(req.getParameter("numberInStock"));
        Product product = new Product.ProductBuilder()
                .setVendor(vendor)
                .setManufacture(ManufactureType.valueOf(manufacture.toUpperCase()))
                .setType(ProductType.valueOf(type.toUpperCase()))
                .setDescription(description)
                .setImage(imageData)
                .setPrice(price)
                .setNumberInStock(numberInStock)
                .build();
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (vendor, manufacture_id, type_id, description, image, price, number_in_stock)" +
                    " VALUES (?,(SELECT id FROM product_manufacture WHERE manufacture = ?),(SELECT id FROM product_types WHERE type = ?),?,?,?,?);");
            statement.setString(1,product.getVendor());
            statement.setString(2,product.getManufacture().getValue());
            statement.setString(3,product.getProductType().getType());
            statement.setString(4,product.getDescription());
            statement.setBytes(5,product.getImage());
            statement.setBigDecimal(6,price);
            statement.setInt(7,product.getNumberInStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        CustomConnectionPool.getInstance().destroyPool();
    }
}
