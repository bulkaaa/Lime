package com.modern.codes.lime;

import com.modern.codes.lime.pojo.*;
import com.modern.codes.lime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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
    private SupplierPOJO supA = new SupplierPOJO();
    private SupplierPOJO supB = new SupplierPOJO();
    private UserPOJO userA = new UserPOJO();
    private UserPOJO userB = new UserPOJO();
    private UserPOJO userC = new UserPOJO();
    private RolePOJO roleA = new RolePOJO();
    private RolePOJO roleB = new RolePOJO();
    private RolePOJO roleC = new RolePOJO();
    private ResourcePOJO resourceA = new ResourcePOJO();
    private ResourcePOJO resourceB = new ResourcePOJO();
    private ResourcePOJO resourceC = new ResourcePOJO();
    private ResourcePOJO resourceD = new ResourcePOJO();
    private ResourcePOJO resourceE = new ResourcePOJO();
    private ResourcePOJO resourceF = new ResourcePOJO();
    private ResourcePOJO resourceG = new ResourcePOJO();
    private ProductPOJO productA = new ProductPOJO();
    private ProductPOJO productB = new ProductPOJO();
    private ProductPOJO productC = new ProductPOJO();
    private PrivilegePOJO privilegeA = new PrivilegePOJO();
    private PrivilegePOJO privilegeB = new PrivilegePOJO();
    private PrivilegePOJO privilegeC = new PrivilegePOJO();
    private JobPOJO jobA = new JobPOJO();
    private JobPOJO jobB = new JobPOJO();
    private JobPOJO jobC = new JobPOJO();
    private JobPOJO jobD = new JobPOJO();
    private JobPOJO jobE = new JobPOJO();
    private FormulaPOJO formulaA = new FormulaPOJO();
    private FormulaPOJO formulaB = new FormulaPOJO();
    private FormulaPOJO formulaC = new FormulaPOJO();
    private FormulaPOJO formulaD = new FormulaPOJO();
    private FormulaPOJO formulaE = new FormulaPOJO();
    private FormulaPOJO formulaF = new FormulaPOJO();
    private FormulaPOJO formulaG = new FormulaPOJO();
    private FormulaPOJO formulaH = new FormulaPOJO();
    private FormulaPOJO formulaI = new FormulaPOJO();
    private FormulaPOJO formulaJ = new FormulaPOJO();
    private FormulaPOJO formulaK = new FormulaPOJO();
    private FormulaPOJO formulaL = new FormulaPOJO();



    public void populate(){
        clearDB();
        setSuppliers();
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
        supA.setId(UUID.randomUUID().toString());
        supA.setId(UUID.randomUUID().toString());
    }
    private void setUser(){
        userA.setPassword("passA");
        userA.setLogin("loginA");
        userA.setDescription("Desctiprion of user B");
        userA.setJoinedAt(new Date(2017, 5, 5));
        userA.setName("Maciej");
        userA.setSurname("Glowala");
        userA.setId(UUID.randomUUID().toString());
        userB.setPassword("passB");
        userB.setLogin("loginB");
        userB.setDescription("Desctiprion of user B");
        userB.setJoinedAt(new Date(2017, 6, 6));
        userB.setName("Klaudia");
        userB.setSurname("Jarosz");
        userB.setId(UUID.randomUUID().toString());
        userC.setPassword("passC");
        userC.setLogin("loginC");
        userC.setDescription("Desctiprion of user B");
        userC.setJoinedAt(new Date(2017, 7, 7));
        userC.setName("Aleksandra");
        userC.setSurname("Bulka");
        userC.setId(UUID.randomUUID().toString());
    }
    private void setRole(){
        roleA.setName("Administrator");
        roleA.setId(UUID.randomUUID().toString());
        roleB.setName("Manager");
        roleB.setId(UUID.randomUUID().toString());
        roleC.setName("Staff");
        roleC.setId(UUID.randomUUID().toString());
    }
    private void setResources(){
//        resourceA.setName();
//        resourceA.setUnit();
//        resourceA.setQuantity();
//        resourceA.setId();
//        resourceA.setDescription();
//        resourceA.setCategory();
    }
}
