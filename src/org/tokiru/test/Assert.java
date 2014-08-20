package org.tokiru.test;

/**
 * Created by tokiru.
 */
public class Assert {
    private boolean result;

    public Assert() {
        result = true;
    }

    public boolean as(boolean expression, String description) {
        if (expression) {
            System.out.println(description + " [passed]");
            return true;
        } else {
            System.out.println(description + " [failed]");
            result = false;
            return false;
        }
    }

    public boolean getResult() {
        return result;
    }
}
