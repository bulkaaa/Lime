package com.modern.codes.lime.class_models;

import java.util.ArrayList;

/**
 * The type Time series.
 */
public class TimeSeries {
    /**
     * Instantiates a new Time series.
     */
    public TimeSeries() {
        label = null;
    }

    /**
     * Instantiates a new Time series.
     *
     * @param label the label
     */
    public TimeSeries(final String label) {
        this.label = label;
    }

    /**
     * Gets label.
     *
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets label.
     *
     * @param label the label
     */
    public void setLabel(final String label) {
        this.label = label;
    }

    /**
     * Sum integer.
     *
     * @return the integer
     */
    public Integer sum() {
        Integer sum = 0;
        for (final Integer d : TS) {
            sum += d;
        }
        return sum;
    }

    /**
     * Sum integer.
     *
     * @param n the n
     * @return the integer
     */
    public Integer sum(final int n) {
        final ArrayList<Integer> TSLast = new ArrayList<>(TS.subList(TS.size() - n, TS.size()));
        Integer sum = 0;
        for (final Integer d : TSLast) {
            sum += d;
        }
        return sum;
    }

    /**
     * Add.
     *
     * @param resource_cnt the resource cnt
     */
    public void add(final Integer resource_cnt) {
        TS.add(resource_cnt);
    }

    /**
     * Get integer.
     *
     * @param time the time
     * @return the integer
     */
    public Integer get(final Integer time) {
        return TS.get(time);
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return TS.size();
    }

    private final ArrayList<Integer> TS = new ArrayList<>();
    private String label;
}