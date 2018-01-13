package com.modern.codes.lime.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class TimeSeries {
    private final ArrayList<Integer> TS = new ArrayList<Integer>();

    public String getLabel() {
        return label;
    }

    public void setLabel(String l) {
        label = l;
    }

    private String label;
    @Autowired
    public TimeSeries() {
        label = null;
    }

    public TimeSeries(String _label) {
        label = _label;
    }


    public void printTS() {
        for (int i = 0; i< TS.size(); i++) {
            Integer time = i;
            Integer state = TS.get(i);
            System.out.printf("%16d: %s%n", time, state);
        }
    }
    public Integer sum() {
        Integer sum = 0;
        for(Integer d : TS)
            sum += d;
        return sum;
    }

    public Integer sum(int n) {
        ArrayList<Integer> TSLast = new ArrayList<Integer>(TS.subList(TS.size() - n, TS.size()));
        Integer sum = 0;
        for(Integer d : TSLast)
            sum += d;
        return sum;
    }


    public void add(Integer resource_cnt) {
        TS.add(resource_cnt);
    }

    public Integer get(Integer time) {
        return TS.get(time);
    }

    public int size() {
        return TS.size();
    }



}

