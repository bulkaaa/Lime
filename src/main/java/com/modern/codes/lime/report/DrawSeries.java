package com.modern.codes.lime.report;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class DrawSeries {

    @Autowired
    public DrawSeries() {
    }

    public static String plot(ArrayList<TimeSeries> timeSeriesList, ArrayList<TimeSeries> timeSeriesForecastList, Date toDay, String title) {
        XYChart chart = new XYChartBuilder().width(600).height(400).title(title).xAxisTitle("Time").yAxisTitle("Production").build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setDatePattern("dd-MMM-YY");
        Calendar call = Calendar.getInstance();
        call.setTime(toDay);
        call.add(Calendar.DATE, -timeSeriesList.get(0).size());
        Date firstDay = call.getTime();

        for (TimeSeries timeSeries: timeSeriesForecastList) {
            List<Date> xData = new ArrayList<Date>();
            List<Integer> yData = new ArrayList<Integer>();
            for (int i = 0; i< timeSeries.size(); i++){
                Calendar cal = Calendar.getInstance();
                cal.setTime(firstDay);
                cal.add(Calendar.DATE, +i);
                Date dateBefore = cal.getTime();
                xData.add(dateBefore);
                yData.add(timeSeries.get(i));
            }
            XYSeries series = chart.addSeries(timeSeries.getLabel(), xData, yData);

        }
        for (TimeSeries timeSeries: timeSeriesList) {
            List<Date> xData = new ArrayList<Date>();
            List<Integer> yData = new ArrayList<Integer>();
            for (int i = 0; i< timeSeries.size(); i++){
                Calendar cal = Calendar.getInstance();
                cal.setTime(firstDay);
                cal.add(Calendar.DATE, +i);
                Date dateBefore = cal.getTime();
                xData.add(dateBefore);
                yData.add(timeSeries.get(i));
            }
            XYSeries series = chart.addSeries(timeSeries.getLabel(), xData, yData);

        }

        // Save it
        String filename = "./Sample_Chart";
        try {
            BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filename+".png";
    }

}
