package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IProductDAO;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.pojo.ProductPOJO;
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
public class IProductServiceTest extends IBasicCRUDServiceTest<Product, ProductPOJO, IProductDAO, ProductService> {
    @Autowired
    private IProductService service;
    @Autowired
    private ProductService serv;
    @Autowired
    DBPopulator pop;

    @Before
    public void setServiceAndResetDB(){
        super.setService(serv);
        pop.clearDB();
        pop.setProducts();
        pop.saveProducts();
    }

    @Test
    public void addProduct(){
        long count = service.count();
        ProductPOJO obj = new ProductPOJO();
        obj.setName("obj1");
        obj.setImage("https://i.ytimg.com/vi/oUKDWoqBrf8/maxresdefault.jpg");
        obj.setExpectedValue(102132.00);
        obj.setDescription("desc00");
        obj.setUnit(Unit.KG);
        service.save(obj);
        assertEquals(obj.getName(), service.findByName("obj1").get(0).getName());
        assertEquals(count + 1, service.count());
    }
    @Test
    public void addProductByList(){
        long count = service.count();
        ProductPOJO obj = new ProductPOJO();
        ProductPOJO obj2 = new ProductPOJO();
        List<ProductPOJO> list = new ArrayList<>();
        obj.setName("obj1");
        obj.setExpectedValue(102132.00);
        obj.setDescription("desc00");
        obj.setImage("https://i.ytimg.com/vi/oUKDWoqBrf8/maxresdefault.jpg");
        obj.setUnit(Unit.KG);
        obj2.setName("obj2");
        obj2.setImage("https://i.ytimg.com/vi/oUKDWoqBrf8/maxresdefault.jpg");
        obj2.setExpectedValue(102132.00);
        obj2.setDescription("desc00");
        obj2.setUnit(Unit.KG);
        list.add(obj);
        list.add(obj2);
        service.save(list);
        assertEquals(obj.getName(), service.findByName("obj1").get(0).getName());
        assertEquals(obj2.getName(), service.findByName("obj2").get(0).getName());
        assertEquals(count + 2, service.count());
    }
    @Test
    public void updateTest(){
        ProductPOJO obj = service.findAll().get(0);
        assertNotEquals("0test", obj.getName());
        obj.setName("0test");
        service.save(obj);
        assertEquals("0test", service.findById(obj.getId()).getName());
    }
    @Test
    public void updateByListTest(){
        List<ProductPOJO> list = service.findAll();
        assertNotEquals("0test", list.get(0).getName());
        assertNotEquals("1test", list.get(1).getName());
        list.get(0).setName("0test");
        list.get(1).setName("1test");
        service.save(list);
        assertEquals("0test", service.findById(list.get(0).getId()).getName());
        assertEquals("1test", service.findById(list.get(1).getId()).getName());
    }
}
