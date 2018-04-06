package com.modern.codes.lime.model;

/**
 * The enum Unit.
 *
 * @author jaroszk
 */
public enum Unit {
    /**
     * Feet unit.
     */
    FEET, /**
     * Meters unit.
     */
    METERS, /**
     * Inches unit.
     */
    INCHES, /**
     * Mm unit.
     */
    MM, /**
     * Unknown unit.
     */
    UNKNOWN, /**
     * Unit unit.
     */
    UNIT, /**
     * Liter unit.
     */
    LITER, /**
     * Ml unit.
     */
    ML, /**
     * Grams unit.
     */
    GRAMS, /**
     * Kg unit.
     */
    KG, /**
     * Mg unit.
     */
    MG, /**
     * Sqm unit.
     */
    SQM, /**
     * Cbm unit.
     */
    CBM, /**
     * Bar unit.
     */
    BAR, /**
     * Box unit.
     */
    BOX;

    /**
     * From value unit.
     *
     * @param val the val
     * @return the unit
     */
    public static Unit fromValue(final String val) {
        if (null != val) {
            try {
                return Unit.valueOf(val);
            } catch (final Exception e) {
                return UNKNOWN;
            }
        } else {
            return null;
        }
    }
}
