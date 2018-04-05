package com.modern.codes.lime.service;

import java.util.List;

/**
 * The interface Report service.
 */
public interface IReportService {
    /**
     * Get report bytes byte [ ].
     *
     * @param startDate  the start date
     * @param noDays     the no days
     * @param chartType  the chart type
     * @param productIds the product ids
     * @return the byte [ ]
     */
    byte[] getReportBytes(String startDate, Integer noDays, String chartType, List<String> productIds);
}
