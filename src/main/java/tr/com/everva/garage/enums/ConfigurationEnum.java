package tr.com.everva.garage.enums;

public enum ConfigurationEnum {

    PROFIT("profit_sharing");

    private String key;

    ConfigurationEnum(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
