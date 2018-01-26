package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IBasicCRUDRepository;
import com.modern.codes.lime.pojo.BasicPOJO;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DataJpaTest
public class IBasicCRUDServiceTest<T, T_POJO, T_DAO extends IBasicCRUDRepository<T, String>, T_SERVICE extends BasicCRUDService<T, T_POJO, T_DAO>> {
    private T_SERVICE service;

    @Test
    public void findAllTest(){
        final List<T_POJO> list = service.findAll();
        assertEquals(service.count(), list.size());
    }
    @Test
    public void deleteByIdTest(){
        final T_POJO obj = service.findAll().get(0);
        service.delete(((BasicPOJO)obj).getId());
        final T_POJO next_obj = service.findAll().get(0);
        assertNotEquals(((BasicPOJO)obj).getId(), ((BasicPOJO)next_obj).getId());
    }
    @Test
    public void deleteByObjectTest(){
        final T_POJO obj = service.findAll().get(0);
        service.delete(obj);
        final T_POJO next_obj = service.findAll().get(0);
        assertNotEquals(((BasicPOJO)obj).getId(), ((BasicPOJO)next_obj).getId());
    }
    @Test
    public void deleteAllTest(){
        service.deleteAll();
        final List<T_POJO> list = service.findAll();
        assertEquals(0, list.size());
    }
    @Test
    public void deleteByListTest(){
        List<T_POJO> obj_list = service.findAll();
        final T_POJO obj = obj_list.get(obj_list.size() - 1);
        obj_list.remove(obj);
        service.delete(obj_list);
        obj_list = service.findAll();
        assertTrue(obj_list.contains(obj));
    }

    @Test
    public void existsTest(){
        final T_POJO obj = service.findAll().get(0);
        assertTrue(service.exists(((BasicPOJO)obj).getId()));
    }
    @Test
    public void countTest(){
        final List<T_POJO> obj_list = service.findAll();
        assertEquals(obj_list.size(), service.count());
    }
    @Test
    public void equalsTest(){
        final T_POJO obj = service.findAll().get(0);
        final T_POJO obj2 = service.findAll().get(0);
        assertEquals(obj,obj2);
    }

    @Test
    public void findByIdTest(){
        final T_POJO obj = service.findAll().get(0);
        final T_POJO obj2 = service.findById(((BasicPOJO)obj).getId());
        assertEquals(obj,obj2);
    }
    void setService(final T_SERVICE service){
        this.service = service;
    }
}