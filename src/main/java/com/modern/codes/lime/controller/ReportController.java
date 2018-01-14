package com.modern.codes.lime.controller;

import com.modern.codes.lime.order.Order;
import com.modern.codes.lime.report.*;
import com.modern.codes.lime.service.JobService;
import com.modern.codes.lime.service.UserService;
import com.modern.codes.lime.tools.DBPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping(path="/report")
public class ReportController {


    //TODO
    //When getting request from front-end to generate page -> return the list of products (names)

    //When user in front-end will select Products and Date to start series (e.g. today) and how many days back we get
    //this means we send back to backend: List of Product ids, a Date, an Integer
    //we can call them LIST, DATE, NO_DAYS

    //Then I get from DB data for the given product IDs
    //ArrayList<TimeSeries> seriesL = tsp.Extract(jobService, DATE, NO_DAYS, LIST);

    //then I generate plot from them
    //String filename = draw.plot(seriesL, new TimeSeries(), DATE, "Production in the Past" + NO_DAYS + "Days");

    //And I send it
    //  ord.SendEmail("aleksandrabulka1@gmail.com","Report Email form LIME", "Please Find Report Attached", filename);


    @Autowired
    DrawSeries draw;
    @Autowired
    TimeSeriesProduct tsp;
    @Autowired
    JobService jobService;
    @Autowired
    Smoothing smoothing;

    @Autowired
    DBPopulator pop;

    @GetMapping(path = "/populate")
    public String populate(){
        pop.populate();
        return "DB reset completed";
    }
    @GetMapping(path = "/test")
    public String Test(){
        Integer no_days = 8;
        ArrayList<TimeSeries> seriesFL = new ArrayList<TimeSeries>();
        ArrayList<TimeSeries> seriesL = tsp.Extract(jobService, new Date(),no_days);
        String filename = draw.plot(seriesL, seriesFL, new Date(), "Production in the Past "+no_days.toString()+" Days");
        ord.SendEmail("aleksandrabulka1@gmail.com","Report Email form LIME", "Please Find Report Attached", filename);

        ArrayList<TimeSeries> seriesL = tsp.Extract(jobService, new Date(),8);
//        for (TimeSeries series2:seriesL) {
//            TimeSeries fcst = smoothing.calculateSmoothing(series2, 5);
//            fcst.setLabel(series2.getLabel()+ " Forecast");
//            seriesFL.add(fcst);
//        };


        String filename = draw.plot(seriesL, seriesFL, new Date());
      Order.SendEmail("aleksandrabulka1@gmail.com","Report Email form LIME", "Please Find Report Attached", filename);

        return "Email Sent";
    }
}
