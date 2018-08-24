package com.modern.codes.lime.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.class_models.TimeSeries;
import com.modern.codes.lime.pojo.JobPOJO;

/**
 * The type Time series service.
 */
@Service
public class TimeSeriesService implements ITimeSeriesService {

    /**
     * Instantiates a new Time series service.
     *
     * @param jobService the job service
     * @param formulaService the formula service
     * @param resourceService the resource service
     */
    TimeSeriesService(final IJobService jobService, final FormulaService formulaService,
                      final ResourceService resourceService) {
        this.jobService = jobService;
        this.formulaService = formulaService;
        this.resourceService = resourceService;

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

    private static TimeSeries ExtractProduct(final List<JobPOJO> list, final Date StartDate, final Integer Days) {

        final TimeSeries ts = new TimeSeries();
        for (int i = 0; i < Days; i++) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(StartDate);
            cal.add(Calendar.DATE, -Days + i + 1);
            final Date dateBefore = cal.getTime();

            final Calendar cal2 = Calendar.getInstance();
            cal2.setTime(StartDate);
            cal2.add(Calendar.DATE, -Days + i + 2);
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
            ts.add(value);
        }
        return ts;
    }


    @Override
    public ArrayList<TimeSeries> ExtractforResource(final Date StartDate,
                                                final Integer Days, final List<String> ResourceIds)
    {
        final List<String> ids = ResourceIds;

        final ArrayList<TimeSeries> series = new ArrayList<TimeSeries>();
        final List<JobPOJO> list = jobService.findAll();
        if (!list.isEmpty()) {
            //List of Jobs
            for (final String resID : ResourceIds) {
                final TimeSeries ts = ExtractResource(list, resID, StartDate, Days);
                final String label = resourceService.findById(resID).getName();
                ts.setLabel(label);
                series.add(ts);
            }
        }

    return series;
}



    private TimeSeries ExtractResource(final List<JobPOJO> list,
                                              final String ResID, final Date StartDate,
                                              final Integer Days){
        final TimeSeries ts = new TimeSeries();
        for (int i = 0; i < Days; i++) {
            final Calendar cal = Calendar.getInstance();
            cal.setTime(StartDate);
            cal.add(Calendar.DATE, -Days + i + 1);
            final Date dateBefore = cal.getTime();
            final Calendar cal2 = Calendar.getInstance();
            cal2.setTime(StartDate);
            cal2.add(Calendar.DATE, -Days + i + 2);
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

                final List<FormulaPOJO> formulas = formulaService.findByProductId(j.getProduct().getId());
                for (final FormulaPOJO f : formulas ) {

                    if (f.getPOJOResource().getId().equals(ResID)) {

                        value += f.getValue();
                    }
                }
            }
            ts.add(value);
        }
        return ts;
    }

    private final IJobService jobService;
    private final FormulaService formulaService;
    private final ResourceService resourceService;

    private static final Logger LOG = LoggerFactory.getLogger(TimeSeriesService.class);
}