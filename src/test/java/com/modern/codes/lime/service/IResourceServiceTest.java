package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IResourceDAO;
import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.tools.DBPopulator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages={"com.modern.codes.lime.tools"})
@ComponentScan(basePackages={"com.modern.codes.lime.service"})
public class IResourceServiceTest extends IBasicCRUDServiceTest<Resource, ResourcePOJO, IResourceDAO, ResourceService> {
    @Autowired
    private IResourceService service;
    @Autowired
    private ResourceService serv;
    @Autowired
    DBPopulator pop;

    @Before
    public void setServiceAndResetDB(){
        super.setService(serv);
        pop.clearDB();
        pop.setResources();
        pop.saveResources();
    }

    @Test
    public void addResource(){
        final long count = service.count();
        final ResourcePOJO obj = new ResourcePOJO();
        obj.setName("Resource");
        obj.setQuantity(10101.00);
        obj.setImage("https://i.ytimg.com/vi/oUKDWoqBrf8/maxresdefault.jpg");
        obj.setDescription("desc");
        obj.setUnit(Unit.KG);
        service.save(obj);
        assertEquals(obj.getName(), service.findByName("Resource").get(0).getName());
        assertEquals(count + 1, service.count());
    }
    @Test
    public void addResourceByList(){
        final long count = service.count();
        final ResourcePOJO obj = new ResourcePOJO();
        final ResourcePOJO obj2 = new ResourcePOJO();
        obj.setName("Resource");
        obj.setQuantity(10101.00);
        obj.setImage("https://i.ytimg.com/vi/oUKDWoqBrf8/maxresdefault.jpg");
        obj.setDescription("desc");
        obj.setUnit(Unit.KG);
        obj2.setName("Resource2");
        obj2.setQuantity(10101.00);
        obj2.setImage("https://i.ytimg.com/vi/oUKDWoqBrf8/maxresdefault.jpg");
        obj2.setDescription("desc");
        obj2.setUnit(Unit.KG);

        final List<ResourcePOJO> list = new ArrayList<>();
        list.add(obj);
        list.add(obj2);
        service.save(list);
        assertEquals(count + 2, service.count());
        assertEquals(obj.getName(), service.findByName("Resource").get(0).getName());
        assertEquals(obj2.getName(), service.findByName("Resource2").get(0).getName());
    }
    @Test
    public void updateTest(){
        final ResourcePOJO obj = service.findAll().get(0);
        assertNotEquals("0test", obj.getName());
        obj.setName("0test");
        service.save(obj);
        assertEquals("0test", service.findById(obj.getId()).getName());
    }
    @Test
    public void updateByListTest(){
        final List<ResourcePOJO> list = service.findAll();
        assertNotEquals("0test", list.get(0).getName());
        assertNotEquals("1test", list.get(1).getName());
        list.get(0).setName("0test");
        list.get(1).setName("1test");
        service.save(list);
        assertEquals("0test", service.findById(list.get(0).getId()).getName());
        assertEquals("1test", service.findById(list.get(1).getId()).getName());
    }
}