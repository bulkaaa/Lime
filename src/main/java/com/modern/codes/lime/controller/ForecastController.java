package com.modern.codes.lime.controller;

        import com.modern.codes.lime.model.Product;
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
        import java.util.Collections;
        import java.util.Date;
        import java.util.List;

        import io.swagger.annotations.ApiOperation;
        import io.swagger.annotations.ApiParam;
        import io.swagger.annotations.ApiResponse;
        import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path="/forecast")
public class ForecastController {
    private static final Logger LOG = LoggerFactory.getLogger(ForecastController.class);

    @Autowired
    IJobService jobService;
    @Autowired
    IProductService productService;

    @RequestMapping(value = "/get-products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all products", notes = "Fetches all products from DB for reports", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Fetch all products")})
    @ResponseBody
    public String getProducts() {
        LOG.info("Fetch all product request received in Forecast Controller");
        return ParseTools.parseToJson(productService.findAll(), Product.class);
    }

    @ResponseBody
    public Boolean send(
            @Validated @RequestBody @ApiParam(value = "Map of Resources and amout") final
            List<Product> productList, @RequestParam(value="date") final Date date, @RequestParam(value="nodays") final Integer noDays,
            @RequestParam(value="nodays-forecast") final Integer noDaysForecast, @RequestParam(value="email") final String email,
            @Validated final BindingResult bindingResult, @Validated final UriComponentsBuilder b) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n noDaysForecast: [] \n email: {} \n", productList, date, noDays, noDaysForecast, email);

        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, date, noDays);
        final ArrayList<TimeSeries> seriesFL = new ArrayList<>();
        for (final TimeSeries series : seriesL) {
            final TimeSeries forecast = Smoothing.calculateSmoothing(series, noDaysForecast);
            forecast.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(forecast);
        }
        final String filename = DrawSeries.plot(seriesL, seriesFL, date, "Production in the Past " + noDays + " Days and forecast for the next " + noDaysForecast + " days.");
        Order.SendEmail(email,"Forecast Email from LIME", "Please Find Report Attached", filename);

        return true;
    }


    @GetMapping(path = "/test")
    public String Test() {
        final Integer days_past = 8;
        final Integer days_forecast = 2;
        final ArrayList<TimeSeries> seriesFL = new ArrayList<>();
        final ArrayList<TimeSeries> seriesL = TimeSeriesProduct.Extract(jobService, new Date(), days_past);

       for (final TimeSeries series : seriesL) {
            final TimeSeries fcst = Smoothing.calculateSmoothing(series, days_forecast);
            fcst.setLabel(series.getLabel() + " Forecast");
            seriesFL.add(fcst);
        }

        final String filename = DrawSeries.plot(seriesL, seriesFL, new Date(), "Production in the Past" + days_past + "Days and forecast for the next " + days_forecast);

        final String email = "aleksandrabulka1@gmail.com";
        Order.SendEmail(email, "Report Email form LIME", "Please Find Report Attached", filename);

        return "Email Sent";
    }
}