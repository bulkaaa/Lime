package com.modern.codes.lime.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.modern.codes.lime.class_models.DrawSeries;
import com.modern.codes.lime.class_models.TimeSeries;
import com.modern.codes.lime.exception.InvalidRequestException;

/**
 * The type Forecast service.
 */
@Service
public class ForecastService implements IForecastService {

    /**
     * Instantiates a new Forecast service.
     *
     * @param timeSeriesService the time series service
     * @param smoothingService  the smoothing service
     * @param jobService        the job service
     */
    @Autowired
    public ForecastService(final ITimeSeriesService timeSeriesService, final ISmoothingService smoothingService,
                           final IJobService jobService) {
        this.timeSeriesService = timeSeriesService;
        this.smoothingService = smoothingService;
        this.jobService = jobService;
    }

    @Override
    public byte[] plotForecast(@RequestParam final String startDate, @RequestParam final Integer noDays,
                               @RequestParam final Integer noDaysForecast, @RequestParam final String chartType,
                               @RequestParam  final List<String> productIds) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        final Date date;
        try {
            date = formatter.parse(startDate);
        } catch (final ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }
        final ArrayList<TimeSeries> seriesL = timeSeriesService.Extract(date, noDays, productIds);
        final ArrayList<TimeSeries> seriesFL = new ArrayList<>();
        for (final TimeSeries series : seriesL) {
            final TimeSeries forecast = smoothingService.calculateSmoothing(series, noDaysForecast);
            forecast.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(forecast);
        }

        return DrawSeries.plotChart(seriesL, seriesFL, date, "Production in the Past "
                                                             + noDays
                                                             + " Days and forecast for the next "
                                                             + noDaysForecast
                                                             + " days.", "chart", chartType);
    }


    @Override
    public byte[] plotForecastResource(@RequestParam final String startDate, @RequestParam final Integer noDays,
                               @RequestParam final Integer noDaysForecast, @RequestParam final String chartType,
                                       @RequestParam  final List<String> resourceIds) {
        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        final Date date;
        try {
            date = formatter.parse(startDate);
        } catch (final ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }
        final ArrayList<TimeSeries> seriesL = timeSeriesService.ExtractforResource(date, noDays, resourceIds);
        final ArrayList<TimeSeries> seriesFL = new ArrayList<>();
        for (final TimeSeries series : seriesL) {
            final TimeSeries forecast = smoothingService.calculateSmoothing(series, noDaysForecast);
            forecast.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(forecast);
        }

        return DrawSeries.plotChart(seriesL, seriesFL, date, "Resources Use in the Past "
                + noDays
                + " Days and forecast for the next "
                + noDaysForecast
                + " days.", "chart", chartType);
    }

    private final ITimeSeriesService timeSeriesService;
    private final ISmoothingService smoothingService;
    private final IJobService jobService;
}
