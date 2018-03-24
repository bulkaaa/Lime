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

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.tools.DBPopulator;

@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.modern.codes.lime.tools"})
@ComponentScan(basePackages = {"com.modern.codes.lime.service"})
public class IUserServiceTest extends IBasicCRUDServiceTest<User, UserPOJO, IUserDAO, UserService> {
    @Before
    public void setServiceAndResetDB() {
        super.setService(serv);
        pop.clearDB();
        pop.setUsers();
        pop.saveUsers();
    }

    @Test
    public void findUserByNameAndUserName() {
        final List<UserPOJO> list = serv.findByNameAndSurname("Maciej", "Glowala");
        assertEquals("loginA", list.get(0)
                                   .getUsername());
    }

    @Test
    public void addUser() {
        final long count = service.count();
        final UserPOJO obj = new UserPOJO();
        obj.setName("Nam");
        obj.setSurname("surn");
        obj.setUsername("logtest0");
        obj.setPassword("pass");
        obj.setEmailAddress("user@mini.pw.edu.pl");
        service.save(obj);
        final UserPOJO fObj = service.findByUsername("logtest0");
        assertEquals(obj.getUsername(), fObj.getUsername());
        assertEquals(count + 1, service.count());
    }

    @Test
    public void addUserByList() {
        final long count = service.count();
        final UserPOJO obj = new UserPOJO();
        final UserPOJO obj2 = new UserPOJO();
        obj.setName("Nam");
        obj.setSurname("surn");
        obj.setUsername("logtest0");
        obj.setPassword("pass");
        obj.setEmailAddress("user@mini.pw.edu.pl");
        obj2.setName("Nam2");
        obj2.setSurname("surn2");
        obj2.setUsername("logtest02");
        obj2.setPassword("pass2");
        obj2.setEmailAddress("user2@mini.pw.edu.pl");
        final List<UserPOJO> list = new ArrayList<>();
        list.add(obj);
        list.add(obj2);
        service.save(list);
        final UserPOJO fObj = service.findByUsername("logtest0");
        assertEquals(obj.getUsername(), fObj.getUsername());
        final UserPOJO fObj2 = service.findByUsername("logtest02");
        assertEquals(obj2.getUsername(), fObj2.getUsername());
        assertEquals(count + 2, service.count());
    }

    @Test
    public void updateTest() {
        final UserPOJO obj = service.findAll()
                                    .get(0);
        assertNotEquals("0test", obj.getName());
        obj.setName("0test");
        service.save(obj);
        assertEquals("0test", service.findById(obj.getId())
                                     .getName());
    }

    @Test
    public void updateByListTest() {
        final List<UserPOJO> list = service.findAll();
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
    private IUserService service;
    @Autowired
    private UserService serv;
}