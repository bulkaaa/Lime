package com.modern.codes.lime.report;

public class Smoothing {

    public static TimeSeries calculateSmoothing(TimeSeries series, final int days_ahead)
    {
        double alpha = 0.1;
        double beta = 1;
        double mse = MSE(series, DoubleExpSmoothing(series,alpha,beta, days_ahead));
        for (double a = 0; a <= 1; a += 0.01) {
            for (double b = 0; b <= 1; b += 0.01) {
                final TimeSeries test = DoubleExpSmoothing(series, a, b, days_ahead);
                final double test_mse = MSE (series, test);
                if (test_mse < mse){
                    mse = test_mse;
                    alpha = a;
                    beta = b;
                }
            }
        }
        series = DoubleExpSmoothing(series,alpha,beta, days_ahead);
        return series;
    }
    private static double MSE(final TimeSeries original, final TimeSeries forecast) {
        double mse = 0;
        for (int i = 0; i< original.size(); i++){
            mse += (original.get(i) - forecast.get(i + 1)) * (original.get(i) - forecast.get(i + 1));
        }
        return mse/original.size();
    }
    private static Integer Average(final TimeSeries ts)
    {
        return ts.sum()/ts.size();
    }

    private static Integer MovingAverage(final TimeSeries ts, final int n)
    {
        return ts.sum(n)/n;
    }

    private static Integer WeightedAverage(final TimeSeries ts){

        final Integer size = ts.size() - 1;
        final Integer four = ts.get(size) * 4;
        final Integer three = ts.get(size - 1) * 3;
        final Integer two = ts.get(size - 2) * 2;
        final Integer one = ts.get(size - 3);

        return (four + three + two + one)/10;
    }

    private static TimeSeries SingleExpSmoothing(final TimeSeries ts, final double alpha){

        final TimeSeries SmoothTS = new TimeSeries();
        SmoothTS.add(ts.get(0));
        for (int i = 1; i<= ts.size(); i++) {
            SmoothTS.add((int)(alpha * ts.get(i - 1) + (1 - alpha) * SmoothTS.get(i - 1)));
        }

        return SmoothTS;
    }

    private static TimeSeries DoubleExpSmoothing(final TimeSeries ts, final double alpha, final double beta, final int days){

        final TimeSeries SmoothTS = new TimeSeries();
        final TimeSeries TrendTS = new TimeSeries();

        SmoothTS.add(ts.get(0));
        TrendTS.add(ts.get(1)-ts.get(0));

        for (int i = 1; i < ts.size(); i++) {
            SmoothTS.add((int)(alpha * ts.get(i) + (1 - alpha) * (SmoothTS.get(i - 1) + TrendTS.get(i - 1))));
            TrendTS.add((int)(beta * (SmoothTS.get(i) - SmoothTS.get(i - 1)) + (1 - beta) * TrendTS.get(i - 1)));
        }

        for (int i = 0; i < days; i++){
            SmoothTS.add(Math.max(SmoothTS.get(ts.size() - 1) + (i + 1) * TrendTS.get(ts.size() - 1),0));
        }
        return SmoothTS;
    }

    private static TimeSeries TripleExpSmoothing(final TimeSeries ts, final double alpha, final double beta, final double gamma, final int days){
        final TimeSeries SmoothTS = new TimeSeries();
        final TimeSeries TrendTS = new TimeSeries();
        final TimeSeries SeasonTS = new TimeSeries();

        SmoothTS.add(ts.get(0));
        TrendTS.add(ts.get(1)-ts.get(0));

        //First Week
        for (int i = 1; i < 7; i++) {
            SmoothTS.add(ts.get(i));
            TrendTS.add((int)(beta * (SmoothTS.get(i) - SmoothTS.get(i-1)) + (1 - beta) * TrendTS.get(i - 1)));
            SeasonTS.add((int)(gamma * ts.get(i)/ SmoothTS.get(i) + (1-gamma) * SeasonTS.get(i-7)));
        }

        //Second and Next Weeks
        for (int i = 7; i < ts.size(); i++) {
            SmoothTS.add((int)(alpha * (ts.get(i) / SeasonTS.get(i-7))+ (1 - alpha) * (SmoothTS.get(i - 1) + TrendTS.get(i-1))));
            TrendTS.add((int)(beta * (SmoothTS.get(i) - SmoothTS.get(i-1)) + (1 - beta) * TrendTS.get(i - 1)));
            SeasonTS.add((int)(gamma * ts.get(i)/ SmoothTS.get(i) + (1-gamma) * SeasonTS.get(i-7)));
        }

        //Forecast
        for (int i = 0; i < days; i++){
            SmoothTS.add((SmoothTS.get(ts.size()-1)+ (i + 1) * TrendTS.get(ts.size() - 1)) * SeasonTS.get(i - 7 + i + 1));
        }
        return SmoothTS;
    }
}