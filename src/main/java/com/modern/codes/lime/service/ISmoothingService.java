package com.modern.codes.lime.service;

import com.modern.codes.lime.class_models.TimeSeries;

/**
 * The interface Smoothing service.
 */
public interface ISmoothingService {

    /**
     * Calculate smoothing time series.
     *
     * @param series     the series
     * @param days_ahead the days ahead
     * @return the time series
     */
    TimeSeries calculateSmoothing(TimeSeries series, int days_ahead);
}
