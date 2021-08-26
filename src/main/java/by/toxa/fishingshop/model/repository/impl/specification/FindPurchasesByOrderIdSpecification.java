package by.toxa.fishingshop.model.repository.impl.specification;

import by.toxa.fishingshop.model.connection.CustomConnectionPool;
import by.toxa.fishingshop.model.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindPurchasesByOrderIdSpecification implements Specification {
    private static final Logger logger = LogManager.getLogger();
    private static final String GET_BY_ORDER_ID_QUERY ="" +
            "SELECT\n" +
            "  order_id,\n" +
            "  p.id,\n" +
            "  p.vendor,\n" +
            "  p.name,\n" +
            "  m.manufacture,\n" +
            "  pt.type,\n" +
            "  p.description,\n" +
            "  p.image,\n" +
            "  p.price,\n" +
            "  p.number_in_stock\n" +
            "FROM purchases\n" +
            "INNER JOIN products AS p on purchases.product_id = p.id\n" +
            "INNER JOIN product_manufacture m on p.manufacture_id = m.id\n" +
            "INNER JOIN product_types pt on p.type_id = pt.id\n" +
            "WHERE order_id = ?;";
    private long id;

    public FindPurchasesByOrderIdSpecification(long id) {
        this.id = id;
    }

    @Override
    public Statement getStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement(GET_BY_ORDER_ID_QUERY);
            preparedStatement.setLong(1, id);
        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
        }
        return preparedStatement;
    }
}
