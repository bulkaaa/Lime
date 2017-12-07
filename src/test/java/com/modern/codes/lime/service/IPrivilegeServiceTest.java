package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IPrivilegeDAO;
import com.modern.codes.lime.model.Privilege;
import com.modern.codes.lime.pojo.PrivilegePOJO;
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
public class IPrivilegeServiceTest extends IBasicCRUDServiceTest<Privilege, PrivilegePOJO, IPrivilegeDAO, PrivilegeService> {
    @Autowired
    private IPrivilegeService service;
    @Autowired
    private PrivilegeService serv;
    @Autowired
    DBPopulator pop;

    @Before
    public void setServiceAndResetDB(){
        super.setService(serv);
        pop.clearDB();
        pop.setPrivileges();
        pop.savePrivileges();
    }

    @Test
    public void addPrivilege(){
        long count = service.count();
        PrivilegePOJO obj = new PrivilegePOJO();
        obj.setName("Sprzatanie");
        service.save(obj);
        assertEquals(count + 1, service.count());
        assertEquals(obj.getName(), service.findByName("Sprzatanie").get(0).getName());
    }
    @Test
    public void addPrivilegeByList(){
        long count = service.count();
        PrivilegePOJO obj = new PrivilegePOJO();
        PrivilegePOJO obj2 = new PrivilegePOJO();
        List<PrivilegePOJO> list = new ArrayList<>();
        obj.setName("Sprzatanie");
        obj2.setName("mycie");
        list.add(obj);
        list.add(obj2);
        service.save(list);
        assertEquals(obj.getName(), service.findByName("Sprzatanie").get(0).getName());
        assertEquals(obj2.getName(), service.findByName("mycie").get(0).getName());
        assertEquals(count + 2, service.count());
    }
    @Test
    public void updateTest(){
        PrivilegePOJO obj = service.findAll().get(0);
        assertNotEquals("0test", obj.getName());
        obj.setName("0test");
        service.save(obj);
        assertEquals("0test", service.findById(obj.getId()).getName());
    }
    @Test
    public void updateByListTest(){
        List<PrivilegePOJO> list = service.findAll();
        assertNotEquals("0test", list.get(0).getName());
        assertNotEquals("1test", list.get(1).getName());
        list.get(0).setName("0test");
        list.get(1).setName("1test");
        service.save(list);
        assertEquals("0test", service.findById(list.get(0).getId()).getName());
        assertEquals("1test", service.findById(list.get(1).getId()).getName());
    }
}
