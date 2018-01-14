package com.modern.codes.lime.controller;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.exception.UnprocessableEntityException;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.order.Order;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.report.*;
import com.modern.codes.lime.service.IJobService;
import com.modern.codes.lime.service.IProductService;
import com.modern.codes.lime.tools.ParseTools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
    public Boolean generate(
            @RequestParam final String email,
            @RequestParam final String startDate,
            @RequestParam final Integer noDays,
            @RequestBody @ApiParam(value = "Products ids list") final List<String> productIds) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n email: {} \n", startDate, noDays, email);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date;
        try {
            date = formatter.parse(startDate);
        } catch (ParseException e) {
            throw new InvalidRequestException("Invalid date format.", null, Locale.ENGLISH);
        }

        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, date, noDays);
        final String filename = DrawSeries.plot(seriesL, new ArrayList<>(Collections.singletonList(new TimeSeries())), date, "Production in past "+noDays+" days");
        Order.SendEmail(email,"Report Email form LIME", "Please Find Report Attached", filename);

        return true;
    }
    @GetMapping(path = "/test")
    public String Test(){
        final ArrayList<TimeSeries> seriesFL = new ArrayList<TimeSeries>();
        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, new Date(), 8);
        final String filename = DrawSeries.plot(seriesL, seriesFL, new Date(),"Production");
        Order.SendEmail("aleksandrabulka1@gmail.com","Report Email form LIME", "Please Find Report Attached", filename);

        return "Email Sent";
    }
}
