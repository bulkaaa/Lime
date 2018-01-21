package com.modern.codes.lime.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.service.IFormulaService;
import com.modern.codes.lime.service.IJobService;
import com.modern.codes.lime.service.IResourceService;
import com.modern.codes.lime.service.UserService;
import com.modern.codes.lime.tools.DBPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(path="/dev")
public class DevController {

    @Autowired
    DBPopulator pop;
    @Autowired
    UserService serv;
    @Autowired
    IJobService jobService;
    @Autowired
    IFormulaService formulaService;
    @GetMapping(path = "/populate")
    public String populate(){
        pop.populate();
        return "DB reset completed";
    }
    @GetMapping(path = "/hello-world")
    public String hello(){
        return "hello world";
    }
    @GetMapping(path = "/hello-world2")
    public String hello2(){
        return "hello worl2d";
    }

    @GetMapping(path = "/GetResourcesForJob")
    public void resdev(){
        try {
            final Map<String,Double> valueMap = new HashMap<>();
            final List<String> resourceIds = new ArrayList<>();
            resourceIds.add("4858b21b-75c8-4d01-8076-3434bf9d721c"); //egg
            resourceIds.add("914c96b9-f598-45cd-9406-60ca175b42e5"); //flour
            final Date startDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse("January 1, 2018");
            final Date endDate = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse("January 29, 2018");
            final List<JobPOJO> jobList = jobService.findByEndDateBetween(startDate, endDate);
            jobList.forEach(job ->{
                final List<FormulaPOJO> formula = formulaService.findByProductId(job.getPOJOProduct().getId());
                final List<FormulaPOJO> filteredFormula = IFormulaService.filterByResources(formula, resourceIds);
                filteredFormula.forEach(y -> {
                    final ResourcePOJO resource = y.getPOJOResource();
                    Double value = valueMap.get(resource.getName());
                    if (null == value)
                        value = 0.0;
                    value += y.getValue();
                    valueMap.put(resource.getName(), value);
                });
            });
        } catch (final ParseException e) {
            e.printStackTrace();
        }
    }
}
