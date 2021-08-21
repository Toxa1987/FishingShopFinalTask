package by.toxa.fishingshop.model.repository.impl.specification;

import by.toxa.fishingshop.model.connection.CustomConnectionPool;
import by.toxa.fishingshop.model.entity.OrderStatus;
import by.toxa.fishingshop.model.repository.Specification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class FindOrderByOrderStatusSpecification  implements Specification {
    private static final Logger logger = LogManager.getLogger();
    private static final String GET_BY_STATUS_QUERY = "" +
            "SELECT orders.id,orders.order,orders.date,status,summary_price,user_id " +
            "FROM orders " +
            "INNER JOIN order_status o on orders.order_status_id = o.id\n" +
            "WHERE status=?" +
            "ORDER BY orders.date DESC;";

    private OrderStatus status;

    public FindOrderByOrderStatusSpecification(OrderStatus status) {

        this.status = status;
    }

    @Override
    public Statement getStatement() {
        PreparedStatement preparedStatement = null;
        try (Connection connection = CustomConnectionPool.getInstance().getConnection()) {
            preparedStatement = connection.prepareStatement(GET_BY_STATUS_QUERY);
            preparedStatement.setString(1, status.getValue());
        } catch (SQLException e) {
            logger.error("Can not create query. Exception: ", e);
        }
        return preparedStatement;
    }
}


