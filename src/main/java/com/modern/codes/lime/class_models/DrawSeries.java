package com.modern.codes.lime.class_models;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.Styler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Draw series.
 */
public class DrawSeries {

    /**
     * Plot chart byte [ ].
     *
     * @param timeSeriesList         the time series list
     * @param timeSeriesForecastList the time series forecast list
     * @param toDay                  the to day
     * @param header                 the header
     * @param filename               the filename
     * @param type                   the type
     * @return the byte [ ]
     */
    public static byte[] plotChart(final ArrayList<TimeSeries> timeSeriesList,
                                   final ArrayList<TimeSeries> timeSeriesForecastList, final Date toDay,
                                   final String header, final String filename, final String type) {

        LOG.info("Generating Chart type: {}", type);

        return "Pie".equals(type)
               ? plotPie(timeSeriesList, timeSeriesForecastList, toDay, header, filename, type)
               : plot(timeSeriesList, timeSeriesForecastList, toDay, header, filename, type);
    }

    private static byte[] plot(final ArrayList<TimeSeries> timeSeriesList,
                               final ArrayList<TimeSeries> timeSeriesForecastList, final Date toDay,
                               final String header, final String filename, final String type) {
        final CategoryChart chart = new CategoryChartBuilder().width(600)
                                                              .height(400)
                                                              .title(header)
                                                              .xAxisTitle("Time")
                                                              .yAxisTitle("Production")
                                                              .build();
        chart.getStyler()
             .setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setXAxisLabelRotation(45);
        chart.getStyler().setXAxisLabelAlignment(Styler.TextAlignment.Right);
        if ("Line".equals(type)) {
            chart.getStyler()
                 .setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Line);
        }
        if ("Bar".equals(type)) {
            chart.getStyler()
                 .setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Bar);
        }
        if ("Stick".equals(type)) {
            chart.getStyler()
                 .setDefaultSeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Stick);
        }

        chart.getStyler()
             .setDatePattern("dd-MMM-YY");
        final Date firstDay = getDate(timeSeriesList, toDay);

        ProcessDataInTimeSeries(timeSeriesForecastList, chart, firstDay);
        ProcessDataInTimeSeries(timeSeriesList, chart, firstDay);

        // Save it
        try {
            BitmapEncoder.saveBitmap(chart, "./chart", BitmapEncoder.BitmapFormat.PNG);
            return Base64.getEncoder()
                         .encode(BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG));
        } catch (final IOException e) {
            LOG.error("IO error while generating chart: " + filename);
        }
        return ArrayUtils.EMPTY_BYTE_ARRAY;
    }

    private static byte[] plotPie(final ArrayList<TimeSeries> timeSeriesList,
                                  final ArrayList<TimeSeries> timeSeriesForecastList, final Date toDay,
                                  final String header, final String filename, final String type) {
        // Create Chart
        final PieChart chart = new PieChartBuilder().width(800)
                                                    .height(600)
                                                    .title(header)
                                                    .build();

        // Customize Chart
        final Color[] sliceColors =
                {new Color(224, 68, 14), new Color(230, 105, 62), new Color(236, 143, 110), new Color(243, 180, 159),
                 new Color(246, 199, 182)};
        chart.getStyler()
             .setSeriesColors(sliceColors);

        final Date firstDay = getDate(timeSeriesList, toDay);

        ProcessDataInTimeSeriesPie(timeSeriesForecastList, chart, firstDay);
        ProcessDataInTimeSeriesPie(timeSeriesList, chart, firstDay);

        // Save it
        try {
            BitmapEncoder.saveBitmap(chart, "./chart", BitmapEncoder.BitmapFormat.PNG);
            return Base64.getEncoder()
                         .encode(BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG));
        } catch (final IOException e) {
            LOG.error("IO error while generating chart: " + filename);
        }
        return ArrayUtils.EMPTY_BYTE_ARRAY;
    }

    private static void ProcessDataInTimeSeries(final ArrayList<TimeSeries> timeSeriesList, final CategoryChart chart,
                                                final Date firstDay) {
        for (final TimeSeries timeSeries : timeSeriesList) {
            final List<Date> xData = new ArrayList<>();
            final List<Double> yData = new ArrayList<>();
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
            Double sum = 0.0;
            final Calendar cal = Calendar.getInstance();
            for (int i = 0; i < timeSeries.size(); i++) {
                cal.setTime(firstDay);
                cal.add(Calendar.DATE, +i);
                sum += timeSeries.get(i);
            }
            chart.addSeries(timeSeries.getLabel(), sum);

        }
    }

    private static Date getDate(final ArrayList<TimeSeries> timeSeriesList, final Date toDay) {
        final Calendar call = Calendar.getInstance();
        call.setTime(toDay);
        LOG.info("Number of series: {}", timeSeriesList.size());
        LOG.info("Number of days: {}", timeSeriesList.get(0).size());

        call.add(Calendar.DATE, -timeSeriesList.get(0).size() + 1);
        return call.getTime();
    }

    private static final Logger LOG = LoggerFactory.getLogger(DrawSeries.class);
}
