package com.modern.codes.lime.controller;

import java.util.List;

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
        final JobPOJO job = jobService.findAll().get(0);

        // get list of ALL Resources to do this job
        final List<FormulaPOJO> formula = formulaService.findByProductId(job.getPOJOProduct().getId());
        // get name of FIRST resource needed to do this job
        String name = formula.get(0).getPOJOResource().getName();
        // get value needed of FIRST resource to do this job
        double value = formula.get(0).getValue();

    }

}
