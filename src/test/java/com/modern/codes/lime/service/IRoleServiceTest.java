package com.modern.codes.lime.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.modern.codes.lime.dao.IRoleDAO;
import com.modern.codes.lime.model.Role;
import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.tools.DBPopulator;

@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.modern.codes.lime.tools"})
@ComponentScan(basePackages = {"com.modern.codes.lime.service"})
public class IRoleServiceTest extends IBasicCRUDServiceTest<Role, RolePOJO, IRoleDAO, RoleService> {
    @Before
    public void setServiceAndResetDB() {
        super.setService(serv);
        pop.clearDB();
        pop.setRoles();
        pop.saveRoles();
    }

    @Test
    public void addRole() {
        final long count = service.count();
        final RolePOJO obj = new RolePOJO();
        obj.setName("Konserwator powierzchni plaskich");
        service.save(obj);
        assertEquals(obj.getName(), service.findByName("Konserwator powierzchni plaskich")
                                           .get(0)
                                           .getName());
        assertEquals(count + 1, service.count());
    }

    @Test
    public void addRoleByList() {
        final long count = service.count();
        final RolePOJO obj = new RolePOJO();
        final RolePOJO obj2 = new RolePOJO();
        final List<RolePOJO> list = new ArrayList<>();
        obj.setName("Konserwator powierzchni plaskich");
        obj2.setName("Kolejarz");
        list.add(obj);
        list.add(obj2);
        service.save(list);
        assertEquals(obj.getName(), service.findByName("Konserwator powierzchni plaskich")
                                           .get(0)
                                           .getName());
        assertEquals(obj2.getName(), service.findByName("Kolejarz")
                                            .get(0)
                                            .getName());
        assertEquals(count + 2, service.count());
    }

    @Test
    public void updateTest() {
        final RolePOJO obj = service.findAll()
                                    .get(0);
        assertNotEquals("0test", obj.getName());
        obj.setName("0test");
        service.save(obj);
        assertEquals("0test", service.findById(obj.getId())
                                     .getName());
    }

    @Test
    public void updateByListTest() {
        final List<RolePOJO> list = service.findAll();
        assertNotEquals("0test", list.get(0)
                                     .getName());
        assertNotEquals("1test", list.get(1)
                                     .getName());
        list.get(0)
            .setName("0test");
        list.get(1)
            .setName("1test");
        service.save(list);
        assertEquals("0test", service.findById(list.get(0)
                                                   .getId())
                                     .getName());
        assertEquals("1test", service.findById(list.get(1)
                                                   .getId())
                                     .getName());
    }
    @Autowired
    DBPopulator pop;
    @Autowired
    private IRoleService service;
    @Autowired
    private RoleService serv;
}