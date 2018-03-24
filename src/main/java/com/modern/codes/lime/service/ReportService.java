package com.modern.codes.lime.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.class_models.DrawSeries;
import com.modern.codes.lime.class_models.TimeSeries;
import com.modern.codes.lime.exception.InvalidRequestException;

@Service
public class ReportService implements IReportService {

    @Autowired
    public ReportService(final ITimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }

    @Override
    public byte[] getReportBytes(final String startDate, final Integer noDays, final String chartType,
                                 final List<String> productIds) {
        final Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
        } catch (final ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }

        final ArrayList<TimeSeries> seriesL = timeSeriesService.Extract(date, noDays, productIds);
        return DrawSeries.plotChart(seriesL, new ArrayList<>(), date, "Production in past " + noDays + " days",
                                    "Sample_Chart", chartType);
    }

    private final ITimeSeriesService timeSeriesService;
}
