package com.modern.codes.lime.service;

import org.springframework.web.bind.annotation.RequestParam;

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
     * @return the byte [ ]
     */
    byte[] plotForecast(@RequestParam String startDate, @RequestParam Integer noDays,
                        @RequestParam Integer noDaysForecast, @RequestParam String chartType);
}
