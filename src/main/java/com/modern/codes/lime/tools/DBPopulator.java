package com.modern.codes.lime.tools;

import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.pojo.*;
import com.modern.codes.lime.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private SupplierPOJO supA;
    private SupplierPOJO supB;
    private UserPOJO userA;
    private UserPOJO userB;
    private UserPOJO userC;
    private RolePOJO roleA;
    private RolePOJO roleB;
    private RolePOJO roleC;
    private ResourcePOJO resourceA;
    private ResourcePOJO resourceB;
    private ResourcePOJO resourceC;
    private ResourcePOJO resourceD;
    private ResourcePOJO resourceE;
    private ResourcePOJO resourceF;
    private ResourcePOJO resourceG;
    private ProductPOJO productA;
    private ProductPOJO productB;
    private ProductPOJO productC;
    private ProductPOJO productD;
    private PrivilegePOJO privilegeA;
    private PrivilegePOJO privilegeB;
    private PrivilegePOJO privilegeC;
    private PrivilegePOJO privilegeD;
    private PrivilegePOJO privilegeE;
    private JobPOJO jobA;
    private JobPOJO jobB;
    private JobPOJO jobC;
    private JobPOJO jobD;
    private JobPOJO jobE;
    private FormulaPOJO formulaA;
    private FormulaPOJO formulaB;
    private FormulaPOJO formulaC;
    private FormulaPOJO formulaD;
    private FormulaPOJO formulaE;
    private FormulaPOJO formulaF;
    private FormulaPOJO formulaG;
    private FormulaPOJO formulaH;
    private FormulaPOJO formulaI;
    private FormulaPOJO formulaJ;
    private FormulaPOJO formulaK;
    private FormulaPOJO formulaL;
    private FormulaPOJO formulaM;
    private FormulaPOJO formulaN;
    private FormulaPOJO formulaO;
    private FormulaPOJO formulaP;
    private FormulaPOJO formulaQ;
    private FormulaPOJO formulaR;
    private FormulaPOJO formulaS;
    private FormulaPOJO formulaT;
    private FormulaPOJO formulaU;

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
        deleteUsers();
        deleteRoles();
        deletePrivileges();
        deleteJobs();
        deleteFormulas();
        deleteProducts();
        deleteResources();
        deleteSuppliers();
    }

    public void deleteSuppliers() {
        supplierService.deleteAll();
    }

    public void deleteResources() {
        resourceService.deleteAll();
    }

    public void deleteProducts() {
        productService.deleteAll();
    }

    public void deleteFormulas() {
        formulaService.deleteAll();
    }

    public void deleteJobs() {
        jobService.deleteAll();
    }

    public void deletePrivileges() {
        privilegeService.deleteAll();
    }

    public void deleteRoles() {
        roleService.deleteAll();
    }

    public void deleteUsers() {
        userService.deleteAll();
    }

    public void setSuppliers() {
        supA = new SupplierPOJO();
        supB = new SupplierPOJO();
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
    }
    public void setUsers(){
        userA = new UserPOJO();
        userB = new UserPOJO();
        userC = new UserPOJO();
        userA.setPassword("passA");
        userA.setLogin("loginA");
        userA.setJoinedAt(ParseTools.parseDate("2017-05-05 12:00:00"));
        userA.setName("Maciej");
        userA.setSurname("Glowala");
        userB.setPassword("passB");
        userB.setLogin("loginB");
        userB.setJoinedAt(ParseTools.parseDate("2017-05-06 12:00:00"));
        userB.setName("Klaudia");
        userB.setSurname("Jarosz");
        userC.setPassword("passC");
        userC.setLogin("loginC");
        userC.setJoinedAt(ParseTools.parseDate("2017-05-06 12:00:00"));
        userC.setName("Aleksandra");
        userC.setSurname("Bulka");
    }
    public void setRoles(){
        roleA = new RolePOJO();
        roleB = new RolePOJO();
        roleC = new RolePOJO();
        roleA.setName("Administrator");
        roleB.setName("Manager");
        roleC.setName("Staff");
    }
    public void setResources(){
        resourceA = new ResourcePOJO();
        resourceB = new ResourcePOJO();
        resourceC = new ResourcePOJO();
        resourceD = new ResourcePOJO();
        resourceE = new ResourcePOJO();
        resourceF = new ResourcePOJO();
        resourceG = new ResourcePOJO();
        resourceA.setName("Flour");
        resourceA.setUnit(Unit.KG);
        resourceA.setQuantity((double)30);
        resourceA.setDescription("Description for resource A");
        resourceA.setCategory("Basic");
        resourceB.setName("Milk");
        resourceB.setUnit(Unit.LITER);
        resourceB.setQuantity((double)20);
        resourceB.setDescription("Description for resource B");
        resourceB.setCategory("Basic");
        resourceC.setName("EGG");
        resourceC.setUnit(Unit.UNIT);
        resourceC.setQuantity((double)50);
        resourceC.setDescription("Description for resource C");
        resourceC.setCategory("Basic");
        resourceD.setName("SUGAR");
        resourceD.setUnit(Unit.KG);
        resourceD.setQuantity((double)20);
        resourceD.setDescription("Description for resource D");
        resourceD.setCategory("Additional");
        resourceE.setName("Strawberry");
        resourceE.setUnit(Unit.KG);
        resourceE.setQuantity((double)10);
        resourceE.setDescription("Description for resource E");
        resourceE.setCategory("Additional");
        resourceF.setName("Cherry");
        resourceF.setUnit(Unit.KG);
        resourceF.setQuantity((double)10);
        resourceF.setDescription("Description for resource F");
        resourceF.setCategory("Additional");
        resourceG.setName("Chocolate");  //formula  S U
        resourceG.setUnit(Unit.BAR);
        resourceG.setQuantity((double)30);
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

    public void setProducts(){
        productA = new ProductPOJO();
        productB = new ProductPOJO();
        productC = new ProductPOJO();
        productD = new ProductPOJO();
        productA.setUnit(Unit.UNIT);
        productA.setName("Cherry Cake");
        productA.setExpectedValue((double)1);
        productA.setDescription("Description for product A");
        productA.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productA.setCategory("Cake");
        productB.setUnit(Unit.UNIT);
        productB.setName("Strawberry Cake");
        productB.setExpectedValue((double)1);
        productB.setDescription("Description for product B");
        productB.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productB.setCategory("Cake");
        productC.setUnit(Unit.UNIT);
        productC.setName("Chocolate Cake with Strawberries");
        productC.setExpectedValue((double)1);
        productC.setDescription("Description for product C");
        productC.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productC.setCategory("Cake");
        productD.setUnit(Unit.UNIT);
        productD.setName("Donut with chocolate");
        productD.setExpectedValue((double)20);
        productD.setDescription("Description for product D");
        productD.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productD.setCategory("Donut");

        productA.setImage("https://tinyurl.com/ya9ene6r");
        productB.setImage("https://tinyurl.com/ya9ene6r");
        productC.setImage("https://tinyurl.com/ya9ene6r");
        productD.setImage("https://tinyurl.com/ya9ene6r");
    }

    public void setPrivileges(){
        privilegeA = new PrivilegePOJO();
        privilegeB = new PrivilegePOJO();
        privilegeC = new PrivilegePOJO();
        privilegeD = new PrivilegePOJO();
        privilegeE = new PrivilegePOJO();
        privilegeA.setName("Manage Users");
        privilegeB.setName("Manage Recipes");
        privilegeC.setName("Get Reports");
        privilegeD.setName("Manage Resources");
        privilegeE.setName("Create Product");
    }
    public void setJobs() {
        jobA = new JobPOJO();
        jobB = new JobPOJO();
        jobC = new JobPOJO();
        jobD = new JobPOJO();
        jobE = new JobPOJO();
        jobA.setStartDate(ParseTools.parseDate("2017-07-01 12:00:00"));
        jobA.setEndDate(ParseTools.parseDate("2017-07-01 16:00:00"));
        jobA.setResultValue((double)1);
        jobA.setDetails("Details for job A. Making cherry cake.");
        jobB.setStartDate(ParseTools.parseDate("2017-07-02 12:00:00"));
        jobB.setEndDate(ParseTools.parseDate("2017-07-02 16:00:00"));
        jobB.setResultValue((double)1);
        jobB.setDetails("Details for job B. Making Strawberry cake");
        jobC.setStartDate(ParseTools.parseDate("2017-07-03 12:00:00"));
        jobC.setEndDate(ParseTools.parseDate("2017-07-03 16:00:00"));
        jobC.setResultValue((double)1);
        jobC.setDetails("Details for job C. Making chocolate cake with strawberries");
        jobD.setStartDate(ParseTools.parseDate("2017-07-04 12:00:00"));
        jobD.setEndDate(ParseTools.parseDate("2017-07-04 16:00:00"));
        jobD.setResultValue((double)20);
        jobD.setDetails("Details for job D. Making donuts");
        jobE.setStartDate(ParseTools.parseDate("2017-07-05 12:00:00"));
        jobE.setEndDate(ParseTools.parseDate("2017-07-05 16:00:00"));
        jobE.setResultValue((double)19);
        jobE.setDetails("Details for job E. Making donuts");
    }
    public void setFormulas(){
        formulaA = new FormulaPOJO();
        formulaB = new FormulaPOJO();
        formulaC = new FormulaPOJO();
        formulaD = new FormulaPOJO();
        formulaE = new FormulaPOJO();
        formulaF = new FormulaPOJO();
        formulaG = new FormulaPOJO();
        formulaH = new FormulaPOJO();
        formulaI = new FormulaPOJO();
        formulaJ = new FormulaPOJO();
        formulaK = new FormulaPOJO();
        formulaL = new FormulaPOJO();
        formulaM = new FormulaPOJO();
        formulaN = new FormulaPOJO();
        formulaO = new FormulaPOJO();
        formulaP = new FormulaPOJO();
        formulaQ = new FormulaPOJO();
        formulaR = new FormulaPOJO();
        formulaS = new FormulaPOJO();
        formulaT = new FormulaPOJO();
        formulaU = new FormulaPOJO();
        formulaA.setValue((double)1);
        formulaB.setValue((double)1);
        formulaC.setValue((double)5);
        formulaD.setValue(0.1);
        formulaE.setValue((double)1);
        formulaF.setValue((double)1);
        formulaG.setValue((double)5);
        formulaH.setValue(0.1);
        formulaI.setValue((double)1);
        formulaJ.setValue((double)1);
        formulaK.setValue((double)5);
        formulaL.setValue(0.1);
        formulaM.setValue((double)1);
        formulaN.setValue((double)1);
        formulaO.setValue((double)5);
        formulaP.setValue(0.1);
        formulaQ.setValue(0.2);
        formulaR.setValue(0.2);
        formulaS.setValue((double)1);
        formulaT.setValue(0.1);
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
        saveRoles();
        saveJobs();
    }
    public void saveFormulas(){
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
    public void savePrivileges(){
        privilegeA = privilegeService.save(privilegeA);
        privilegeB = privilegeService.save(privilegeB);
        privilegeC = privilegeService.save(privilegeC);
        privilegeD = privilegeService.save(privilegeD);
        privilegeE = privilegeService.save(privilegeE);
    }
    public void saveProducts(){
        productA = productService.save(productA);
        productB = productService.save(productB);
        productC = productService.save(productC);
        productD = productService.save(productD);
    }
    public void saveResources(){
        resourceA = resourceService.save(resourceA);
        resourceB = resourceService.save(resourceB);
        resourceC = resourceService.save(resourceC);
        resourceD = resourceService.save(resourceD);
        resourceE = resourceService.save(resourceE);
        resourceF = resourceService.save(resourceF);
        resourceG = resourceService.save(resourceG);
    }
    public void saveRoles(){
        roleA = roleService.save(roleA);
        roleB = roleService.save(roleB);
        roleC = roleService.save(roleC);
    }
    public void saveSuppliers(){
        supA = supplierService.save(supA);
        supB = supplierService.save(supB);
    }
    public void saveJobs(){
        jobA = jobService.save(jobA);
        jobB = jobService.save(jobB);
        jobC = jobService.save(jobC);
        jobD = jobService.save(jobD);
        jobE = jobService.save(jobE);
    }
    public void saveUsers (){
        userA = userService.save(userA);
        userB = userService.save(userB);
        userC = userService.save(userC);
    }
}
