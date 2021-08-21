package by.toxa.fishingshop.model.entity;

public class Purchase {
    private long userId;
    private Cart cart;

    public Purchase(Cart cart) {
        this.cart = cart;
    }

    public Purchase(long userId, Cart cart) {
        this.userId = userId;
        this.cart = cart;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Purchase purchase = (Purchase) o;

        if (getUserId() != purchase.getUserId()) return false;
        return getCart() != null ? getCart().equals(purchase.getCart()) : purchase.getCart() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + (getCart() != null ? getCart().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "userId=" + userId +
                ", cart=" + cart +
                '}';
    }
}