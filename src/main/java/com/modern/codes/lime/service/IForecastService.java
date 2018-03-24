package com.modern.codes.lime.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface IForecastService {

    byte [] plotForecast(@RequestParam String startDate, @RequestParam Integer noDays, @RequestParam Integer noDaysForecast,
                         @RequestParam String chartType);
}
