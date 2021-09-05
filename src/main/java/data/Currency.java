package data;

public enum Currency {
    USD("Доллар"), EURO("Евро");
    private String value;

    Currency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

