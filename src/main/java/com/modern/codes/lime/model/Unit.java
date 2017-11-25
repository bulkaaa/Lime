package com.modern.codes.lime.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

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
            } catch (Exception e) {
                return UNKNOWN;
            }
        } else {
            return null;
        }
    }
}
