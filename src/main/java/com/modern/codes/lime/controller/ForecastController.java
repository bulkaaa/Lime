package com.modern.codes.lime.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.class_models.DrawSeries;
import com.modern.codes.lime.service.ISmoothingService;
import com.modern.codes.lime.class_models.TimeSeries;
import com.modern.codes.lime.service.TimeSeriesService;
import com.modern.codes.lime.service.IJobService;
import com.modern.codes.lime.tools.MailTools;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/forecast")
public class ForecastController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(ForecastController.class);

    @Autowired
    IJobService jobService;

    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> generateForecast(
            @RequestBody @ApiParam("List of product ids") final List<String> productIds,
            @RequestParam final String startDate, @RequestParam final Integer noDays,
            @RequestParam final Integer noDaysForecast, @RequestParam final String chartType) {
        LOG.info(
                "Generate request received product list: \n Date from: {} \n noDays: [] \n noDaysForecast: [] \n  "
                + "chartType: [] \n",
                productIds, startDate, noDays, noDaysForecast, chartType);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(startDate);
        } catch (final ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }

        final ArrayList<TimeSeries> seriesL = TimeSeriesService.Extract(jobService, date, noDays);
        final ArrayList<TimeSeries> seriesFL = new ArrayList<>();
        for (final TimeSeries series : seriesL) {
            final TimeSeries forecast = ISmoothingService.calculateSmoothing(series, noDaysForecast);
            forecast.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(forecast);
        }
        final byte[] bytes = DrawSeries.plotChart(seriesL, seriesFL, date, "Production in the Past "
                                                                           + noDays
                                                                           + " Days and forecast for the next "
                                                                           + noDaysForecast
                                                                           + " days.", "Sample_Chart", chartType);

        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_PNG)
                             .body(bytes);
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendForecast(@RequestBody @ApiParam("List of product ids") final List<String> productIds,
                             @RequestParam final String startDate, @RequestParam final Integer noDays,
                             @RequestParam final Integer noDaysForecast, @RequestParam final String email,
                             @RequestParam final String chartType) {
        LOG.info(
                "Send request received product list: \n Date from: {} \n noDays: [] \n noDaysForecast: [] \n email: "
                + "{} \n chartType: [] \n",
                productIds, startDate, noDays, noDaysForecast, email, chartType);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(startDate);
        } catch (final ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }
        final ArrayList<TimeSeries> seriesL = TimeSeriesService.Extract(jobService, date, noDays);
        final ArrayList<TimeSeries> seriesFL = new ArrayList<>();
        for (final TimeSeries series : seriesL) {
            final TimeSeries forecast = ISmoothingService.calculateSmoothing(series, noDaysForecast);
            forecast.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(forecast);
        }

        DrawSeries.plotChart(seriesL, seriesFL, date, "Production in the Past "
                                                      + noDays
                                                      + " Days and forecast for the next "
                                                      + noDaysForecast
                                                      + " days.", "Sample_Chart", chartType);
        MailTools.SendEmail(email, "Forecast Email from LIME", "Please Find Report Attached", "./Sample_Chart.png");
    }
}