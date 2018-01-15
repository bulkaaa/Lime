package com.modern.codes.lime.report;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DrawSeries {

    public static String plot(final ArrayList<TimeSeries> timeSeriesList,
                              final ArrayList<TimeSeries> timeSeriesForecastList, final Date toDay, String header) {
        final XYChart chart = new XYChartBuilder().width(600)
                                                  .height(400)
                                                  .title(header)
                                                  .xAxisTitle("Time")
                                                  .yAxisTitle("Production")
                                                  .build();
        chart.getStyler()
             .setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler()
             .setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler()
             .setDatePattern("dd-MMM-YY");
        final Date firstDay = getDate(timeSeriesList, toDay);

        ProcessDataInTimeSeries(timeSeriesForecastList, chart, firstDay);
        ProcessDataInTimeSeries(timeSeriesList, chart, firstDay);

        // Save it
        final String filename = "./Sample_Chart";
        try {
            BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return filename + ".png";
    }

    private static void ProcessDataInTimeSeries(final ArrayList<TimeSeries> timeSeriesList, final XYChart chart,
                                                final Date firstDay) {
        for (final TimeSeries timeSeries : timeSeriesList) {
            final List<Date> xData = new ArrayList<>();
            final List<Integer> yData = new ArrayList<>();
            final Calendar cal = Calendar.getInstance();
            for (int i = 0; i < timeSeries.size(); i++) {
                cal.setTime(firstDay);
                cal.add(Calendar.DATE, +i);
                final Date dateBefore = cal.getTime();
                xData.add(dateBefore);
                yData.add(timeSeries.get(i));
            }
            chart.addSeries(timeSeries.getLabel(), xData, yData);
        }
    }

    private static Date getDate(final ArrayList<TimeSeries> timeSeriesList, final Date toDay) {
        final Calendar call = Calendar.getInstance();
        call.setTime(toDay);
        call.add(Calendar.DATE, -timeSeriesList.get(0)
                                                       .size() + 1);
        return call.getTime();
    }
}
