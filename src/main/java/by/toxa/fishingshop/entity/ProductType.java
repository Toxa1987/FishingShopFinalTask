package by.toxa.fishingshop.entity;

public enum ProductType {
    REEL("reel",0),
    ROD("rod",0),
    LINE("line",0),
    SPINNING("spinning",0),
    ACCESSORIES("accessories",0);

    private String type;
    private int discount;

    ProductType(String type, int discount) {
        this.type = type;
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
