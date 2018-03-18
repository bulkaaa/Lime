package com.modern.codes.lime.service;

import com.modern.codes.lime.class_models.TimeSeries;

public interface ISmoothingService {

    TimeSeries calculateSmoothing(TimeSeries series, int days_ahead);
}
