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
import org.springframework.web.bind.annotation.RestController;

import com.modern.codes.lime.service.IReportService;
import com.modern.codes.lime.service.MailService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * The type Report controller.
 */
@RestController
@RequestMapping(path = "/report")
public class ReportController {

    /**
     * Instantiates a new Report controller.
     *
     * @param reportService the report service
     */
    public ReportController(final IReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * Generate response entity.
     *
     * @param startDate  the start date
     * @param noDays     the no days
     * @param chartType  the chart type
     * @param productIds the product ids
     * @return the response entity
     */
    @RequestMapping(value = "/product/generate",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Generate class_models for products")
    public ResponseEntity<byte[]> generate(@RequestParam final String startDate, @RequestParam final Integer noDays,
                                           @RequestParam final String chartType,
                                           @RequestBody @ApiParam("Products ids list") final List<String> productIds) {
        LOG.info("Generate request received product list: \n Date from: {} \n noDays: [] \n productIds: [] \n "
                 + "chartType: [] \n", startDate, noDays, productIds, chartType);

        final byte[] bytes = reportService.getReportBytes(startDate, noDays, chartType, productIds);

        return ResponseEntity.ok()
                             .contentType(MediaType.IMAGE_PNG)
                             .body(bytes);
    }

    /**
     * Generate response entity.
     *
     * @param startDate  the start date
     * @param noDays     the no days
     * @param chartType  the chart type
     * @param resourcesIds the resource ids
     * @return the response entity
     */
    @RequestMapping(value = "/resource/generate",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Generate class_models for resources")
    public ResponseEntity<byte[]> generate2(@RequestParam final String startDate, @RequestParam final Integer noDays,
                                           @RequestParam final String chartType,
                                           @RequestBody @ApiParam("Resource ids list") final List<String> resourcesIds) {
        LOG.info("Generate request received product list: \n Date from: {} \n noDays: [] \n resourceIds: [] \n "
                + "chartType: [] \n", startDate, noDays, resourcesIds, chartType);

        final byte[] bytes = reportService.getReportBytesResource(startDate, noDays, chartType, resourcesIds);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(bytes);
    }

    /**
     * Send.
     *
     * @param startDate  the start date
     * @param email      the email
     * @param noDays     the no days
     * @param chartType  the chart type
     * @param productIds the product ids
     */
    @RequestMapping(value = "/product/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Generate and send class_models for products")
    public void send(@RequestParam final String startDate, @RequestParam final String email,
                     @RequestParam final Integer noDays, @RequestParam final String chartType,
                     @RequestBody @ApiParam("Products ids list") final List<String> productIds) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n email: {} \n productIds: [] \n"
                 + " chartType: [] \n", startDate, noDays, email, productIds, chartType);

        final byte[] bytes = reportService.getReportBytes(startDate, noDays, chartType, productIds);
        try {
            MailService.SendEmail(email, "Report Email form LIME", "Please Find Report Attached", "chart");
        } catch (final MessagingException e) {
            LOG.error("FAILED TO SEND REPORT", e);
        }

    }

    /**
     * Send (for resources reports)
     *
     * @param startDate  the start date
     * @param email      the email
     * @param noDays     the no days
     * @param chartType  the chart type
     * @param resourceIds the resource ids
     */
    @RequestMapping(value = "/resource/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Generate and send class_models for resources")
    public void send2(@RequestParam final String startDate, @RequestParam final String email,
                     @RequestParam final Integer noDays, @RequestParam final String chartType,
                     @RequestBody @ApiParam("Resources ids list") final List<String> resourceIds) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n email: {} \n resourceIds: [] \n"
                + " chartType: [] \n", startDate, noDays, email, resourceIds, chartType);

        final byte[] bytes = reportService.getReportBytesResource(startDate, noDays, chartType, resourceIds);
        try {
            MailService.SendEmail(email, "Report Email form LIME", "Please Find Report Attached", "chart");
        } catch (final MessagingException e) {
            LOG.error("FAILED TO SEND REPORT", e);
        }

    }
    private final IReportService reportService;

    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);
}
