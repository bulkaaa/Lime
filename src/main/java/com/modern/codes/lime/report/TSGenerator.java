package com.modern.codes.lime.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;
@Service
public class TSGenerator {
    @Autowired
    public TSGenerator() {
    }

    public static void loadSampleTSList(ArrayList<TimeSeries> seriesList) {
        for (int i = 0; i < 3; i++) {
            TimeSeries series2 = new TimeSeries("Series "+(i+1));
            loadSampleTS(series2, (3-i)*(2-i));
            seriesList.add(series2);
        }
    }


    public static void loadSampleTS(TimeSeries series, int k){
        for (int i = 15 ; i > 0 ; i--) {
            Random r = new Random();
            int lowerBound = 0;
            int upperBound = 100;
            int result =  r.nextInt(upperBound-lowerBound)  + i*i + k ;

            series.add(result);
        }
    }

}
