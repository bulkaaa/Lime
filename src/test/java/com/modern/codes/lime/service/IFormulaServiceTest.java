package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IFormulaDAO;
import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.pojo.FormulaPOJO;
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
public class IFormulaServiceTest extends IBasicCRUDServiceTest<Formula, FormulaPOJO, IFormulaDAO, FormulaService> {
    @Autowired
    private IFormulaService service;
    @Autowired
    private FormulaService serv;
    @Autowired
    DBPopulator pop;

    @Before
    public void setServiceAndResetDB(){
        super.setService(serv);
        pop.clearDB();
        pop.setFormulas();
        pop.saveFormulas();
    }

    @Test
    public void addFormula(){
        final long count = service.count();
        final FormulaPOJO obj = new FormulaPOJO();
        obj.setValue(20.000);
        service.save(obj);
        assertEquals(count + 1, service.count());
    }
    @Test
    public void addFormulaByList(){
        final long count = service.count();
        final FormulaPOJO obj = new FormulaPOJO();
        final FormulaPOJO obj2 = new FormulaPOJO();
        final List<FormulaPOJO> list = new ArrayList<>();
        obj.setValue(20.000);
        obj2.setValue(21.000);
        list.add(obj);
        list.add(obj2);
        service.save(list);
        assertEquals(count + 2, service.count());
    }
    @Test
    public void updateTest(){
        final FormulaPOJO obj = service.findAll().get(0);
        assertNotEquals("0test", obj.getValue());
        obj.setValue(10101010.00);
        service.save(obj);
        assertEquals(new Double(10101010.00), service.findById(obj.getId()).getValue());
    }
    @Test
    public void updateByListTest(){
        final List<FormulaPOJO> list = service.findAll();
        assertNotEquals("0test", list.get(0).getValue());
        assertNotEquals("1test", list.get(1).getValue());
        list.get(0).setValue(10101010.00);
        list.get(1).setValue(10101011.00);
        service.save(list);
        assertEquals(new Double(10101010.00), service.findById(list.get(0).getId()).getValue());
        assertEquals(new Double(10101011.00), service.findById(list.get(1).getId()).getValue());
    }
}