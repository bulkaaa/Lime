package com.modern.codes.lime.report;

import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.service.JobService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.service.JobService;
import com.modern.codes.lime.service.ProductService;
import org.omg.CORBA.portable.IDLEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
public class TimeSeriesProduct {
    @Autowired
    public TimeSeriesProduct() {
    }

    public ArrayList<TimeSeries> Extract(JobService service, Date StartDate, Integer Days, ArrayList<String> ListOfIds) {
        List<String> ids = ListOfIds;
        ArrayList<TimeSeries> series = new ArrayList<TimeSeries>();
        for (String id : ids) {
            List<JobPOJO> list = service.findByProductId(id);
            if (!list.isEmpty()) {
                TimeSeries ts = ExtractProduct(list, StartDate, Days);
                String label = list.get(0).getPOJOProduct().getName();
                ts.setLabel(label);
                series.add(ts);
            }
        }
        return series;
    }


        public ArrayList<TimeSeries> Extract(JobService service, Date StartDate, Integer Days)
    {
        List<String> ids = getProductId(service);
        ArrayList<TimeSeries> series = new ArrayList<TimeSeries>();
        for (String id:ids){
            List<JobPOJO> list = service.findByProductId(id);
            if (!list.isEmpty()) {
                TimeSeries ts = ExtractProduct(list, StartDate, Days);
                String label = list.get(0).getPOJOProduct().getName();
                ts.setLabel(label);
                series.add(ts);
            }
        }
        return series;

    }

    public TimeSeries ExtractProduct(List<JobPOJO> list, Date StartDate, Integer Days){

        TimeSeries ts = new TimeSeries();
        for (int i = 0 ;i < Days; i++){
            List<JobPOJO> day = new ArrayList<JobPOJO>();
            Calendar cal = Calendar.getInstance();
            cal.setTime(StartDate);
            cal.add(Calendar.DATE, - Days + i );
            Date dateBefore = cal.getTime();

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(StartDate);
            cal2.add(Calendar.DATE, - Days + i + 1);
            Date dateBeforeLimit = cal2.getTime();
            for (JobPOJO job: list) {
                if (job.getEndDate().after(dateBefore) && job.getEndDate().before(dateBeforeLimit))  day.add(job);
            }
            double value = 0;
            for (JobPOJO j : day) {
                value = value + j.getResultValue();

            }
            ts.add((int) value);
        }
        return ts;
    }

    public List<String> getProductId (JobService service){
        List<String> IDList = new ArrayList<String>();
        List<JobPOJO> list = service.findAll();
        while (!list.isEmpty()){
            String id = list.get(0).getProduct().getId();
            System.out.println(id);
            if(!IDList.contains(id)) IDList.add(id);
            list.remove(0);
        }
        return IDList;
    }
}
