package com.modern.codes.lime.report;

import org.apache.commons.lang3.ArrayUtils;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DrawSeries {

    private static final Logger LOG = LoggerFactory.getLogger(DrawSeries.class);

    public static byte[] plot(final ArrayList<TimeSeries> timeSeriesList,
                                                      final ArrayList<TimeSeries> timeSeriesForecastList, final Date toDay, final String header, final String filename) {
        final CategoryChart chart = new CategoryChartBuilder().width(600)
                                                  .height(400)
                                                  .title(header)
                                                  .xAxisTitle("Time")
                                                  .yAxisTitle("Production")
                                                  .build();
        chart.getStyler()
             .setLegendPosition(Styler.LegendPosition.OutsideE);
        //chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Line);
        chart.getStyler()
             .setDatePattern("dd-MMM-YY");
        final Date firstDay = getDate(timeSeriesList, toDay);

        ProcessDataInTimeSeries(timeSeriesForecastList, chart, firstDay);
        ProcessDataInTimeSeries(timeSeriesList, chart, firstDay);

        // Save it
        try {
            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
            return Base64.getEncoder().encode(
                    BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG));
        } catch (final IOException e) {
            LOG.error("IO error while generating chart: " + filename);
        }
        return ArrayUtils.EMPTY_BYTE_ARRAY;
    }

    private static void ProcessDataInTimeSeries(final ArrayList<TimeSeries> timeSeriesList, final CategoryChart chart,
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
