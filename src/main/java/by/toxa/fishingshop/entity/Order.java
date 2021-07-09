package by.toxa.fishingshop.entity;

import java.util.Date;

public class Order {
    private long id;
    private String orderName;
    private Date date;
    private OrderStatus status;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
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
            order.orderName=orderName;
            return this;
        }

        public OrderBuilder setDate(Date date) {
            order.date=date;
            return this;
        }
        public OrderBuilder setOrderStatus(OrderStatus status){
            order.status=status;
            return this;
        }
        public Order build(){
            return order;
        }

    }
}
