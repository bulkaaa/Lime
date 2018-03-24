package com.modern.codes.lime.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.modern.codes.lime.service.IForecastService;
import com.modern.codes.lime.service.MailService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(path = "/forecast")
public class ForecastController extends BaseController {

    public ForecastController(final IForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @RequestMapping(value = "/product/generate",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> generateForecast(
            @RequestBody @ApiParam("List of product ids") final List<String> productIds,
            @RequestParam final String startDate, @RequestParam final Integer noDays,
            @RequestParam final Integer noDaysForecast, @RequestParam final String chartType) {
        LOG.info("Generate request received product list: \n Date from: {} \n noDays: [] \n noDaysForecast: [] \n  "
                 + "chartType: [] \n", productIds, startDate, noDays, noDaysForecast, chartType);

        final byte[] img = forecastService.plotForecast(startDate, noDays, noDaysForecast, chartType);

        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_PNG)
                             .body(img);
    }

    @RequestMapping(value = "/product/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendForecast(@RequestBody @ApiParam("List of product ids") final List<String> productIds,
                             @RequestParam final String startDate, @RequestParam final Integer noDays,
                             @RequestParam final Integer noDaysForecast, @RequestParam final String email,
                             @RequestParam final String chartType) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n noDaysForecast: [] \n email: "
                 + "{} \n chartType: [] \n", productIds, startDate, noDays, noDaysForecast, email, chartType);

        final byte[] bytes = forecastService.plotForecast(startDate, noDays, noDaysForecast, chartType);

        try {
            MailService.SendEmail(email, "Forecast Email from LIME", "Please Find Report Attached", bytes);
        } catch (final MessagingException e) {
            LOG.error("FAILED TO SEND REPORT.", e);
        }
    }
    private IForecastService forecastService;
    private static final Logger LOG = LoggerFactory.getLogger(ForecastController.class);
}