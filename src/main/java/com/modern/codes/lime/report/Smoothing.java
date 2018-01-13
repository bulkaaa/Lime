package com.modern.codes.lime.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Smoothing {

    @Autowired
    public Smoothing() {
    }

    public TimeSeries calculateSmoothing(TimeSeries series, int days_ahead)
    {

        double alpha = 0.1;
        double beta = 1;
        double mse = MSE(series, DoubleExpSmoothing(series,alpha,beta, days_ahead));
        for (double a = 0 ; a <= 1; a = a + 0.01) {
            for (double b = 0 ; b <= 1; b = b + 0.01) {
                TimeSeries test = DoubleExpSmoothing(series, a, b, days_ahead);
                double test_mse = MSE (series, test);

                if (test_mse < mse){
                    System.out.println("ALPHA: "+alpha+" BETA: "+ beta+" MSE: "+mse);

                    mse = test_mse;
                    alpha = a;
                    beta = b;
                }
            }
        }
        System.out.println("Final ALPHA: "+alpha+" BETA: "+ beta+" MSE: "+mse );
        series = DoubleExpSmoothing(series,alpha,beta, days_ahead);
        return series;
    }
    public double MSE (TimeSeries original, TimeSeries forecast) {
        double mse = 0;
        for (int i = 0; i< original.size(); i++){
            mse = mse + (((original.get(i) - forecast.get(i+1))*(original.get(i) - forecast.get(i+1))));
        }
        return mse/original.size();
    }
    public Integer Average(TimeSeries ts)
    {
        return ts.sum()/ts.size();
    }

    public Integer MovingAverage(TimeSeries ts, int n)
    {
        return ts.sum(n)/n;
    }

    public Integer WeightedAverage(TimeSeries ts){

        Integer size = ts.size() - 1;
        Integer four = ts.get(size)*4;
        Integer three = ts.get(size-1)*3;
        Integer two = ts.get(size-2)*2;
        Integer one = ts.get(size-3)*1;


        return (four + three + two + one)/10;
    }

    public TimeSeries SingleExpSmoothing(TimeSeries ts, double alpha){

        TimeSeries SmoothTS = new TimeSeries();
        SmoothTS.add(ts.get(0));
        for (int i = 1; i<= ts.size(); i++) {
            Integer result = (int)(alpha * ts.get(i-1) + (1 - alpha) * SmoothTS.get(i - 1));
            SmoothTS.add(result);
            System.out.println(result);
        }

        return SmoothTS;
    }

    public TimeSeries DoubleExpSmoothing(TimeSeries ts, double alpha, double beta, int days){

        TimeSeries SmoothTS = new TimeSeries();
        TimeSeries TrendTS = new TimeSeries();

        SmoothTS.add(ts.get(0));
        TrendTS.add(ts.get(1)-ts.get(0));


        for (int i = 1; i < ts.size(); i++) {
            Integer result = (int)(alpha * ts.get(i) + (1 - alpha) * (SmoothTS.get(i - 1) + TrendTS.get(i-1)));
            SmoothTS.add(result);
            Integer result2 = (int)(beta * (SmoothTS.get(i) - SmoothTS.get(i-1)) + (1 - beta) * (TrendTS.get(i-1)));
            TrendTS.add(result2);
        }

        for (int i = 0; i < days; i++){
            SmoothTS.add(SmoothTS.get(ts.size()-1)+ ((i + 1)*(TrendTS.get(ts.size()-1))));
        }

        return SmoothTS;
    }

    public TimeSeries TripleExpSmoothing(TimeSeries ts, double alpha, double beta, double gamma, int days){

        TimeSeries SmoothTS = new TimeSeries();
        TimeSeries TrendTS = new TimeSeries();
        TimeSeries SeasonTS = new TimeSeries();


        SmoothTS.add(ts.get(0));

        TrendTS.add(ts.get(1)-ts.get(0));

        //First Week

        for (int i = 1; i < 7; i++) {
            Integer result = ts.get(i);
            SmoothTS.add(result);
            Integer result2 = (int)(beta * (SmoothTS.get(i) - SmoothTS.get(i-1)) + (1 - beta) * (TrendTS.get(i-1)));
            TrendTS.add(result2);
            Integer result3 = (int)(gamma * ts.get(i)/ SmoothTS.get(i) + (1-gamma) * SeasonTS.get(i-7));
            SeasonTS.add(result3);
        }

        //Second and Next Weeks

        for (int i = 7; i < ts.size(); i++) {
            Integer result = (int)(alpha * (ts.get(i) / SeasonTS.get(i-7))+ (1 - alpha) * (SmoothTS.get(i - 1) + TrendTS.get(i-1)));
            SmoothTS.add(result);
            Integer result2 = (int)(beta * (SmoothTS.get(i) - SmoothTS.get(i-1)) + (1 - beta) * (TrendTS.get(i-1)));
            TrendTS.add(result2);
            Integer result3 = (int)(gamma * ts.get(i)/ SmoothTS.get(i) + (1-gamma) * SeasonTS.get(i-7));
            SeasonTS.add(result3);
        }

        //Forecast

        for (int i = 0; i < days; i++){
            Integer result =  (SmoothTS.get(ts.size()-1)+ (i + 1)*(TrendTS.get(ts.size()-1))) * SeasonTS.get(i-7+i+1);
            SmoothTS.add(result);

        }

        return SmoothTS;
    }


}

