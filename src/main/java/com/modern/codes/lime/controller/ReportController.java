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

@RestController
@RequestMapping(path = "/report")
public class ReportController {

    public ReportController(final IReportService reportService) {
        this.reportService = reportService;
    }

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

    @RequestMapping(value = "/product/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Generate and send class_models for products")
    public void send(@RequestParam final String startDate, @RequestParam final String email,
                     @RequestParam final Integer noDays, @RequestParam final String chartType,
                     @RequestBody @ApiParam("Products ids list") final List<String> productIds) {
        LOG.info("Send request received product list: \n Date from: {} \n noDays: [] \n email: {} \n productIds: [] \n"
                 + " chartType: [] \n", startDate, noDays, email, productIds, chartType);

        final byte[] bytes = reportService.getReportBytes(startDate, noDays, chartType, productIds);
        try {
            MailService.SendEmail(email, "Report Email form LIME", "Please Find Report Attached", bytes);
        } catch (final MessagingException e) {
            LOG.error("FAILED TO SEND REPORT", e);
        }

    }

    private final IReportService reportService;

    private static final Logger LOG = LoggerFactory.getLogger(ReportController.class);
}
