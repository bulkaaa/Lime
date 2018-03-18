package com.modern.codes.lime.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.modern.codes.lime.class_models.TimeSeries;

public interface ITimeSeriesService {
    ArrayList<TimeSeries> Extract(Date StartDate, Integer Days, List<String> ProductsIds);

    ArrayList<TimeSeries> Extract(IJobService service, Date StartDate, Integer Days);
}
