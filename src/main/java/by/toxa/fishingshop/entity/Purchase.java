package by.toxa.fishingshop.entity;

public class Purchase {
    private long id;
    private long orderId;
    private long userId;
    private Cart cart;

    public Purchase(long orderId, long userId, Cart cart) {
        this.orderId = orderId;
        this.userId = userId;
        this.cart = cart;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
