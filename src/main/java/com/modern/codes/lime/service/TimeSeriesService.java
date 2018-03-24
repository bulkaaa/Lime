package com.modern.codes.lime.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.modern.codes.lime.class_models.TimeSeries;
import com.modern.codes.lime.pojo.JobPOJO;

@Service
public class TimeSeriesService implements ITimeSeriesService {

    TimeSeriesService(final IJobService jobService) {
        this.jobService = jobService;
    }

    @Override
    public ArrayList<TimeSeries> Extract(final Date StartDate, final Integer Days, final List<String> ProductsIds) {
        final ArrayList<TimeSeries> series = new ArrayList<>();
        for (final String id : ProductsIds) {
            final List<JobPOJO> list = jobService.findByProductId(id);
            if (!list.isEmpty()) {
                final TimeSeries ts = ExtractProduct(list, StartDate, Days);
                final String label = list.get(0)
                                         .getPOJOProduct()
                                         .getName();
                ts.setLabel(label);
                series.add(ts);
            }
        }
        return series;
    }

    @Override
    public ArrayList<TimeSeries> Extract(final IJobService service, final Date StartDate, final Integer Days) {
        return Extract(StartDate, Days, getProductId(service));
    }

    private static TimeSeries ExtractProduct(final List<JobPOJO> list, final Date StartDate, final Integer Days) {

        final TimeSeries ts = new TimeSeries();
        for (int i = 0; i < Days; i++) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(StartDate);
            cal.add(Calendar.DATE, -Days + i);
            final Date dateBefore = cal.getTime();

            final Calendar cal2 = Calendar.getInstance();
            cal2.setTime(StartDate);
            cal2.add(Calendar.DATE, -Days + i + 1);
            final Date dateBeforeLimit = cal2.getTime();
            final List<JobPOJO> day = new ArrayList<>();
            for (final JobPOJO job : list) {
                if (job.getEndDate()
                       .after(dateBefore) && job.getEndDate()
                                                .before(dateBeforeLimit)) {
                    day.add(job);
                }
            }
            double value = 0;
            for (final JobPOJO j : day) {
                value += j.getResultValue();
            }
            ts.add((int) value);
        }
        return ts;
    }

    private static List<String> getProductId(final IJobService service) {
        final List<String> IDList = new ArrayList<>();
        final List<JobPOJO> list = service.findAll();
        while (!list.isEmpty()) {
            final String id = list.get(0)
                                  .getProduct()
                                  .getId();
            if (!IDList.contains(id)) {
                IDList.add(id);
            }
            list.remove(0);
        }
        return IDList;
    }

    private final IJobService jobService;
}