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
