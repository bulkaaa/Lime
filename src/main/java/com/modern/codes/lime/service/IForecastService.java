package com.modern.codes.lime.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * The interface Forecast service.
 */
public interface IForecastService {

    /**
     * Plot forecast byte [ ].
     *
     * @param startDate      the start date
     * @param noDays         the no days
     * @param noDaysForecast the no days forecast
     * @param chartType      the chart type
     * @param productIds     product ids
     * @return the byte [ ]
     */
    byte[] plotForecast(@RequestParam String startDate, @RequestParam Integer noDays,
                        @RequestParam Integer noDaysForecast, @RequestParam String chartType,
                        @RequestParam  List<String> productIds);


    /**
     * Plot forecast byte [ ].
     *
     * @param startDate      the start date
     * @param noDays         the no days
     * @param noDaysForecast the no days forecast
     * @param chartType      the chart type
     * @param resourceIds    resource ids
     * @return the byte [ ]
     */
    byte[] plotForecastResource(@RequestParam String startDate, @RequestParam Integer noDays,
                                       @RequestParam Integer noDaysForecast, @RequestParam String chartType,
                                       @RequestParam  List<String> resourceIds);
}
