package com.modern.codes.lime;

import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.pojo.*;
import com.modern.codes.lime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private ProductPOJO productD = new ProductPOJO();
    private PrivilegePOJO privilegeA = new PrivilegePOJO();
    private PrivilegePOJO privilegeB = new PrivilegePOJO();
    private PrivilegePOJO privilegeC = new PrivilegePOJO();
    private PrivilegePOJO privilegeD = new PrivilegePOJO();
    private PrivilegePOJO privilegeE = new PrivilegePOJO();
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
    private FormulaPOJO formulaM = new FormulaPOJO();
    private FormulaPOJO formulaN = new FormulaPOJO();
    private FormulaPOJO formulaO = new FormulaPOJO();
    private FormulaPOJO formulaP = new FormulaPOJO();
    private FormulaPOJO formulaQ = new FormulaPOJO();
    private FormulaPOJO formulaR = new FormulaPOJO();
    private FormulaPOJO formulaS = new FormulaPOJO();
    private FormulaPOJO formulaT = new FormulaPOJO();
    private FormulaPOJO formulaU = new FormulaPOJO();

    public void populate(){
        clearDB();
        setSuppliers();
        setUsers();
        setRoles();
        setResources();
        setProducts();
        setPrivileges();
        setJobs();
        setFormulas();
        saveToDB();
        suppliersRelations();
        usersRelations();
        rolesRelations();
        resourcesRelations();
        productsRelations();
        privilegesRelations();
        jobsRelations();
        formulasRelations();
        saveToDB();
    }



    public void clearDB(){
        userService.deleteAll();
        roleService.deleteAll();
        privilegeService.deleteAll();
        jobService.deleteAll();
        formulaService.deleteAll();
        productService.deleteAll();
        resourceService.deleteAll();
        supplierService.deleteAll();

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
        supA.setCountry("PL");
        supB.setCountry("PL");
        supA.setCity("Walbrzych");
        supB.setCity("Kurza Stopka");
        supA.setEmailAddress("Jan@Kowalski.pl");
        supB.setEmailAddress("Cezary@Pazura.co");
        supA.setId(UUID.randomUUID().toString());
        supA.setId(UUID.randomUUID().toString());
    }
    private void setUsers(){
        userA.setPassword("passA");
        userA.setLogin("loginA");
        userA.setJoinedAt(ParseTools.parseDate("2017-05-05 12:00:00"));
        userA.setName("Maciej");
        userA.setSurname("Glowala");
        userA.setId(UUID.randomUUID().toString());
        userB.setPassword("passB");
        userB.setLogin("loginB");
        userB.setJoinedAt(ParseTools.parseDate("2017-05-06 12:00:00"));
        userB.setName("Klaudia");
        userB.setSurname("Jarosz");
        userB.setId(UUID.randomUUID().toString());
        userC.setPassword("passC");
        userC.setLogin("loginC");
        userC.setJoinedAt(ParseTools.parseDate("2017-05-06 12:00:00"));
        userC.setName("Aleksandra");
        userC.setSurname("Bulka");
        userC.setId(UUID.randomUUID().toString());
    }
    private void setRoles(){
        roleA.setName("Administrator");
        roleA.setId(UUID.randomUUID().toString());
        roleB.setName("Manager");
        roleB.setId(UUID.randomUUID().toString());
        roleC.setName("Staff");
        roleC.setId(UUID.randomUUID().toString());
    }
    private void setResources(){
        resourceA.setName("Flour");
        resourceA.setUnit(Unit.KG);
        resourceA.setQuantity((double)30);
        resourceA.setId(UUID.randomUUID().toString());
        resourceA.setDescription("Description for resource A");
        resourceA.setCategory("Basic");
        resourceB.setName("Milk");
        resourceB.setUnit(Unit.LITER);
        resourceB.setQuantity((double)20);
        resourceB.setId(UUID.randomUUID().toString());
        resourceB.setDescription("Description for resource B");
        resourceB.setCategory("Basic");
        resourceC.setName("EGG");
        resourceC.setUnit(Unit.UNIT);
        resourceC.setQuantity((double)50);
        resourceC.setId(UUID.randomUUID().toString());
        resourceC.setDescription("Description for resource C");
        resourceC.setCategory("Basic");
        resourceD.setName("SUGAR");
        resourceD.setUnit(Unit.KG);
        resourceD.setQuantity((double)20);
        resourceD.setId(UUID.randomUUID().toString());
        resourceD.setDescription("Description for resource D");
        resourceD.setCategory("Additional");
        resourceE.setName("Strawberry");
        resourceE.setUnit(Unit.KG);
        resourceE.setQuantity((double)10);
        resourceE.setId(UUID.randomUUID().toString());
        resourceE.setDescription("Description for resource E");
        resourceE.setCategory("Additional");
        resourceF.setName("Cherry");
        resourceF.setUnit(Unit.KG);
        resourceF.setQuantity((double)10);
        resourceF.setId(UUID.randomUUID().toString());
        resourceF.setDescription("Description for resource F");
        resourceF.setCategory("Additional");
        resourceG.setName("Chocolate");  //formula  S U
        resourceG.setUnit(Unit.BAR);
        resourceG.setQuantity((double)30);
        resourceG.setId(UUID.randomUUID().toString());
        resourceG.setDescription("Description for resource G");
        resourceG.setCategory("Additional");

        resourceA.setImage("https://tinyurl.com/ya9ene6r");
        resourceB.setImage("https://tinyurl.com/ya9ene6r");
        resourceC.setImage("https://tinyurl.com/ya9ene6r");
        resourceD.setImage("https://tinyurl.com/ya9ene6r");
        resourceE.setImage("https://tinyurl.com/ya9ene6r");
        resourceF.setImage("https://tinyurl.com/ya9ene6r");
        resourceG.setImage("https://tinyurl.com/ya9ene6r");
    }

    private void setProducts(){
        productA.setUnit(Unit.UNIT);
        productA.setName("Cherry Cake");
        productA.setId(UUID.randomUUID().toString());
        productA.setExpectedValue((double)1);
        productA.setDescription("Description for product A");
        productA.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productA.setCategory("Cake");
        productB.setUnit(Unit.UNIT);
        productB.setName("Strawberry Cake");
        productB.setId(UUID.randomUUID().toString());
        productB.setExpectedValue((double)1);
        productB.setDescription("Description for product B");
        productB.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productB.setCategory("Cake");
        productC.setUnit(Unit.UNIT);
        productC.setName("Chocolate Cake with Strawberries");
        productC.setId(UUID.randomUUID().toString());
        productC.setExpectedValue((double)1);
        productC.setDescription("Description for product C");
        productC.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productC.setCategory("Cake");
        productD.setUnit(Unit.UNIT);
        productD.setName("Donut with chocolate");
        productD.setId(UUID.randomUUID().toString());
        productD.setExpectedValue((double)20);
        productD.setDescription("Description for product D");
        productD.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productD.setCategory("Donut");

        productA.setImage("https://tinyurl.com/ya9ene6r");
        productB.setImage("https://tinyurl.com/ya9ene6r");
        productC.setImage("https://tinyurl.com/ya9ene6r");
        productD.setImage("https://tinyurl.com/ya9ene6r");
    }

    private void setPrivileges(){
        privilegeA.setName("Manage Users");
        privilegeA.setId(UUID.randomUUID().toString());
        privilegeB.setName("Manage Recipes");
        privilegeB.setId(UUID.randomUUID().toString());
        privilegeC.setName("Get Reports");
        privilegeC.setId(UUID.randomUUID().toString());
        privilegeD.setName("Manage Resources");
        privilegeD.setId(UUID.randomUUID().toString());
        privilegeE.setName("Create Product");
        privilegeE.setId(UUID.randomUUID().toString());
    }
    private void setJobs() {
        jobA.setId(UUID.randomUUID().toString());
        jobA.setStartDate(ParseTools.parseDate("2017-07-01 12:00:00"));
        jobA.setEndDate(ParseTools.parseDate("2017-07-01 16:00:00"));
        jobA.setResultValue((double)1);
        jobA.setDetails("Details for job A. Making cherry cake.");
        jobB.setId(UUID.randomUUID().toString());
        jobB.setStartDate(ParseTools.parseDate("2017-07-02 12:00:00"));
        jobB.setEndDate(ParseTools.parseDate("2017-07-02 16:00:00"));
        jobB.setResultValue((double)1);
        jobB.setDetails("Details for job B. Making Strawberry cake");
        jobC.setId(UUID.randomUUID().toString());
        jobC.setStartDate(ParseTools.parseDate("2017-07-03 12:00:00"));
        jobC.setEndDate(ParseTools.parseDate("2017-07-03 16:00:00"));
        jobC.setResultValue((double)1);
        jobC.setDetails("Details for job C. Making chocolate cake with strawberries");
        jobD.setId(UUID.randomUUID().toString());
        jobD.setStartDate(ParseTools.parseDate("2017-07-04 12:00:00"));
        jobD.setEndDate(ParseTools.parseDate("2017-07-04 16:00:00"));
        jobD.setResultValue((double)20);
        jobD.setDetails("Details for job D. Making donuts");
        jobE.setId(UUID.randomUUID().toString());
        jobE.setStartDate(ParseTools.parseDate("2017-07-05 12:00:00"));
        jobE.setEndDate(ParseTools.parseDate("2017-07-05 16:00:00"));
        jobE.setResultValue((double)19);
        jobE.setDetails("Details for job E. Making donuts");
    }
    private void setFormulas(){
        formulaA.setId(UUID.randomUUID().toString());
        formulaA.setValue((double)1);
        formulaB.setId(UUID.randomUUID().toString());
        formulaB.setValue((double)1);
        formulaC.setId(UUID.randomUUID().toString());
        formulaC.setValue((double)5);
        formulaD.setId(UUID.randomUUID().toString());
        formulaD.setValue(0.1);
        formulaE.setId(UUID.randomUUID().toString());
        formulaE.setValue((double)1);
        formulaF.setId(UUID.randomUUID().toString());
        formulaF.setValue((double)1);
        formulaG.setId(UUID.randomUUID().toString());
        formulaG.setValue((double)5);
        formulaH.setId(UUID.randomUUID().toString());
        formulaH.setValue(0.1);
        formulaI.setId(UUID.randomUUID().toString());
        formulaI.setValue((double)1);
        formulaJ.setId(UUID.randomUUID().toString());
        formulaJ.setValue((double)1);
        formulaK.setId(UUID.randomUUID().toString());
        formulaK.setValue((double)5);
        formulaL.setId(UUID.randomUUID().toString());
        formulaL.setValue(0.1);
        formulaM.setId(UUID.randomUUID().toString());
        formulaM.setValue((double)1);
        formulaN.setId(UUID.randomUUID().toString());
        formulaN.setValue((double)1);
        formulaO.setId(UUID.randomUUID().toString());
        formulaO.setValue((double)5);
        formulaP.setId(UUID.randomUUID().toString());
        formulaP.setValue(0.1);
        formulaQ.setId(UUID.randomUUID().toString());
        formulaQ.setValue(0.2);
        formulaR.setId(UUID.randomUUID().toString());
        formulaR.setValue(0.2);
        formulaS.setId(UUID.randomUUID().toString());
        formulaS.setValue((double)1);
        formulaT.setId(UUID.randomUUID().toString());
        formulaT.setValue(0.1);
        formulaU.setId(UUID.randomUUID().toString());
        formulaU.setValue((double)1);
    }
    private void suppliersRelations(){
        List<ResourcePOJO> resA = new ArrayList<>();
        List<ResourcePOJO> resB = new ArrayList<>();
        resA.add(resourceA);
        resA.add(resourceB);
        resA.add(resourceD);
        resA.add(resourceE);
        resA.add(resourceF);
        resA.add(resourceG);
        resB.add(resourceC);
        supA.setPOJOResources(resA);
        supB.setPOJOResources(resB);
    }
    private void usersRelations(){
        List<JobPOJO> jobListC = new ArrayList<>();
        List<RolePOJO> roleListA = new ArrayList<>();
        List<RolePOJO> roleListB = new ArrayList<>();
        List<RolePOJO> roleListC = new ArrayList<>();
        jobListC.add(jobA);
        jobListC.add(jobB);
        jobListC.add(jobC);
        jobListC.add(jobD);
        jobListC.add(jobE);
        roleListA.add(roleA);
        roleListB.add(roleB);
        roleListC.add(roleC);
        userC.setPOJOJobs(jobListC);
        userC.setPOJORoles(roleListA);
        userB.setPOJORoles(roleListB);
        userA.setPOJORoles(roleListC);
    }
    private void rolesRelations(){
        List<PrivilegePOJO> privilegeListA = new ArrayList<>();
        List<PrivilegePOJO> privilegeListB = new ArrayList<>();
        List<PrivilegePOJO> privilegeListC = new ArrayList<>();
        List<UserPOJO> userListC = new ArrayList<>();
        List<UserPOJO> userListB = new ArrayList<>();
        List<UserPOJO> userListA = new ArrayList<>();
        privilegeListA.add(privilegeA);
        privilegeListA.add(privilegeB);
        privilegeListA.add(privilegeC);
        privilegeListA.add(privilegeD);
        privilegeListA.add(privilegeE);
        privilegeListB.add(privilegeC);
        privilegeListB.add(privilegeD);
        privilegeListC.add(privilegeE);
        userListA.add(userA);
        userListB.add(userB);
        userListC.add(userC);
        roleA.setPOJOPrivileges(privilegeListA);
        roleA.setPOJOUsers(userListA);
        roleB.setPOJOPrivileges(privilegeListB);
        roleB.setPOJOUsers(userListB);
        roleC.setPOJOPrivileges(privilegeListC);
        roleC.setPOJOUsers(userListC);
    }
    private void resourcesRelations(){
        List<FormulaPOJO> formulaListA = new ArrayList<>();
        List<FormulaPOJO> formulaListB = new ArrayList<>();
        List<FormulaPOJO> formulaListC = new ArrayList<>();
        List<FormulaPOJO> formulaListD = new ArrayList<>();
        List<FormulaPOJO> formulaListE = new ArrayList<>();
        List<FormulaPOJO> formulaListF = new ArrayList<>();
        List<FormulaPOJO> formulaListG = new ArrayList<>();
        formulaListA.add(formulaA);
        formulaListA.add(formulaE);
        formulaListA.add(formulaI);
        formulaListA.add(formulaM);
        formulaListB.add(formulaB);
        formulaListB.add(formulaF);
        formulaListB.add(formulaJ);
        formulaListB.add(formulaN);
        formulaListC.add(formulaC);
        formulaListC.add(formulaG);
        formulaListC.add(formulaK);
        formulaListC.add(formulaO);
        formulaListD.add(formulaD);
        formulaListD.add(formulaH);
        formulaListD.add(formulaL);
        formulaListD.add(formulaP);
        formulaListF.add(formulaQ); // cherrycake
        formulaListE.add(formulaR); // strwaberry  cake
        formulaListE.add(formulaS); // Strawberry for choc cake
        formulaListG.add(formulaT); // chocolate for cake
        formulaListG.add(formulaU); // chocolate for donuts
        resourceA.setPOJOFormula(formulaListA);
        resourceB.setPOJOFormula(formulaListB);
        resourceC.setPOJOFormula(formulaListC);
        resourceD.setPOJOFormula(formulaListD);
        resourceE.setPOJOFormula(formulaListE);
        resourceF.setPOJOFormula(formulaListF);
        resourceG.setPOJOFormula(formulaListG);
        resourceA.setPOJOSupplier(supA);
        resourceB.setPOJOSupplier(supA);
        resourceD.setPOJOSupplier(supA);
        resourceE.setPOJOSupplier(supA);
        resourceF.setPOJOSupplier(supA);
        resourceG.setPOJOSupplier(supA);
        resourceC.setPOJOSupplier(supB);
    }
    private void productsRelations(){
        List<JobPOJO> jobListA = new ArrayList<>();
        List<JobPOJO> jobListB = new ArrayList<>();
        List<JobPOJO> jobListC = new ArrayList<>();
        List<JobPOJO> jobListD = new ArrayList<>();
        List<FormulaPOJO> formulaListA = new ArrayList<>();
        List<FormulaPOJO> formulaListB = new ArrayList<>();
        List<FormulaPOJO> formulaListC = new ArrayList<>();
        List<FormulaPOJO> formulaListD = new ArrayList<>();
        formulaListA.add(formulaA);
        formulaListA.add(formulaB);
        formulaListA.add(formulaC);
        formulaListA.add(formulaD);
        formulaListB.add(formulaE);
        formulaListB.add(formulaF);
        formulaListB.add(formulaG);
        formulaListB.add(formulaH);
        formulaListC.add(formulaI);
        formulaListC.add(formulaJ);
        formulaListC.add(formulaK);
        formulaListC.add(formulaL);
        formulaListD.add(formulaM);
        formulaListD.add(formulaN);
        formulaListD.add(formulaO);
        formulaListD.add(formulaP);
        formulaListA.add(formulaQ);
        formulaListB.add(formulaR);
        formulaListC.add(formulaS);
        formulaListC.add(formulaT);
        formulaListD.add(formulaU);
        jobListA.add(jobA);
        jobListB.add(jobB);
        jobListC.add(jobC);
        jobListD.add(jobD);
        jobListD.add(jobE);
        productA.setPOJOFormulas(formulaListA);
        productA.setPOJOJobs(jobListA);
        productB.setPOJOFormulas(formulaListB);
        productB.setPOJOJobs(jobListB);
        productC.setPOJOFormulas(formulaListC);
        productC.setPOJOJobs(jobListC);
        productD.setPOJOFormulas(formulaListD);
        productD.setPOJOJobs(jobListD);
    }
    private void privilegesRelations(){
        List<RolePOJO> roleListA = new ArrayList<>();
        List<RolePOJO> roleListB = new ArrayList<>();
        List<RolePOJO> roleListC = new ArrayList<>();
        List<RolePOJO> roleListD = new ArrayList<>();
        List<RolePOJO> roleListE = new ArrayList<>();
        roleListA.add(roleA);
        roleListB.add(roleA);
        roleListC.add(roleA);
        roleListC.add(roleB);
        roleListD.add(roleA);
        roleListD.add(roleB);
        roleListE.add(roleA);
        roleListE.add(roleC);
        privilegeA.setPOJORoles(roleListA);
        privilegeB.setPOJORoles(roleListB);
        privilegeC.setPOJORoles(roleListC);
        privilegeD.setPOJORoles(roleListD);
        privilegeE.setPOJORoles(roleListE);

    }
    private void jobsRelations(){
        jobA.setPOJOUser(userC);
        jobA.setPOJOProduct(productA);
        jobB.setPOJOUser(userC);
        jobB.setPOJOProduct(productB);
        jobC.setPOJOUser(userC);
        jobC.setPOJOProduct(productC);
        jobD.setPOJOUser(userC);
        jobD.setPOJOProduct(productD);
        jobE.setPOJOUser(userC);
        jobE.setPOJOProduct(productD);
    }
    private void formulasRelations(){
        formulaA.setPOJOProduct(productA);
        formulaB.setPOJOProduct(productA);
        formulaC.setPOJOProduct(productA);
        formulaD.setPOJOProduct(productA);
        formulaE.setPOJOProduct(productB);
        formulaF.setPOJOProduct(productB);
        formulaG.setPOJOProduct(productB);
        formulaH.setPOJOProduct(productB);
        formulaI.setPOJOProduct(productC);
        formulaJ.setPOJOProduct(productC);
        formulaK.setPOJOProduct(productC);
        formulaL.setPOJOProduct(productC);
        formulaM.setPOJOProduct(productD);
        formulaN.setPOJOProduct(productD);
        formulaO.setPOJOProduct(productD);
        formulaP.setPOJOProduct(productD);
        formulaQ.setPOJOProduct(productA);
        formulaR.setPOJOProduct(productB);
        formulaS.setPOJOProduct(productC);
        formulaT.setPOJOProduct(productC);
        formulaU.setPOJOProduct(productD);
        formulaA.setPOJOResource(resourceA);
        formulaE.setPOJOResource(resourceA);
        formulaI.setPOJOResource(resourceA);
        formulaM.setPOJOResource(resourceA);
        formulaB.setPOJOResource(resourceB);
        formulaF.setPOJOResource(resourceB);
        formulaJ.setPOJOResource(resourceB);
        formulaN.setPOJOResource(resourceB);
        formulaC.setPOJOResource(resourceC);
        formulaG.setPOJOResource(resourceC);
        formulaK.setPOJOResource(resourceC);
        formulaO.setPOJOResource(resourceC);
        formulaD.setPOJOResource(resourceD);
        formulaH.setPOJOResource(resourceD);
        formulaL.setPOJOResource(resourceD);
        formulaP.setPOJOResource(resourceD);
        formulaQ.setPOJOResource(resourceF);
        formulaR.setPOJOResource(resourceE);
        formulaS.setPOJOResource(resourceE);
        formulaT.setPOJOResource(resourceG);
        formulaU.setPOJOResource(resourceG);
    }
    private void saveToDB(){
        saveSuppliers();
        saveResources();
        saveProducts();
        saveUsers();
        saveFormulas();
        savePrivileges();
        saveRole();
        saveJobs();
    }
    private void saveFormulas(){
        formulaA = formulaService.save(formulaA);
        formulaB = formulaService.save(formulaB);
        formulaC = formulaService.save(formulaC);
        formulaD = formulaService.save(formulaD);
        formulaE = formulaService.save(formulaE);
        formulaF = formulaService.save(formulaF);
        formulaG = formulaService.save(formulaG);
        formulaH = formulaService.save(formulaH);
        formulaI = formulaService.save(formulaI);
        formulaJ = formulaService.save(formulaJ);
        formulaK = formulaService.save(formulaK);
        formulaL = formulaService.save(formulaL);
        formulaM = formulaService.save(formulaM);
        formulaN = formulaService.save(formulaN);
        formulaO = formulaService.save(formulaO);
        formulaP = formulaService.save(formulaP);
        formulaQ = formulaService.save(formulaQ);
        formulaR = formulaService.save(formulaR);
        formulaS = formulaService.save(formulaS);
        formulaT = formulaService.save(formulaT);
        formulaU = formulaService.save(formulaU);
    }
    private void savePrivileges(){
        privilegeA = privilegeService.save(privilegeA);
        privilegeB = privilegeService.save(privilegeB);
        privilegeC = privilegeService.save(privilegeC);
        privilegeD = privilegeService.save(privilegeD);
        privilegeE = privilegeService.save(privilegeE);
    }
    private void saveProducts(){
        productA = productService.save(productA);
        productB = productService.save(productB);
        productC = productService.save(productC);
        productD = productService.save(productD);
    }
    private void saveResources(){
        resourceA = resourceService.save(resourceA);
        resourceB = resourceService.save(resourceB);
        resourceC = resourceService.save(resourceC);
        resourceD = resourceService.save(resourceD);
        resourceE = resourceService.save(resourceE);
        resourceF = resourceService.save(resourceF);
        resourceG = resourceService.save(resourceG);
    }
    private void saveRole(){
        roleA = roleService.save(roleA);
        roleB = roleService.save(roleB);
        roleC = roleService.save(roleC);
    }
    private void saveSuppliers(){
        supA = supplierService.save(supA);
        supB = supplierService.save(supB);
    }
    private void saveJobs(){
        jobA = jobService.save(jobA);
        jobB = jobService.save(jobB);
        jobC = jobService.save(jobC);
        jobD = jobService.save(jobD);
        jobE = jobService.save(jobE);
    }
    private void saveUsers (){
        userA = userService.save(userA);
        userB = userService.save(userB);
        userC = userService.save(userC);
    }
}
