package by.toxa.fishingshop.model.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void clearCart() {
        products.clear();
    }

    public BigDecimal getSummaryPrice() {
        BigDecimal sum = BigDecimal.valueOf(0);
        for (Product p : products) {
            sum = sum.add(p.getPrice());
        }
        return sum;
    }
}
