package com.modern.codes.lime.controller;

import com.modern.codes.lime.order.Order;
import com.modern.codes.lime.report.*;
import com.modern.codes.lime.service.JobService;
import com.modern.codes.lime.tools.DBPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping(path="/report")
public class ReportController {

    //TODO
    //When getting request from front-end to generate page -> return the list of products (names)

    //When user in front-end will select Products and Date to start series (e.g. today) and how many days back we get, and email of receiver of report
    //this means we send back to backend: List of Product ids, a Date, an Integer, and a String
    //we can call them LIST, DATE, NO_DAYS, EMAIL

    //Then I get from DB data for the given product IDs
    //ArrayList<TimeSeries> seriesL = tsp.Extract(jobService, DATE, NO_DAYS, LIST);

    //then I generate plot from them
    //String filename = draw.plot(seriesL, new TimeSeries(), DATE, "Production in the Past" + NO_DAYS + "Days");

    //And I send it
    //  ord.SendEmail(EMAIL,"Report Email form LIME", "Please Find Report Attached", filename);

    @Autowired
    TSGenerator tsgen;
    @Autowired
    JobService jobService;

    @Autowired
    DBPopulator pop;

    @GetMapping(path = "/populate")
    public String populate(){
        pop.populate();
        return "DB reset completed";
    }
    @GetMapping(path = "/test")
    public String Test(){
       // ArrayList<TimeSeries> seriesL = new ArrayList<TimeSeries>();
        final ArrayList<TimeSeries> seriesFL = new ArrayList<TimeSeries>();
        //tsgen.loadSampleTSList(seriesL);

        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, new Date(), 8);
//        for (TimeSeries series2:seriesL) {
//            TimeSeries fcst = smoothing.calculateSmoothing(series2, 5);
//            fcst.setLabel(series2.getLabel()+ " Forecast");
//            seriesFL.add(fcst);
//        };


        final String filename = DrawSeries.plot(seriesL, seriesFL, new Date());
        Order.SendEmail("aleksandrabulka1@gmail.com","Report Email form LIME", "Please Find Report Attached", filename);

        return "Email Sent";
    }
}
