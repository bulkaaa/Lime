package com.modern.codes.lime.report;

import org.apache.commons.lang3.ArrayUtils;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DrawSeries {

    private static final Logger LOG = LoggerFactory.getLogger(DrawSeries.class);

    public static byte[] plotChart(final ArrayList<TimeSeries> timeSeriesList,
                              final ArrayList<TimeSeries> timeSeriesForecastList, final Date toDay, final String header, final String filename, final String type) {

        byte[] ReturnArray;
        LOG.info("Generating Chart type: {}", type);

        if  (type.equals("Pie")) ReturnArray = plotPie(timeSeriesList, timeSeriesForecastList, toDay, header, filename, type);
        else ReturnArray = plot(timeSeriesList, timeSeriesForecastList, toDay, header, filename, type);

        return ReturnArray;
    }

    public static byte[] plot(final ArrayList<TimeSeries> timeSeriesList,
                                                      final ArrayList<TimeSeries> timeSeriesForecastList, final Date toDay, final String header, final String filename, final String type) {
        final CategoryChart chart = new CategoryChartBuilder().width(600)
                                                  .height(400)
                                                  .title(header)
                                                  .xAxisTitle("Time")
                                                  .yAxisTitle("Production")
                                                  .build();
        chart.getStyler()
             .setLegendPosition(Styler.LegendPosition.OutsideE);
        if (type.equals("Line")) chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Line);
        if (type.equals("Bar")) chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar);
        if (type.equals("Stick")) chart.getStyler().setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Stick);

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

    public static byte[] plotPie(final ArrayList<TimeSeries> timeSeriesList,
                              final ArrayList<TimeSeries> timeSeriesForecastList, final Date toDay, final String header, final String filename, final String type) {
        // Create Chart
        PieChart chart = new PieChartBuilder().width(800).height(600).title(header).build();

        // Customize Chart
        Color[] sliceColors = new Color[] { new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159), new Color(246, 199, 182) };
        chart.getStyler().setSeriesColors(sliceColors);

        final Date firstDay = getDate(timeSeriesList, toDay);

        ProcessDataInTimeSeriesPie(timeSeriesForecastList, chart, firstDay);
        ProcessDataInTimeSeriesPie(timeSeriesList, chart, firstDay);

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

    private static void ProcessDataInTimeSeriesPie(final ArrayList<TimeSeries> timeSeriesList, final PieChart chart,
                                                final Date firstDay) {
        for (final TimeSeries timeSeries : timeSeriesList) {
            Integer sum = 0;
            final Calendar cal = Calendar.getInstance();
            for (int i = 0; i < timeSeries.size(); i++) {
                cal.setTime(firstDay);
                cal.add(Calendar.DATE, +i);
                sum = sum + timeSeries.get(i);
            }
            chart.addSeries(timeSeries.getLabel(), sum);

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
