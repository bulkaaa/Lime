package com.modern.codes.lime.model;

/**
 *
 * @author jaroszk
 *
 */

public enum Unit {
    FEET,METERS,INCHES,MM, UNKNOWN;

    public static Unit fromValue(final String val) {
        if (null != val) {
            for (final Unit unit : Unit.values()) {
                if (unit.toString().equalsIgnoreCase(val)) {
                    return unit;
                }
            }
            return UNKNOWN;
        } else {
            return null;
        }
    }
}
