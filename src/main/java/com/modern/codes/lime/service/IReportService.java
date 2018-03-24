package com.modern.codes.lime.service;

import java.util.List;

public interface IReportService {
    byte[] getReportBytes(String startDate, Integer noDays, String chartType, List<String> productIds);
}
