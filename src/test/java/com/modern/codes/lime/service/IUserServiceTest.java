package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
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
public class IUserServiceTest extends IBasicCRUDServiceTest<User, UserPOJO, IUserDAO, UserService> {
    @Autowired
    private IUserService service;
    @Autowired
    private UserService serv;
    @Autowired
    DBPopulator pop;

    @Before
    public void setServiceAndResetDB(){
        super.setService(serv);
        pop.clearDB();
        pop.setUsers();
        pop.saveUsers();
    }

    @Test
    public void findUserByNameAndUserName(){
        List<UserPOJO> list = serv.findByNameAndSurname("Maciej", "Glowala");
        assertEquals(list.get(0).getUsername(), "loginA");
    }

    @Test
    public void addUser(){
        long count = service.count();
        UserPOJO obj = new UserPOJO();
        obj.setName("Nam");
        obj.setSurname("surn");
        obj.setUsername("logtest0");
        obj.setPassword("pass");
        service.save(obj);
        UserPOJO fObj = service.findByUsername("logtest0");
        assertEquals(obj.getUsername(), fObj.getUsername());
        assertEquals(count + 1, service.count());
    }
    @Test
    public void addUserByList(){
        long count = service.count();
        UserPOJO obj = new UserPOJO();
        UserPOJO obj2 = new UserPOJO();
        List<UserPOJO> list = new ArrayList<>();
        obj.setName("Nam");
        obj.setSurname("surn");
        obj.setUsername("logtest0");
        obj.setPassword("pass");
        obj2.setName("Nam2");
        obj2.setSurname("surn2");
        obj2.setUsername("logtest02");
        obj2.setPassword("pass2");
        list.add(obj);
        list.add(obj2);
        service.save(list);
        UserPOJO fObj = service.findByUsername("logtest0");
        assertEquals(obj.getUsername(), fObj.getUsername());
        UserPOJO fObj2 = service.findByUsername("logtest02");
        assertEquals(obj2.getUsername(), fObj2.getUsername());
        assertEquals(count + 2, service.count());
    }
    @Test
    public void updateTest(){
        UserPOJO obj = service.findAll().get(0);
        assertNotEquals("0test", obj.getName());
        obj.setName("0test");
        service.save(obj);
        assertEquals("0test", service.findById(obj.getId()).getName());
    }
    @Test
    public void updateByListTest(){
        List<UserPOJO> list = service.findAll();
        assertNotEquals("0test", list.get(0).getName());
        assertNotEquals("1test", list.get(1).getName());
        list.get(0).setName("0test");
        list.get(1).setName("1test");
        service.save(list);
        assertEquals("0test", service.findById(list.get(0).getId()).getName());
        assertEquals("1test", service.findById(list.get(1).getId()).getName());
    }
}