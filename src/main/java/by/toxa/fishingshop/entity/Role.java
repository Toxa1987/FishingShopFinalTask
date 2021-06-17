package by.toxa.fishingshop.entity;

public enum Role {
    MANAGER("manager"),
    USER("user"),
    ADMIN("admin"),
    GUEST("guest");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

