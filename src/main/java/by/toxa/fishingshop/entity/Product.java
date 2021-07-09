package by.toxa.fishingshop.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Base64;

public class Product {
    private int id;
    private String vendor;
    private ProductType productType;
    private ManufactureType manufacture;
    private String description;
    private byte[] image;
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

    public byte[] getImage() {
        return image;
    }

    public String getImageCode() {
        return Base64.getEncoder().encodeToString(image);
    }

    public void setImage(byte[] image) {
        this.image = image;
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
                ", image=" + Arrays.toString(image) +
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

        public ProductBuilder setImage(byte[] bytes) {
            product.setImage(bytes);
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
