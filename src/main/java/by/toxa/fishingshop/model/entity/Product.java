package by.toxa.fishingshop.model.entity;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Base64;

public class Product {
    private int id;
    private String vendor;
    private String name;
    private ProductType productType;
    private ManufactureType manufacture;
    private String description;
    private InputStream image;
    private BigDecimal price;
    private int numberInStock;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ManufactureType getManufacture() {
        return manufacture;
    }

    public void setManufacture(ManufactureType manufacture) {
        this.manufacture = manufacture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InputStream getImage() {
        return image;
    }

    public void setImage(InputStream image) {

        this.image = image;

    }

    public String getImageCode() {
        String imageCode = "";
        try {
            imageCode = Base64.getEncoder().encodeToString(image.readAllBytes());
        } catch (IOException e) {

        }
        return imageCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (getId() != product.getId()) return false;
        if (!getVendor().equals(product.getVendor())) return false;
        if (getProductType() != product.getProductType()) return false;
        if (getManufacture() != product.getManufacture()) return false;
        return getPrice().equals(product.getPrice());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getVendor().hashCode();
        result = 31 * result + getProductType().hashCode();
        result = 31 * result + getManufacture().hashCode();
        result = 31 * result + getPrice().hashCode();
        return result;
    }

    @Override//to do in StringBuffer style
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", vendor='" + vendor + '\'' +
                ", productType=" + productType +
                ", manufacture=" + manufacture +
                ", description='" + description + '\'' +
                ", image=" + image.toString() +
                ", price=" + price +
                ", numberInStock=" + numberInStock +
                '}';
    }

    public static class ProductBuilder {
        private Product product;

        public ProductBuilder() {
            product = new Product();
        }

        public ProductBuilder setId(int id) {
            product.setId(id);
            return this;
        }

        public ProductBuilder setVendor(String vendor) {
            product.setVendor(vendor);
            return this;
        }
        public ProductBuilder setName(String name) {
            product.setName(name);
            return this;
        }
        public ProductBuilder setManufacture(ManufactureType manufacture) {
            product.setManufacture(manufacture);
            return this;
        }

        public ProductBuilder setType(ProductType type) {
            product.setProductType(type);
            return this;
        }

        public ProductBuilder setDescription(String description) {
            product.setDescription(description);
            return this;
        }

        public ProductBuilder setImage(InputStream inputStream) {
            product.setImage(inputStream);
            return this;
        }

        public ProductBuilder setPrice(BigDecimal price) {
            product.setPrice(price);
            return this;
        }

        public ProductBuilder setNumberInStock(int number) {
            product.setNumberInStock(number);
            return this;
        }

        public Product build() {
            return product;
        }
    }
}
