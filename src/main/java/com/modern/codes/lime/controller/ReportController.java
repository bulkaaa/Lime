package com.modern.codes.lime.controller;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.order.Order;
import com.modern.codes.lime.report.*;
import com.modern.codes.lime.service.IJobService;
import com.modern.codes.lime.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path="/report")
public class ReportController {

    //TODO
    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);


    @Autowired
    IJobService jobService;

    @Autowired
    IProductService productService;



    @RequestMapping(value = "/generate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Generate report for products")
    public ResponseEntity<byte[]> generate(
            @RequestParam final String startDate,
            @RequestParam final Integer noDays,
            @RequestBody @ApiParam(value = "Products ids list") final List<String> productIds) {
        LOG.info("Generate request received product list: \n Date from: {} \n noDays: [] \n", startDate, noDays);

        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
        } catch (ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }

        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, date, noDays);
        final byte [] bytes = DrawSeries.plot(seriesL, new ArrayList<>(Collections.singletonList(new TimeSeries())), date, "Production in past "+noDays+" days", "Sample_Chart");

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Generate and send report for products")
    public void send(
            @RequestParam final String startDate,
            @RequestParam final String email,
            @RequestParam final Integer noDays,
            @RequestBody @ApiParam(value = "Products ids list") final List<String> productIds) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n email: {}", startDate, noDays, email);

        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
        } catch (ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }

        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, date, noDays);
        DrawSeries.plot(seriesL, new ArrayList<TimeSeries>(), date, "Production in past "+noDays+" days", "Sample_Chart");
        Order.SendEmail(email,"Report Email form LIME", "Please Find Report Attached", "Sample_Chart");

    }

    @GetMapping(path = "/test")
    public String Test(){
        final ArrayList<TimeSeries> seriesFL = new ArrayList<TimeSeries>();
        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, new Date(), 8);
        //final String filename = DrawSeries.plot(seriesL, seriesFL, new Date(),"Production");
        //Order.SendEmail("aleksandrabulka1@gmail.com","Report Email form LIME", "Please Find Report Attached", filename);

        return "Email Sent";
    }
}
