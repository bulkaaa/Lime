package com.modern.codes.lime.report;
import java.util.ArrayList;

public class TimeSeries {
    private final ArrayList<Integer> TS = new ArrayList<Integer>();
    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    TimeSeries() {
        label = null;
    }

    TimeSeries(final String label) {
        this.label = label;
    }

    public Integer sum() {
        Integer sum = 0;
        for(final Integer d : TS)
            sum += d;
        return sum;
    }

    public Integer sum(final int n) {
        final ArrayList<Integer> TSLast = new ArrayList<Integer>(TS.subList(TS.size() - n, TS.size()));
        Integer sum = 0;
        for(final Integer d : TSLast)
            sum += d;
        return sum;
    }


    public void add(final Integer resource_cnt) {
        TS.add(resource_cnt);
    }

    public Integer get(final Integer time) {
        return TS.get(time);
    }

    public int size() {
        return TS.size();
    }
}