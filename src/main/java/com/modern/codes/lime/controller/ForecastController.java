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

@RestController()
@RequestMapping(path="/forecast")
public class ForecastController {

    //TODO
    //When getting request from front-end to generate page -> return the list of products (names)

    //When user in front-end will select Products and Date to start series (e.g. today) and how many days back we get, and how many days are forecasted and email of receiver of report
    //this means we send back to backend: List of Product ids, a Date, an Integer, another Integer, and a String
    //we can call them LIST, DATE, NO_DAYS, NO_DAYS_FORECAST, EMAIL

    //Then I get from DB data for the given product IDs
    //ArrayList<TimeSeries> seriesL = tsp.Extract(jobService, DATE, NO_DAYS, LIST);

    //generate forecast
    /*  for (TimeSeries series : seriesL) {
        TimeSeries fcst = Smoothing.calculateSmoothing(series, NO_DAYS_FORECAST);
        fcst.setLabel(series.getLabel() + " Forecast");
        seriesFL.add(fcst);
    }
*/
    //then I generate plot from them
    //String filename = draw.plot(seriesL, new TimeSeries(), DATE, "Production in the Past" + NO_DAYS + "Days and forecast for the next " + NO_DAYS_FORECAST);

    //And I send it
    //  ord.SendEmail(EMAIL,"Report Email form LIME", "Please Find Report Attached", filename);


    @Autowired
    JobService jobService;
    @Autowired
    DBPopulator pop;

    @GetMapping(path = "/populate")
    public String populate() {
        pop.populate();
        return "DB reset completed";
    }

    @GetMapping(path = "/test")
    public String Test() {
        final String email = "aleksandrabulka1@gmail.com";
        final Integer days_past = 8;
        final Integer days_forecast = 2;
        ArrayList<TimeSeries> seriesFL = new ArrayList<TimeSeries>();
        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, new Date(), days_past);

       for (TimeSeries series : seriesL) {
            TimeSeries fcst = Smoothing.calculateSmoothing(series, days_forecast);
            fcst.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(fcst);
        }

        String filename = DrawSeries.plot(seriesL, seriesFL, new Date(),"Production in the Past" + days_past + "Days and forecast for the next " + days_forecast);
        Order.SendEmail(email, "Report Email form LIME", "Please Find Report Attached", filename);

        return "Email Sent";
    }
}