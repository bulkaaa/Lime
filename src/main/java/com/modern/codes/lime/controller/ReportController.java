package com.modern.codes.lime.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.class_models.DrawSeries;
import com.modern.codes.lime.class_models.TimeSeries;
import com.modern.codes.lime.service.ITimeSeriesService;
import com.modern.codes.lime.tools.MailTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/report")
public class ReportController {

    @Autowired
    public ReportController(final ITimeSeriesService timeSeriesService){
        this.timeSeriesService = timeSeriesService;
    }

    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Generate class_models for products")
    public ResponseEntity<byte[]> generate(@RequestParam final String startDate, @RequestParam final Integer noDays,
                                           @RequestParam final String chartType,
                                           @RequestBody @ApiParam("Products ids list") final List<String> productIds) {
        LOG.info(
                "Generate request received product list: \n Date from: {} \n noDays: [] \n productIds: [] \n "
                + "chartType: [] \n",
                startDate, noDays, productIds, chartType);

        final byte[] bytes = getBytes(startDate, noDays, chartType, productIds);

        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_PNG)
                             .body(bytes);
    }

    // FIXME: move to service layer, change name
    private byte[] getBytes(final String startDate, final Integer noDays,
                            final String chartType,
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

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Generate and send class_models for products")
    public void send(@RequestParam final String startDate, @RequestParam final String email,
                     @RequestParam final Integer noDays, @RequestParam final String chartType,
                     @RequestBody @ApiParam("Products ids list") final List<String> productIds) {
        LOG.info(
                "Send request received product list: \n Date from: {} \n noDays: [] \n email: {} \n productIds: [] \n"
                + " chartType: [] \n",
                startDate, noDays, email, productIds, chartType);

        final byte[] bytes = getBytes(startDate, noDays, chartType, productIds);
        try {
            MailTools.SendEmail(email, "Report Email form LIME", "Please Find Report Attached", bytes);
        } catch (final MessagingException e) {
            LOG.error("FAILED TO SEND REPORT", e);
        }

    }

    private final ITimeSeriesService timeSeriesService;

    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);


}
