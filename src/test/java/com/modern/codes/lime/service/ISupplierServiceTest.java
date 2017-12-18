package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.ISupplierDAO;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.pojo.SupplierPOJO;
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
public class ISupplierServiceTest extends IBasicCRUDServiceTest<Supplier, SupplierPOJO, ISupplierDAO, SupplierService> {
    @Autowired
    private ISupplierService service;
    @Autowired
    private SupplierService serv;
    @Autowired
    DBPopulator pop;

    @Before
    public void setServiceAndResetDB(){
        super.setService(serv);
        pop.clearDB();
        pop.setSuppliers();
        pop.saveSuppliers();
    }

    @Test
    public void addSupplier(){
        long count = service.count();
        SupplierPOJO obj = new SupplierPOJO();
        obj.setEmailAddress("EmailAdress@test.pl");
        obj.setCity("warsaw");
        obj.setCountry("PL");
        obj.setPostalCode("01-142");
        obj.setStreet("ulica");
        obj.setTelephone("123012039");
        obj.setName("Zdzisiek");
        service.save(obj);
        assertEquals(obj.getName(), service.findByName("Zdzisiek").get(0).getName());
        assertEquals(count + 1, service.count());
    }
    @Test
    public void addSupplierByList(){
        long count = service.count();
        SupplierPOJO obj = new SupplierPOJO();
        SupplierPOJO obj2 = new SupplierPOJO();
        List<SupplierPOJO> list = new ArrayList<>();
        obj.setEmailAddress("EmailAdress@test.pl");
        obj.setCity("warsaw");
        obj.setCountry("PL");
        obj.setPostalCode("01-142");
        obj.setStreet("ulica");
        obj.setTelephone("123012039");
        obj.setName("Zdzisiek");
        obj2.setEmailAddress("EmailAdress@test2.pl");
        obj2.setCity("warsaw2");
        obj2.setCountry("PL");
        obj2.setPostalCode("01-1422");
        obj2.setStreet("ulica2");
        obj2.setTelephone("1230120392");
        obj2.setName("Zdzisiek2");
        list.add(obj);
        list.add(obj2);
        service.save(list);
        assertEquals(obj2.getName(), service.findByName("Zdzisiek2").get(0).getName());
        assertEquals(obj.getName(), service.findByName("Zdzisiek").get(0).getName());
        assertEquals(count + 2, service.count());

    }
    @Test
    public void updateTest(){
        SupplierPOJO obj = service.findAll().get(0);
        assertNotEquals("0test", obj.getName());
        obj.setName("0test");
        service.save(obj);
        assertEquals("0test", service.findById(obj.getId()).getName());
    }
    @Test
    public void updateByListTest(){
        List<SupplierPOJO> list = service.findAll();
        assertNotEquals("0test", list.get(0).getName());
        assertNotEquals("1test", list.get(1).getName());
        list.get(0).setName("0test");
        list.get(1).setName("1test");
        service.save(list);
        assertEquals("0test", service.findById(list.get(0).getId()).getName());
        assertEquals("1test", service.findById(list.get(1).getId()).getName());
    }

}
