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

/**
 * The type Forecast controller.
 */
@RestController
@RequestMapping(path = "/forecast")
public class ForecastController extends BaseController {

    /**
     * Instantiates a new Forecast controller.
     *
     * @param forecastService the forecast service
     */
    public ForecastController(final IForecastService forecastService) {
        this.forecastService = forecastService;
    }

    /**
     * Generate forecast response entity.
     *
     * @param productIds     the product ids
     * @param startDate      the start date
     * @param noDays         the no days
     * @param noDaysForecast the no days forecast
     * @param chartType      the chart type
     * @return the response entity
     */
    @RequestMapping(value = "/product/generate",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> generateForecast(
            @RequestBody @ApiParam("List of product ids") final List<String> productIds,
            @RequestParam final String startDate, @RequestParam final Integer noDays,
            @RequestParam final Integer noDaysForecast, @RequestParam final String chartType) {
        LOG.info("Generate request received product list: {} \n Date from: {} \n noDays: {} \n noDaysForecast: {} \n"
                 + "chartType: {} \n", productIds, startDate, noDays, noDaysForecast, chartType);

        final byte[] img = forecastService.plotForecast(startDate, noDays, noDaysForecast, chartType, productIds);

        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_PNG)
                             .body(img);
    }

    /**
     * Generate forecast response entity for resources
     *
     * @param resourceIds     the resource ids
     * @param startDate      the start date
     * @param noDays         the no days
     * @param noDaysForecast the no days forecast
     * @param chartType      the chart type
     * @return the response entity
     */
    @RequestMapping(value = "/resource/generate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> generateForecast2(
            @RequestBody @ApiParam("List of resource ids") final List<String> resourceIds,
            @RequestParam final String startDate, @RequestParam final Integer noDays,
            @RequestParam final Integer noDaysForecast, @RequestParam final String chartType) {
        LOG.info("Generate request received resource list: {} \n Date from: {} \n noDays: {} \n noDaysForecast: {} \n"
                + "chartType: {} \n", resourceIds, startDate, noDays, noDaysForecast, chartType);

        final byte[] img = forecastService.plotForecastResource(startDate, noDays, noDaysForecast, chartType, resourceIds);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(img);
    }
    /**
     * Send forecast.
     *
     * @param productIds     the product ids
     * @param startDate      the start date
     * @param noDays         the no days
     * @param noDaysForecast the no days forecast
     * @param email          the email
     * @param chartType      the chart type
     */
    @RequestMapping(value = "/product/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendForecast(@RequestBody @ApiParam("List of product ids") final List<String> productIds,
                             @RequestParam final String startDate, @RequestParam final Integer noDays,
                             @RequestParam final Integer noDaysForecast, @RequestParam final String email,
                             @RequestParam final String chartType) {
        LOG.info("Send request received product list: {} \n Date from: {} \n noDays: {} \n noDaysForecast: {} \n email: "
                 + "{} \n chartType: {} \n", productIds, startDate, noDays, noDaysForecast, email, chartType);

        final byte[] bytes = forecastService.plotForecast(startDate, noDays, noDaysForecast, chartType, productIds);

        try {
            MailService.SendEmail(email, "Forecast Email from LIME", "Please Find Report Attached", "./chart.png");
        } catch (final MessagingException e) {
            LOG.error("FAILED TO SEND REPORT.", e);
        }
    }

    /**
     * Send forecast (for resources)
     *
     * @param resourceIds     the resource ids
     * @param startDate      the start date
     * @param noDays         the no days
     * @param noDaysForecast the no days forecast
     * @param email          the email
     * @param chartType      the chart type
     */
    @RequestMapping(value = "/resource/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void sendForecast2(@RequestBody @ApiParam("List of resource ids") final List<String> resourceIds,
                             @RequestParam final String startDate, @RequestParam final Integer noDays,
                             @RequestParam final Integer noDaysForecast, @RequestParam final String email,
                             @RequestParam final String chartType) {
        LOG.info("Send request received resources list: {} \n Date from: {} \n noDays: {} \n noDaysForecast: {} \n email: "
                + "{} \n chartType: {} \n", resourceIds, startDate, noDays, noDaysForecast, email, chartType);

        final byte[] bytes = forecastService.plotForecastResource(startDate, noDays, noDaysForecast, chartType, resourceIds);

        try {
            MailService.SendEmail(email, "Forecast Email from LIME", "Please Find Report Attached", "./chart.png");
        } catch (final MessagingException e) {
            LOG.error("FAILED TO SEND REPORT.", e);
        }
    }

    private IForecastService forecastService;
    private static final Logger LOG = LoggerFactory.getLogger(ForecastController.class);
}