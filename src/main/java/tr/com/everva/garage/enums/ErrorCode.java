package tr.com.everva.garage.enums;

public enum  ErrorCode {

    NOT_EXIST("1000");

    private String key;

    ErrorCode(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
