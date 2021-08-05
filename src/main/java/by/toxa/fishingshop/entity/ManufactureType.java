package by.toxa.fishingshop.entity;

public enum ManufactureType {
    SHIMANO("shimano"),
    KAIDA("kaida"),
    OWNER("owner"),
    SALMO("salmo"),
    OTHER("other");

    private String value;

    ManufactureType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
