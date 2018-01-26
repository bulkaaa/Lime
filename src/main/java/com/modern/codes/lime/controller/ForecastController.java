package com.modern.codes.lime.controller;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.order.Order;
import com.modern.codes.lime.report.DrawSeries;
import com.modern.codes.lime.report.Smoothing;
import com.modern.codes.lime.report.TimeSeries;
import com.modern.codes.lime.report.TimeSeriesProduct;
import com.modern.codes.lime.service.IJobService;
import io.swagger.annotations.ApiParam;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(path="/forecast")
public class ForecastController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(ForecastController.class);

    @Autowired
    IJobService jobService;

    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]>  generateForecast(
            @RequestBody @ApiParam("List of product ids") final List<String> productIds,
            @RequestParam final String startDate,
            @RequestParam final Integer noDays,
            @RequestParam final Integer noDaysForecast) {
        LOG.info("Generate request received product list: \n Date from: {} \n noDays: [] \n noDaysForecast: [] \n", productIds, startDate, noDays, noDaysForecast);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(startDate);
        } catch (final ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }

        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, date, noDays);
        final ArrayList<TimeSeries> seriesFL = new ArrayList<>();
        for (final TimeSeries series : seriesL) {
            final TimeSeries forecast = Smoothing.calculateSmoothing(series, noDaysForecast);
            forecast.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(forecast);
        }
        final byte[] bytes = DrawSeries.plot(seriesL, seriesFL, date, "Production in the Past " + noDays + " Days and forecast for the next " + noDaysForecast + " days.", "Sample_Chart");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void  sendForecast(
            @RequestBody @ApiParam("List of product ids") final List<String> productIds,
            @RequestParam final String startDate,
            @RequestParam final Integer noDays,
            @RequestParam final Integer noDaysForecast,
            @RequestParam final String email) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n noDaysForecast: [] \n email: {} \n", productIds, startDate, noDays, noDaysForecast, email);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(startDate);
        } catch (final ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }
        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, date, noDays);
        final ArrayList<TimeSeries> seriesFL = new ArrayList<>();
        for (final TimeSeries series : seriesL) {
            final TimeSeries forecast = Smoothing.calculateSmoothing(series, noDaysForecast);
            forecast.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(forecast);
        }
        DrawSeries.plot(seriesL, seriesFL, date, "Production in the Past " + noDays + " Days and forecast for the next " + noDaysForecast + " days.", "Sample_Chart");
        Order.SendEmail(email,"Forecast Email from LIME", "Please Find Report Attached", "./Sample_Chart.png");
    }
}