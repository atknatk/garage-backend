package tr.com.everva.garage.enums;

public enum ProfitEnum {

    RATIO("ratio"),
    CAPITAL("capital");

    private String key;

    ProfitEnum(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
