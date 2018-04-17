package com.modern.codes.lime.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.modern.codes.lime.class_models.TimeSeries;

/**
 * The interface Time series service.
 */
public interface ITimeSeriesService {
    /**
     * Extract array list.
     *
     * @param StartDate   the start date
     * @param Days        the days
     * @param ProductsIds the products ids
     * @return the array list
     */
    ArrayList<TimeSeries> Extract(Date StartDate, Integer Days, List<String> ProductsIds);

    /**
     * Extract array list - this time for resources
     *
     *
     * @param StartDate the start date
     * @param Days      the days
     * @param ResourceIds selected resources
     * @return the array list
     */
    ArrayList<TimeSeries> ExtractforResource(Date StartDate, Integer Days, List<String> ResourceIds);



}