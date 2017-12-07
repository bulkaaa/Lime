package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IJobDAO;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.tools.DBPopulator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages={"com.modern.codes.lime.tools"})
@ComponentScan(basePackages={"com.modern.codes.lime.service"})
public class IJobServiceTest extends IBasicCRUDServiceTest<Job, JobPOJO, IJobDAO, JobService> {
    @Autowired
    private IJobService service;
    @Autowired
    private JobService serv;
    @Autowired
    DBPopulator pop;

    @Before
    public void setServiceAndResetDB(){
        super.setService(serv);
        pop.clearDB();
        pop.setJobs();
        pop.saveJobs();
    }


    @Test
    public void addJob(){
        long count = service.count();
        JobPOJO obj = new JobPOJO();
        obj.setEndDate(new Date());
        obj.setStartDate(new Date());
        obj.setResultValue(12.00);
        obj.setDetails("Details");
        service.save(obj);
        assertEquals(count + 1, service.count());
    }
    @Test
    public void addJobByList(){
        long count = service.count();
        JobPOJO obj = new JobPOJO();
        JobPOJO obj2 = new JobPOJO();
        List<JobPOJO> list = new ArrayList<>();
        obj.setEndDate(new Date());
        obj.setStartDate(new Date());
        obj.setResultValue(12.00);
        obj.setDetails("Details");
        obj2.setEndDate(new Date());
        obj2.setStartDate(new Date());
        obj2.setResultValue(12.00);
        obj2.setDetails("Details2");
        list.add(obj);
        list.add(obj2);
        service.save(list);
        assertEquals(count + 2, service.count());
    }
    @Test
    public void updateTest(){
        JobPOJO obj = service.findAll().get(0);
        assertNotEquals("0test", obj.getDetails());
        obj.setDetails("0test");
        service.save(obj);
        assertEquals("0test", service.findById(obj.getId()).getDetails());
    }
    @Test
    public void updateByListTest(){
        List<JobPOJO> list = service.findAll();
        assertNotEquals("0test", list.get(0).getDetails());
        assertNotEquals("1test", list.get(1).getDetails());
        list.get(0).setDetails("0test");
        list.get(1).setDetails("1test");
        service.save(list);
        assertEquals("0test", service.findById(list.get(0).getId()).getDetails());
        assertEquals("1test", service.findById(list.get(1).getId()).getDetails());
    }
}