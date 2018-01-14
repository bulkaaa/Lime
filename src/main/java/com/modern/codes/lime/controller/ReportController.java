package com.modern.codes.lime.controller;

import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.order.Order;
import com.modern.codes.lime.report.*;
import com.modern.codes.lime.service.IJobService;
import com.modern.codes.lime.service.IProductService;
import com.modern.codes.lime.service.JobService;
import com.modern.codes.lime.tools.DBPopulator;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    DBPopulator pop;

    @GetMapping(path = "/populate")
    public String populate(){
        pop.populate();
        return "DB reset completed";
    }

    @RequestMapping(value = "/get-products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all products", notes = "Fetches all products from DB for reports", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Fetch all products")})
    @ResponseBody
    public String getProducts() {
        LOG.info("Fetch all product request received in Report Controller");
        return ParseTools.parseToJson(productService.findAll(), Product.class);
    }

    @ResponseBody
    public Boolean send(
            @Validated @RequestBody @ApiParam(value = "Map of Resources and amout") final
                    List<Product> productList, @RequestParam(value="date") final Date date, @RequestParam(value="nodays") final Integer noDays,@RequestParam(value="email") final String email,
            @Validated final BindingResult bindingResult, @Validated final UriComponentsBuilder b) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n email: {} \n", productList, date, noDays, email);

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
