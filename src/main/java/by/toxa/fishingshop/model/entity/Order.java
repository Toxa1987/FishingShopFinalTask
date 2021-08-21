package by.toxa.fishingshop.model.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Order {
    private long id;
    private String orderName;
    private LocalDate date;
    private OrderStatus status;
    private BigDecimal summaryPrice;
    private long userId;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getSummaryPrice() {
        return summaryPrice;
    }

    public void setSummaryPrice(BigDecimal summaryPrice) {
        this.summaryPrice = summaryPrice;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (getId() != order.getId()) return false;
        if (getUserId() != order.getUserId()) return false;
        if (getOrderName() != null ? !getOrderName().equals(order.getOrderName()) : order.getOrderName() != null)
            return false;
        if (getDate() != null ? !getDate().equals(order.getDate()) : order.getDate() != null) return false;
        if (getStatus() != order.getStatus()) return false;
        return getSummaryPrice() != null ? getSummaryPrice().equals(order.getSummaryPrice()) : order.getSummaryPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getOrderName() != null ? getOrderName().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getSummaryPrice() != null ? getSummaryPrice().hashCode() : 0);
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderName='" + orderName + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", summaryPrice=" + summaryPrice +
                ", userId=" + userId +
                '}';
    }

    public static class OrderBuilder {
        private Order order;

        public OrderBuilder() {
            order = new Order();
        }

        public OrderBuilder setId(long id) {
            order.id = id;
            return this;
        }

        public OrderBuilder setOrderName(String orderName) {
            order.orderName = orderName;
            return this;
        }

        public OrderBuilder setDate(LocalDate date) {
            order.date = date;
            return this;
        }

        public OrderBuilder setOrderStatus(OrderStatus status) {
            order.status = status;
            return this;
        }
        public OrderBuilder setSummaryPrice(BigDecimal summaryPrice) {
            order.summaryPrice = summaryPrice;
            return this;
        }
        public OrderBuilder setUserId(long id) {
            order.userId = id;
            return this;
        }
        public Order build() {
            return order;
        }

    }
}
