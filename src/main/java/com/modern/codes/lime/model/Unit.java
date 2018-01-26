package com.modern.codes.lime.model;

/**
 *
 * @author jaroszk
 *
 */

public enum Unit {
    FEET,METERS,INCHES,MM,UNKNOWN,UNIT,LITER,ML,GRAMS,KG,MG,SQM,CBM,BAR,BOX;

    public static Unit fromValue(final String val) {
        if (null != val) {
            try{
                return Unit.valueOf(val);
            } catch (final Exception e) {
                return UNKNOWN;
            }
        } else {
            return null;
        }
    }
}
