package com.modern.codes.lime.tools;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ProductCategoryPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.pojo.ResourceCategoryPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.SupplierPOJO;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.service.FormulaService;
import com.modern.codes.lime.service.JobService;
import com.modern.codes.lime.service.ProductCategoryService;
import com.modern.codes.lime.service.ProductService;
import com.modern.codes.lime.service.ResourceCategoryService;
import com.modern.codes.lime.service.ResourceService;
import com.modern.codes.lime.service.RoleService;
import com.modern.codes.lime.service.SupplierService;
import com.modern.codes.lime.service.UserService;

/**
 * The type Db populator.
 */
@Service
public class DBPopulator {

    /**
     * Instantiates a new Db populator.
     *
     * @param jobService              the job service
     * @param formulaService          the formula service
     * @param productService          the product service
     * @param resourceService         the resource service
     * @param roleService             the role service
     * @param supplierService         the supplier service
     * @param userService             the user service
     * @param resourceCategoryService the resource category service
     * @param productCategoryService  the product category service
     */
    @Autowired
    public DBPopulator(JobService jobService, FormulaService formulaService, ProductService productService,
                       ResourceService resourceService, RoleService roleService, SupplierService supplierService,
                       UserService userService, ResourceCategoryService resourceCategoryService,
                       ProductCategoryService productCategoryService) {
        this.jobService = jobService;
        this.formulaService = formulaService;
        this.productService = productService;
        this.resourceService = resourceService;
        this.roleService = roleService;
        this.supplierService = supplierService;
        this.userService = userService;
        this.resourceCategoryService = resourceCategoryService;
        this.productCategoryService = productCategoryService;
    }

    /**
     * Populate.
     */
    public void populate() {
        clearDB();
        setResourceCategories();
        setProductCategories();
        setSuppliers();
        setUsers();
        setRoles();
        setResources();
        setProducts();
        setJobs();
        setFormulas();
        saveToDB();
        resourceCategoriesRelations();
        productCategoriesRelations();
        suppliersRelations();
        usersRelations();
        rolesRelations();
        resourcesRelations();
        productsRelations();
        jobsRelations();
        formulasRelations();
        saveToDB();
    }

    /**
     * Clear db.
     */
    public void clearDB() {
        deleteUsers();
        deleteRoles();
        deleteJobs();
        deleteFormulas();
        deleteProducts();
        deleteResources();
        deleteSuppliers();
        deleteResourceCategories();
        deleteProductCategories();
    }

    /**
     * Delete resource categories.
     */
    public void deleteResourceCategories() {
        resourceCategoryService.deleteAll();
    }

    /**
     * Delete product categories.
     */
    public void deleteProductCategories() {
        productCategoryService.deleteAll();
    }

    /**
     * Delete suppliers.
     */
    public void deleteSuppliers() {
        supplierService.deleteAll();
    }

    /**
     * Delete resources.
     */
    public void deleteResources() {
        resourceService.deleteAll();
    }

    /**
     * Delete products.
     */
    public void deleteProducts() {
        productService.deleteAll();
    }

    /**
     * Delete formulas.
     */
    public void deleteFormulas() {
        formulaService.deleteAll();
    }

    /**
     * Delete jobs.
     */
    public void deleteJobs() {
        jobService.deleteAll();
    }

    /**
     * Delete roles.
     */
    public void deleteRoles() {
        roleService.deleteAll();
    }

    /**
     * Delete users.
     */
    public void deleteUsers() {
        userService.deleteAll();
    }

    /**
     * Sets suppliers.
     */
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

    /**
     * Sets users.
     */
    public void setUsers() {
        userA = new UserPOJO();
        userB = new UserPOJO();
        userC = new UserPOJO();
        userA.setPlainPassword("passA");
        userA.setUsername("loginA");
        userA.setJoinedAt(ParseTools.parseDate("2017-05-05 12:00:00"));
        userA.setName("Maciej");
        userA.setSurname("Glowala");
        userA.setEmailAddress("glowalam@student.mini.pw.pl");
        userB.setPlainPassword("passB");
        userB.setUsername("loginB");
        userB.setJoinedAt(ParseTools.parseDate("2017-05-06 12:00:00"));
        userB.setName("Klaudia");
        userB.setSurname("Jarosz");
        userB.setEmailAddress("jaroszk@student.mini.pw.pl");
        userC.setPlainPassword("passC");
        userC.setUsername("loginC");
        userC.setJoinedAt(ParseTools.parseDate("2017-05-06 12:00:00"));
        userC.setName("Aleksandra");
        userC.setSurname("Bulka");
        userC.setEmailAddress("bulkaa@student.mini.pw.pl");
    }

    /**
     * Sets roles.
     */
    public void setRoles() {
        roleA = new RolePOJO();
        roleB = new RolePOJO();
        roleC = new RolePOJO();
        roleA.setName("ROLE_ADMIN");
        roleB.setName("ROLE_MANAGER");
        roleC.setName("ROLE_STAFF");
    }

    /**
     * Sets resources.
     */
    public void setResources() {
        resourceA = new ResourcePOJO();
        resourceB = new ResourcePOJO();
        resourceC = new ResourcePOJO();
        resourceD = new ResourcePOJO();
        resourceE = new ResourcePOJO();
        resourceF = new ResourcePOJO();
        resourceG = new ResourcePOJO();
        resourceA.setName("Flour");
        resourceA.setUnit(Unit.KG);
        resourceA.setQuantity((double) 30);
        resourceA.setDescription("Description for resource A");
        resourceB.setName("Milk");
        resourceB.setUnit(Unit.LITER);
        resourceB.setQuantity((double) 20);
        resourceB.setDescription("Description for resource B");
        resourceC.setName("EGG");
        resourceC.setUnit(Unit.UNIT);
        resourceC.setQuantity((double) 50);
        resourceC.setDescription("Description for resource C");
        resourceD.setName("SUGAR");
        resourceD.setUnit(Unit.KG);
        resourceD.setQuantity((double) 20);
        resourceD.setDescription("Description for resource D");
        resourceE.setName("Strawberry");
        resourceE.setUnit(Unit.KG);
        resourceE.setQuantity((double) 10);
        resourceE.setDescription("Description for resource E");
        resourceF.setName("Cherry");
        resourceF.setUnit(Unit.KG);
        resourceF.setQuantity((double) 10);
        resourceF.setDescription("Description for resource F");
        resourceG.setName("Chocolate");  //formula  S U
        resourceG.setUnit(Unit.BAR);
        resourceG.setQuantity((double) 30);
        resourceG.setDescription("Description for resource G");

        resourceA.setImage("no_image.jpg");
        resourceB.setImage("no_image.jpg");
        resourceC.setImage("no_image.jpg");
        resourceD.setImage("no_image.jpg");
        resourceE.setImage("no_image.jpg");
        resourceF.setImage("no_image.jpg");
        resourceG.setImage("no_image.jpg");

        resourceA.setCritical_value(0.0);
        resourceB.setCritical_value(0.0);
        resourceC.setCritical_value(0.0);
        resourceD.setCritical_value(0.0);
        resourceE.setCritical_value(0.0);
        resourceF.setCritical_value(0.0);
        resourceG.setCritical_value(0.0);

        resourceA.setOrdering_on(false);
        resourceB.setOrdering_on(false);
        resourceC.setOrdering_on(false);
        resourceD.setOrdering_on(false);
        resourceE.setOrdering_on(false);
        resourceF.setOrdering_on(false);
        resourceG.setOrdering_on(false);

        resourceA.setNotifications_on(false);
        resourceB.setNotifications_on(false);
        resourceC.setNotifications_on(false);
        resourceD.setNotifications_on(false);
        resourceE.setNotifications_on(false);
        resourceF.setNotifications_on(false);
        resourceG.setNotifications_on(false);
    }

    /**
     * Sets products.
     */
    public void setProducts() {
        productA = new ProductPOJO();
        productB = new ProductPOJO();
        productC = new ProductPOJO();
        productD = new ProductPOJO();
        productA.setUnit(Unit.UNIT);
        productA.setName("Cherry Cake");
        productA.setExpectedValue((double) 1);
        productA.setDescription("Description for product A");
        productA.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productB.setUnit(Unit.UNIT);
        productB.setName("Strawberry Cake");
        productB.setExpectedValue((double) 1);
        productB.setDescription("Description for product B");
        productB.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productC.setUnit(Unit.UNIT);
        productC.setName("Chocolate Cake with Strawberries");
        productC.setExpectedValue((double) 1);
        productC.setDescription("Description for product C");
        productC.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productD.setUnit(Unit.UNIT);
        productD.setName("Donut");
        productD.setExpectedValue((double) 20);
        productD.setDescription("Description for product D");
        productD.setAddedAt(ParseTools.parseDate("2017-05-01 12:00:00"));
        productA.setImage("no_image.jpg");
        productB.setImage("no_image.jpg");
        productC.setImage("no_image.jpg");
        productD.setImage("no_image.jpg");
    }

    /**
     * Sets jobs.
     */
    public void setJobs() {
        jobA = new JobPOJO();
        jobB = new JobPOJO();
        jobC = new JobPOJO();
        jobD = new JobPOJO();
        jobE = new JobPOJO();
        jobA.setStartDate(ParseTools.parseDate("2018-01-11 12:00:00"));
        jobA.setEndDate(ParseTools.parseDate("2018-01-11 16:00:00"));
        jobA.setResultValue((double) 24);
        jobA.setDetails("Details for job A. Making cherry cake.");
        jobB.setStartDate(ParseTools.parseDate("2018-01-12 12:00:00"));
        jobB.setEndDate(ParseTools.parseDate("2018-01-12 16:00:00"));
        jobB.setResultValue((double) 11);
        jobB.setDetails("Details for job B. Making Strawberry cake");
        jobC.setStartDate(ParseTools.parseDate("2018-01-10 12:00:00"));
        jobC.setEndDate(ParseTools.parseDate("2018-01-10 16:00:00"));
        jobC.setResultValue((double) 31);
        jobC.setDetails("Details for job C. Making chocolate cake with strawberries");
        jobD.setStartDate(ParseTools.parseDate("2018-01-09 12:00:00"));
        jobD.setEndDate(ParseTools.parseDate("2018-01-09 16:00:00"));
        jobD.setResultValue((double) 20);
        jobD.setDetails("Details for job D. Making donuts");
        jobE.setStartDate(ParseTools.parseDate("2018-01-13 12:00:00"));
        jobE.setEndDate(ParseTools.parseDate("2018-01-13 16:00:00"));
        jobE.setResultValue((double) 19);
        jobE.setDetails("Details for job E. Making donuts");
    }

    /**
     * Sets formulas.
     */
    public void setFormulas() {
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
        formulaA.setValue((double) 1);
        formulaB.setValue((double) 1);
        formulaC.setValue((double) 5);
        formulaD.setValue(0.1);
        formulaE.setValue((double) 1);
        formulaF.setValue((double) 1);
        formulaG.setValue((double) 5);
        formulaH.setValue(0.1);
        formulaI.setValue((double) 1);
        formulaJ.setValue((double) 1);
        formulaK.setValue((double) 5);
        formulaL.setValue(0.1);
        formulaM.setValue((double) 1);
        formulaN.setValue((double) 1);
        formulaO.setValue((double) 5);
        formulaP.setValue(0.1);
        formulaQ.setValue(0.2);
        formulaR.setValue(0.2);
        formulaS.setValue((double) 1);
        formulaT.setValue(0.1);
        formulaU.setValue((double) 1);
    }

    /**
     * Save formulas.
     */
    public void saveFormulas() {
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

    /**
     * Save products.
     */
    public void saveProducts() {
        productA = productService.save(productA);
        productB = productService.save(productB);
        productC = productService.save(productC);
        productD = productService.save(productD);
    }

    /**
     * Save resources.
     */
    public void saveResources() {
        resourceA = resourceService.save(resourceA);
        resourceB = resourceService.save(resourceB);
        resourceC = resourceService.save(resourceC);
        resourceD = resourceService.save(resourceD);
        resourceE = resourceService.save(resourceE);
        resourceF = resourceService.save(resourceF);
        resourceG = resourceService.save(resourceG);
    }

    /**
     * Save roles.
     */
    public void saveRoles() {
        roleA = roleService.save(roleA);
        roleB = roleService.save(roleB);
        roleC = roleService.save(roleC);
    }

    /**
     * Save suppliers.
     */
    public void saveSuppliers() {
        supA = supplierService.save(supA);
        supB = supplierService.save(supB);
    }

    /**
     * Save jobs.
     */
    public void saveJobs() {
        jobA = jobService.save(jobA);
        jobB = jobService.save(jobB);
        jobC = jobService.save(jobC);
        jobD = jobService.save(jobD);
        jobE = jobService.save(jobE);
    }

    /**
     * Save users.
     */
    public void saveUsers() {
        userA = userService.save(userA);
        userB = userService.save(userB);
        userC = userService.save(userC);
    }

    private void setResourceCategories() {
        resourceCategoryA = new ResourceCategoryPOJO();
        resourceCategoryB = new ResourceCategoryPOJO();
        resourceCategoryA.setName("Basics");
        resourceCategoryB.setName("Fruits");
    }

    private void setProductCategories() {
        productCategoryA = new ProductCategoryPOJO();
        productCategoryB = new ProductCategoryPOJO();
        productCategoryA.setName("Cakes");
        productCategoryB.setName("Others");
    }

    private void productCategoriesRelations() {
        // catB products: D, catA a b c
        List<ProductPOJO> prodA = new ArrayList<>();
        List<ProductPOJO> prodB = new ArrayList<>();
        prodA.add(productA);
        prodA.add(productB);
        prodA.add(productC);
        prodB.add(productD);
        productCategoryA.setPOJOProducts(prodA);
        productCategoryB.setPOJOProducts(prodB);
    }

    private void resourceCategoriesRelations() {
        List<ResourcePOJO> resA = new ArrayList<>();
        List<ResourcePOJO> resB = new ArrayList<>();
        resA.add(resourceA);
        resA.add(resourceB);
        resA.add(resourceC);
        resA.add(resourceD);
        resA.add(resourceG);
        resB.add(resourceE);
        resB.add(resourceF);
        resourceCategoryA.setPOJOResources(resA);
        resourceCategoryB.setPOJOResources(resB);
    }

    private void suppliersRelations() {
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

    private void usersRelations() {
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
        roleListB.add(roleA);
        roleListC.add(roleC);
        userC.setPOJOJobs(jobListC);
        userC.setPOJORoles(roleListC);
        userB.setPOJORoles(roleListB);
        userA.setPOJORoles(roleListA);
    }

    private void rolesRelations() {
        List<UserPOJO> userListC = new ArrayList<>();
        List<UserPOJO> userListB = new ArrayList<>();
        List<UserPOJO> userListA = new ArrayList<>();
        userListA.add(userA);
        userListB.add(userB);
        userListC.add(userC);
        roleA.setPOJOUsers(userListA);
        roleB.setPOJOUsers(userListB);
        roleB.setPOJOUsers(userListB);
        roleC.setPOJOUsers(userListC);
    }

    private void resourcesRelations() {
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
        resourceG.setPOJOCategory(resourceCategoryA);
        resourceF.setPOJOCategory(resourceCategoryB);
        resourceE.setPOJOCategory(resourceCategoryB);
        resourceB.setPOJOCategory(resourceCategoryA);
        resourceA.setPOJOCategory(resourceCategoryA);
        resourceC.setPOJOCategory(resourceCategoryA);
        resourceD.setPOJOCategory(resourceCategoryA);
        resourceA.setPOJOFormulas(formulaListA);
        resourceB.setPOJOFormulas(formulaListB);
        resourceC.setPOJOFormulas(formulaListC);
        resourceD.setPOJOFormulas(formulaListD);
        resourceE.setPOJOFormulas(formulaListE);
        resourceF.setPOJOFormulas(formulaListF);
        resourceG.setPOJOFormulas(formulaListG);
        resourceA.setPOJOSupplier(supA);
        resourceB.setPOJOSupplier(supA);
        resourceD.setPOJOSupplier(supA);
        resourceE.setPOJOSupplier(supA);
        resourceF.setPOJOSupplier(supA);
        resourceG.setPOJOSupplier(supA);
        resourceC.setPOJOSupplier(supB);
    }

    private void productsRelations() {
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
        productD.setPOJOCategory(productCategoryB);
        productC.setPOJOCategory(productCategoryA);
        productB.setPOJOCategory(productCategoryA);
        productA.setPOJOCategory(productCategoryA);
        productA.setPOJOFormulas(formulaListA);
        productA.setPOJOJobs(jobListA);
        productB.setPOJOFormulas(formulaListB);
        productB.setPOJOJobs(jobListB);
        productC.setPOJOFormulas(formulaListC);
        productC.setPOJOJobs(jobListC);
        productD.setPOJOFormulas(formulaListD);
        productD.setPOJOJobs(jobListD);
    }

    private void jobsRelations() {
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

    private void formulasRelations() {
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

    private void saveToDB() {
        saveResourceCategories();
        saveProductCategories();
        saveSuppliers();
        saveResources();
        saveProducts();
        saveUsers();
        saveRoles();
        saveJobs();
        saveFormulas();
    }

    private void saveResourceCategories() {
        resourceCategoryA = resourceCategoryService.save(resourceCategoryA);
        resourceCategoryB = resourceCategoryService.save(resourceCategoryB);
    }

    private void saveProductCategories() {
        productCategoryA = productCategoryService.save(productCategoryA);
        productCategoryB = productCategoryService.save(productCategoryB);
    }

    private JobService jobService;
    private FormulaService formulaService;
    private ProductService productService;
    private ResourceService resourceService;
    private RoleService roleService;
    private SupplierService supplierService;
    private UserService userService;
    private ResourceCategoryService resourceCategoryService;
    private ProductCategoryService productCategoryService;
    private ResourceCategoryPOJO resourceCategoryA;
    private ResourceCategoryPOJO resourceCategoryB;
    private ProductCategoryPOJO productCategoryA;
    private ProductCategoryPOJO productCategoryB;
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
}
