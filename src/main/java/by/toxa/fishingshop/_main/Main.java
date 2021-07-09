package by.toxa.fishingshop._main;

import by.toxa.fishingshop.connection.CustomConnectionPool;
import by.toxa.fishingshop.entity.Product;
import by.toxa.fishingshop.repository.ColumnName;
import by.toxa.fishingshop.repository.impl.FindAllProductsSpecification;
import by.toxa.fishingshop.repository.impl.ProductRepositoryImpl;

import java.io.*;
import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Connection connection = CustomConnectionPool.getInstance().getConnection();
        try {
             PreparedStatement statement1 = connection.prepareStatement("UPDATE products SET image=?" +
                    "WHERE id=2;");
            InputStream stream =new FileInputStream("C:\\Users\\Toxa\\Downloads\\owner-53117-3-360x360.jpg");
             statement1.setBinaryStream(1,stream);
           statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                CustomConnectionPool.getInstance().destroyPool();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}