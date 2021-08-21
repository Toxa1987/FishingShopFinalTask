package by.toxa.fishingshop.model.entity;

public enum OrderStatus {
    IN_PROCESS("in_process"),
    CANCELED("canceled"),
    DONE("done");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
