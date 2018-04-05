package com.modern.codes.lime.tools;

import java.util.ArrayList;
import java.util.Random;

import com.modern.codes.lime.class_models.TimeSeries;

/**
 * The type Ts generator.
 */
public class TSGenerator {

    /**
     * Instantiates a new Ts generator.
     */
    public TSGenerator() {
    }

    /**
     * Load sample ts list.
     *
     * @param seriesList the series list
     */
    public static void loadSampleTSList(final ArrayList<TimeSeries> seriesList) {
        for (int i = 0; i < 3; i++) {
            final TimeSeries series2 = new TimeSeries("Series " + (i + 1));
            loadSampleTS(series2, (3 - i) * (2 - i));
            seriesList.add(series2);
        }
    }

    /**
     * Load sample ts.
     *
     * @param series the series
     * @param k      the k
     */
    public static void loadSampleTS(final TimeSeries series, final int k) {
        for (int i = 15; i > 0; i--) {
            final Random r = new Random();
            final int lowerBound = 0;
            final int upperBound = 100;
            final int result = r.nextInt(upperBound - lowerBound) + (i * i) + k;

            series.add(result);
        }
    }

}
