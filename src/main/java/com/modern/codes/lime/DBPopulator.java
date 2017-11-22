package com.modern.codes.lime;

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.SupplierPOJO;
import com.modern.codes.lime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBPopulator {

    private JobService jobService;
    private FormulaService formulaService;
    private PrivilegeService privilegeService;
    private ProductService productService;
    private ResourceService resourceService;
    private RoleService roleService;
    private SupplierService supplierService;
    private UserService userService;
    @Autowired
    public DBPopulator(JobService jobService,
            FormulaService formulaService,
            PrivilegeService privilegeService,
            ProductService productService,
            ResourceService resourceService,
            RoleService roleService,
            SupplierService supplierService,
            UserService userService){
        this.jobService = jobService;
        this.formulaService = formulaService;
        this.privilegeService = privilegeService;
        this.productService = productService;
        this.resourceService = resourceService;
        this.roleService = roleService;
        this.supplierService = supplierService;
        this.userService = userService;
    }

    SupplierPOJO supA = new SupplierPOJO();
    SupplierPOJO supB = new SupplierPOJO();

    public void populate(){
        clearDB();
        //SUPPLIER


    }
    public void clearDB(){
        jobService.deleteAll();
        formulaService.deleteAll();
        privilegeService.deleteAll();
        productService.deleteAll();
        resourceService.deleteAll();
        roleService.deleteAll();
        supplierService.deleteAll();
        userService.deleteAll();
    }
    private void setSuppliers(){
        supA.setName("Hurtownia Spozywcza");
        supB.setName("JAJO sp. z o.o. ");
        supA.setTelephone("123123123");
        supB.setTelephone("123123125");
        supA.setStreet("Sportowa 15");
        supB.setStreet("Koguta 5b");
        supA.setPostalCode("09-100");
        supB.setPostalCode("00-000");
        supA.setCountry("Poland");
        supB.setCountry("Poland");
        supA.setCity("Wałbrzych");
        supB.setCity("Kurza Stopka");
        supA.setEmailAddress("Jan@Kowalski.pl");
        supB.setEmailAddress("Cezary@Pazura.co");
//        supA.setResources();
//        supA.setResources();
    }
//    List<ResourcePOJO> AddResources(){
//        ResourcePOJO milk;
//        ResourcePOJO flour;
//        ResourcePOJO egg;
//        ResourcePOJO sugar;
//        milk.set
//    }
}
