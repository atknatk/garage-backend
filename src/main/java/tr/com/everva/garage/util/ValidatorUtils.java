package tr.com.everva.garage.util;

import java.util.UUID;

public class ValidatorUtils {

    public static boolean isValidUUID(String key) {
        try {
            UUID.fromString(key);
            return true;
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

}
