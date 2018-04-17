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
@SuppressWarnings("Duplicates")
public class ThesisPopulator {

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
    public ThesisPopulator(JobService jobService, FormulaService formulaService, ProductService productService,
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
        fruitSupplier = new SupplierPOJO();
        basicSupplier = new SupplierPOJO();
        fruitSupplier.setName("Giełda okęcie");
        basicSupplier.setName("Hurtownia-spoyzwcya.pl");
        fruitSupplier.setTelephone("+48 (22) 868 66 03");
        basicSupplier.setTelephone("+48 (22) 721 15 42 ");
        fruitSupplier.setStreet("Leonidasa 55");
        basicSupplier.setStreet("Sochaczewska 156");
        fruitSupplier.setPostalCode("02-239");
        basicSupplier.setPostalCode("05-850");
        fruitSupplier.setCountry("Polska");
        basicSupplier.setCountry("Polska");
        fruitSupplier.setCity("Warszawa");
        basicSupplier.setCity("Wieruchów");
        fruitSupplier.setEmailAddress("hubertpawlowski8@fakemail.com");
        basicSupplier.setEmailAddress("hurtownia@fakemail.com");
    }

    /**
     * Sets users.
     */
    public void setUsers() {
        userAdmin = new UserPOJO();
        userManager = new UserPOJO();
        userStaff = new UserPOJO();
        userAdmin.setPlainPassword("admin1234");
        userAdmin.setUsername("admin");
        userAdmin.setJoinedAt(ParseTools.parseDate("2018-04-12 11:23:02"));
        userAdmin.setName("Maciej");
        userAdmin.setSurname("Głowala");
        userAdmin.setEmailAddress("glowalam@student.mini.pw.edu.pl");
        userManager.setPlainPassword("manager1234");
        userManager.setUsername("manager");
        userManager.setJoinedAt(ParseTools.parseDate("2018-04-12 11:24:02"));
        userManager.setName("Klaudia");
        userManager.setSurname("Jarosz");
        userManager.setEmailAddress("jaroszk@student.mini.pw.edu.pl");
        userStaff.setPlainPassword("staff1234");
        userStaff.setUsername("staff");
        userStaff.setJoinedAt(ParseTools.parseDate("2018-04-13 11:24:02"));
        userStaff.setName("Aleksandra");
        userStaff.setSurname("Bułka");
        userStaff.setEmailAddress("bulkaa@student.mini.pw.edu.pl");
    }

    /**
     * Sets roles.
     */
    public void setRoles() {
        roleAdmin = new RolePOJO();
        roleManager = new RolePOJO();
        roleStaff = new RolePOJO();
        roleAdmin.setName("ROLE_ADMIN");
        roleManager.setName("ROLE_MANAGER");
        roleStaff.setName("ROLE_STAFF");
    }

    /**
     * Sets resources.
     */
    public void setResources() {
        resourceFlour = new ResourcePOJO();
        resourceMilk = new ResourcePOJO();
        resourceEgg = new ResourcePOJO();
        resourceSugar = new ResourcePOJO();
        resourceStrawberry = new ResourcePOJO();
        resourceCherry = new ResourcePOJO();
        resourceChocolate = new ResourcePOJO();
        resourceFlour.setName("Flour");
        resourceFlour.setUnit(Unit.KG);
        resourceFlour.setQuantity((double) 30);
        resourceFlour.setDescription("Description for resource A");
        resourceMilk.setName("Milk");
        resourceMilk.setUnit(Unit.LITER);
        resourceMilk.setQuantity((double) 20);
        resourceMilk.setDescription("Description for resource B");
        resourceEgg.setName("EGG");
        resourceEgg.setUnit(Unit.UNIT);
        resourceEgg.setQuantity((double) 100);
        resourceEgg.setDescription("Description for resource C");
        resourceSugar.setName("SUGAR");
        resourceSugar.setUnit(Unit.KG);
        resourceSugar.setQuantity((double) 20);
        resourceSugar.setDescription("Description for resource D");
        resourceStrawberry.setName("Strawberry");
        resourceStrawberry.setUnit(Unit.KG);
        resourceStrawberry.setQuantity((double) 10);
        resourceStrawberry.setDescription("Description for resource E");
        resourceCherry.setName("Cherry");
        resourceCherry.setUnit(Unit.KG);
        resourceCherry.setQuantity((double) 10);
        resourceCherry.setDescription("Description for resource F");
        resourceChocolate.setName("Chocolate");  //formula  S U
        resourceChocolate.setUnit(Unit.BAR);
        resourceChocolate.setQuantity((double) 30);
        resourceChocolate.setDescription("Description for resource G");

        resourceFlour.setImage("thesis_flour.jpg");
        resourceMilk.setImage("thesis_milk.jpg");
        resourceEgg.setImage("thesis_egg.jpg");
        resourceSugar.setImage("thesis_sugar.jpg");
        resourceStrawberry.setImage("thesis_strawberry.jpg");
        resourceCherry.setImage("thesis_cherry.jpg");
        resourceChocolate.setImage("thesis_chocolate.jpg");

        resourceFlour.setCritical_value(5.0);
        resourceMilk.setCritical_value(5.0);
        resourceEgg.setCritical_value(20.0);
        resourceSugar.setCritical_value(5.0);
        resourceStrawberry.setCritical_value(1.0);
        resourceCherry.setCritical_value(1.0);
        resourceChocolate.setCritical_value(5.0);

        resourceFlour.setOrdering_on(false);
        resourceMilk.setOrdering_on(false);
        resourceEgg.setOrdering_on(false);
        resourceSugar.setOrdering_on(false);
        resourceStrawberry.setOrdering_on(false);
        resourceCherry.setOrdering_on(false);
        resourceChocolate.setOrdering_on(false);

        resourceFlour.setNotifications_on(false);
        resourceMilk.setNotifications_on(false);
        resourceEgg.setNotifications_on(false);
        resourceSugar.setNotifications_on(false);
        resourceStrawberry.setNotifications_on(false);
        resourceCherry.setNotifications_on(false);
        resourceChocolate.setNotifications_on(false);
    }

    /**
     * Sets products.
     */
    public void setProducts() {
        productCherryCake = new ProductPOJO();
        productStrawberryCake = new ProductPOJO();
        productChocolateStrawberryCake = new ProductPOJO();
        productDonutWithChocolate = new ProductPOJO();
        productCherryCake.setUnit(Unit.UNIT);
        productCherryCake.setName("Cherry Cake");
        productCherryCake.setExpectedValue((double) 1);
        productCherryCake.setDescription("Delicious cherry cake with fresh cherries");
        productCherryCake.setAddedAt(ParseTools.parseDate("2018-04-12 11:30:02"));
        productStrawberryCake.setUnit(Unit.UNIT);
        productStrawberryCake.setName("Strawberry Cake");
        productStrawberryCake.setExpectedValue((double) 1);
        productStrawberryCake.setDescription("Delicious strawberry cake with fresh strawberries");
        productStrawberryCake.setAddedAt(ParseTools.parseDate("2018-04-12 11:30:02"));
        productChocolateStrawberryCake.setUnit(Unit.UNIT);
        productChocolateStrawberryCake.setName("Chocolate Cake with Strawberries");
        productChocolateStrawberryCake.setExpectedValue((double) 1);
        productChocolateStrawberryCake.setDescription("Delicious chocolate cake with fresh strawberries");
        productChocolateStrawberryCake.setAddedAt(ParseTools.parseDate("2018-04-12 11:30:02"));
        productDonutWithChocolate.setUnit(Unit.UNIT);
        productDonutWithChocolate.setName("Donut");
        productDonutWithChocolate.setExpectedValue((double) 20);
        productDonutWithChocolate.setDescription("Small round donuts with chocolate cream");
        productDonutWithChocolate.setAddedAt(ParseTools.parseDate("2018-04-12 11:30:02"));
        productCherryCake.setImage("thesis_cherry_cake.jpg");
        productStrawberryCake.setImage("thesis_strawberry_cake.jpg");
        productChocolateStrawberryCake.setImage("thesis_chocolate_cake_with_strawberries.jpg");
        productDonutWithChocolate.setImage("thesis_donut.jpg");
    }

    /**
     * Sets jobs.
     */
    public void setJobs() {
        jobCherryCake = new JobPOJO();
        jobStrawberryCake = new JobPOJO();
        jobChocolateStrawberryCake = new JobPOJO();
        jobDonut1 = new JobPOJO();
        jobDonut2 = new JobPOJO();
        jobCherryCake.setStartDate(ParseTools.parseDate("2018-04-12 11:30:02"));
        jobCherryCake.setEndDate(ParseTools.parseDate("2018-04-12 13:30:02"));
        jobCherryCake.setResultValue((double) 24);
        jobCherryCake.setDetails("Everything gone ok.");
        jobStrawberryCake.setStartDate(ParseTools.parseDate("2018-04-06 13:30:02"));
        jobStrawberryCake.setEndDate(ParseTools.parseDate("2018-04-06 17:30:02"));
        jobStrawberryCake.setResultValue((double) 1);
        jobStrawberryCake.setDetails("Everything gone ok.");
        jobChocolateStrawberryCake.setStartDate(ParseTools.parseDate("2018-04-07 11:30:02"));
        jobChocolateStrawberryCake.setEndDate(ParseTools.parseDate("2018-04-07 13:30:02"));
        jobChocolateStrawberryCake.setResultValue((double) 1);
        jobChocolateStrawberryCake.setDetails("Everything gone ok.");
        jobDonut1.setStartDate(ParseTools.parseDate("2018-04-07 13:30:02"));
        jobDonut1.setEndDate(ParseTools.parseDate("2018-04-07 17:30:02"));
        jobDonut1.setResultValue((double) 21);
        jobDonut1.setDetails("One donut additional.");
        jobDonut2.setStartDate(ParseTools.parseDate("2018-04-08 12:00:00"));
        jobDonut2.setEndDate(ParseTools.parseDate("2018-04-08 16:00:00"));
        jobDonut2.setResultValue((double) 19);
        jobDonut2.setDetails("One donut less than usual.");
    }

    /**
     * Sets formulas.
     */
    public void setFormulas() {
        formulaFlourCherry = new FormulaPOJO();
        formulaB = new FormulaPOJO();
        formulaC = new FormulaPOJO();
        formulaD = new FormulaPOJO();
        formulaFlourStrawberry = new FormulaPOJO();
        formulaF = new FormulaPOJO();
        formulaG = new FormulaPOJO();
        formulaH = new FormulaPOJO();
        formulaFlourChocolateStrawberryCake = new FormulaPOJO();
        formulaJ = new FormulaPOJO();
        formulaK = new FormulaPOJO();
        formulaL = new FormulaPOJO();
        formulaFlourDonut = new FormulaPOJO();
        formulaN = new FormulaPOJO();
        formulaO = new FormulaPOJO();
        formulaP = new FormulaPOJO();
        formulaQ = new FormulaPOJO();
        formulaR = new FormulaPOJO();
        formulaS = new FormulaPOJO();
        formulaT = new FormulaPOJO();
        formulaU = new FormulaPOJO();
        formulaFlourCherry.setValue((double) 1);
        formulaB.setValue((double) 1);
        formulaC.setValue((double) 5);
        formulaD.setValue(0.1);
        formulaFlourStrawberry.setValue((double) 1);
        formulaF.setValue((double) 1);
        formulaG.setValue((double) 5);
        formulaH.setValue(0.1);
        formulaFlourChocolateStrawberryCake.setValue((double) 1);
        formulaJ.setValue((double) 1);
        formulaK.setValue((double) 5);
        formulaL.setValue(0.1);
        formulaFlourDonut.setValue((double) 1);
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
        formulaFlourCherry = formulaService.save(formulaFlourCherry);
        formulaB = formulaService.save(formulaB);
        formulaC = formulaService.save(formulaC);
        formulaD = formulaService.save(formulaD);
        formulaFlourStrawberry = formulaService.save(formulaFlourStrawberry);
        formulaF = formulaService.save(formulaF);
        formulaG = formulaService.save(formulaG);
        formulaH = formulaService.save(formulaH);
        formulaFlourChocolateStrawberryCake = formulaService.save(formulaFlourChocolateStrawberryCake);
        formulaJ = formulaService.save(formulaJ);
        formulaK = formulaService.save(formulaK);
        formulaL = formulaService.save(formulaL);
        formulaFlourDonut = formulaService.save(formulaFlourDonut);
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
        productCherryCake = productService.save(productCherryCake);
        productStrawberryCake = productService.save(productStrawberryCake);
        productChocolateStrawberryCake = productService.save(productChocolateStrawberryCake);
        productDonutWithChocolate = productService.save(productDonutWithChocolate);
    }

    /**
     * Save resources.
     */
    public void saveResources() {
        resourceFlour = resourceService.save(resourceFlour);
        resourceMilk = resourceService.save(resourceMilk);
        resourceEgg = resourceService.save(resourceEgg);
        resourceSugar = resourceService.save(resourceSugar);
        resourceStrawberry = resourceService.save(resourceStrawberry);
        resourceCherry = resourceService.save(resourceCherry);
        resourceChocolate = resourceService.save(resourceChocolate);
    }

    /**
     * Save roles.
     */
    public void saveRoles() {
        roleAdmin = roleService.save(roleAdmin);
        roleManager = roleService.save(roleManager);
        roleStaff = roleService.save(roleStaff);
    }

    /**
     * Save suppliers.
     */
    public void saveSuppliers() {
        fruitSupplier = supplierService.save(fruitSupplier);
        basicSupplier = supplierService.save(basicSupplier);
    }

    /**
     * Save jobs.
     */
    public void saveJobs() {
        jobCherryCake = jobService.save(jobCherryCake);
        jobStrawberryCake = jobService.save(jobStrawberryCake);
        jobChocolateStrawberryCake = jobService.save(jobChocolateStrawberryCake);
        jobDonut1 = jobService.save(jobDonut1);
        jobDonut2 = jobService.save(jobDonut2);
    }

    /**
     * Save users.
     */
    public void saveUsers() {
        userAdmin = userService.save(userAdmin);
        userManager = userService.save(userManager);
        userStaff = userService.save(userStaff);
    }

    private void setResourceCategories() {
        resourceCategoryBasics = new ResourceCategoryPOJO();
        resourceCategoryFruits = new ResourceCategoryPOJO();
        resourceCategoryBasics.setName("Basics");
        resourceCategoryFruits.setName("Fruits");
    }

    private void setProductCategories() {
        productCategoryCakes = new ProductCategoryPOJO();
        productCategoryOthers = new ProductCategoryPOJO();
        productCategoryCakes.setName("Cakes");
        productCategoryOthers.setName("Others");
    }

    private void productCategoriesRelations() {
        List<ProductPOJO> cakesList = new ArrayList<>();
        List<ProductPOJO> othersList = new ArrayList<>();
        cakesList.add(productCherryCake);
        cakesList.add(productStrawberryCake);
        cakesList.add(productChocolateStrawberryCake);
        othersList.add(productDonutWithChocolate);
        productCategoryCakes.setPOJOProducts(cakesList);
        productCategoryOthers.setPOJOProducts(othersList);
    }

    private void resourceCategoriesRelations() {
        List<ResourcePOJO> basicsList = new ArrayList<>();
        List<ResourcePOJO> fruitList = new ArrayList<>();
        basicsList.add(resourceFlour);
        basicsList.add(resourceMilk);
        basicsList.add(resourceEgg);
        basicsList.add(resourceSugar);
        basicsList.add(resourceChocolate);
        fruitList.add(resourceStrawberry);
        fruitList.add(resourceCherry);
        resourceCategoryBasics.setPOJOResources(basicsList);
        resourceCategoryFruits.setPOJOResources(fruitList);
    }

    private void suppliersRelations() {
        List<ResourcePOJO> fruitList = new ArrayList<>();
        List<ResourcePOJO> basicList = new ArrayList<>();
        basicList.add(resourceFlour);
        basicList.add(resourceMilk);
        basicList.add(resourceSugar);
        basicList.add(resourceChocolate);
        basicList.add(resourceEgg);
        fruitList.add(resourceStrawberry);
        fruitList.add(resourceCherry);
        fruitSupplier.setPOJOResources(fruitList);
        basicSupplier.setPOJOResources(basicList);
    }

    private void usersRelations() {
        List<JobPOJO> staffJobList = new ArrayList<>();
        List<RolePOJO> roleListAdmin = new ArrayList<>();
        List<RolePOJO> roleListManager = new ArrayList<>();
        List<RolePOJO> roleListStaff = new ArrayList<>();
        staffJobList.add(jobCherryCake);
        staffJobList.add(jobStrawberryCake);
        staffJobList.add(jobChocolateStrawberryCake);
        staffJobList.add(jobDonut1);
        staffJobList.add(jobDonut2);
        roleListAdmin.add(roleAdmin);
        roleListManager.add(roleManager);
        roleListStaff.add(roleStaff);
        userStaff.setPOJOJobs(staffJobList);
        userStaff.setPOJORoles(roleListStaff);
        userManager.setPOJORoles(roleListManager);
        userAdmin.setPOJORoles(roleListAdmin);
    }

    private void rolesRelations() {
        List<UserPOJO> userListStaff = new ArrayList<>();
        List<UserPOJO> userListManager = new ArrayList<>();
        List<UserPOJO> userListAadmin = new ArrayList<>();
        userListAadmin.add(userAdmin);
        userListManager.add(userManager);
        userListStaff.add(userStaff);
        roleAdmin.setPOJOUsers(userListAadmin);
        roleManager.setPOJOUsers(userListManager);
        roleStaff.setPOJOUsers(userListStaff);
    }

    private void resourcesRelations() {
        List<FormulaPOJO> formulaListFlour = new ArrayList<>();
        List<FormulaPOJO> formulaListB = new ArrayList<>();
        List<FormulaPOJO> formulaListC = new ArrayList<>();
        List<FormulaPOJO> formulaListD = new ArrayList<>();
        List<FormulaPOJO> formulaListE = new ArrayList<>();
        List<FormulaPOJO> formulaListF = new ArrayList<>();
        List<FormulaPOJO> formulaListG = new ArrayList<>();
        formulaListFlour.add(formulaFlourCherry);
        formulaListFlour.add(formulaFlourStrawberry);
        formulaListFlour.add(formulaFlourChocolateStrawberryCake);
        formulaListFlour.add(formulaFlourDonut);
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
        resourceChocolate.setPOJOCategory(resourceCategoryBasics);
        resourceCherry.setPOJOCategory(resourceCategoryFruits);
        resourceStrawberry.setPOJOCategory(resourceCategoryFruits);
        resourceMilk.setPOJOCategory(resourceCategoryBasics);
        resourceFlour.setPOJOCategory(resourceCategoryBasics);
        resourceEgg.setPOJOCategory(resourceCategoryBasics);
        resourceSugar.setPOJOCategory(resourceCategoryBasics);
        resourceFlour.setPOJOFormulas(formulaListFlour);
        resourceMilk.setPOJOFormulas(formulaListB);
        resourceEgg.setPOJOFormulas(formulaListC);
        resourceSugar.setPOJOFormulas(formulaListD);
        resourceStrawberry.setPOJOFormulas(formulaListE);
        resourceCherry.setPOJOFormulas(formulaListF);
        resourceChocolate.setPOJOFormulas(formulaListG);
        resourceFlour.setPOJOSupplier(fruitSupplier);
        resourceMilk.setPOJOSupplier(fruitSupplier);
        resourceSugar.setPOJOSupplier(fruitSupplier);
        resourceStrawberry.setPOJOSupplier(fruitSupplier);
        resourceCherry.setPOJOSupplier(fruitSupplier);
        resourceChocolate.setPOJOSupplier(fruitSupplier);
        resourceEgg.setPOJOSupplier(basicSupplier);
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
        formulaListA.add(formulaFlourCherry);
        formulaListA.add(formulaB);
        formulaListA.add(formulaC);
        formulaListA.add(formulaD);
        formulaListB.add(formulaFlourStrawberry);
        formulaListB.add(formulaF);
        formulaListB.add(formulaG);
        formulaListB.add(formulaH);
        formulaListC.add(formulaFlourChocolateStrawberryCake);
        formulaListC.add(formulaJ);
        formulaListC.add(formulaK);
        formulaListC.add(formulaL);
        formulaListD.add(formulaFlourDonut);
        formulaListD.add(formulaN);
        formulaListD.add(formulaO);
        formulaListD.add(formulaP);
        formulaListA.add(formulaQ);
        formulaListB.add(formulaR);
        formulaListC.add(formulaS);
        formulaListC.add(formulaT);
        formulaListD.add(formulaU);
        jobListA.add(jobCherryCake);
        jobListB.add(jobStrawberryCake);
        jobListC.add(jobChocolateStrawberryCake);
        jobListD.add(jobDonut1);
        jobListD.add(jobDonut2);
        productDonutWithChocolate.setPOJOCategory(productCategoryOthers);
        productChocolateStrawberryCake.setPOJOCategory(productCategoryCakes);
        productStrawberryCake.setPOJOCategory(productCategoryCakes);
        productCherryCake.setPOJOCategory(productCategoryCakes);
        productCherryCake.setPOJOFormulas(formulaListA);
        productCherryCake.setPOJOJobs(jobListA);
        productStrawberryCake.setPOJOFormulas(formulaListB);
        productStrawberryCake.setPOJOJobs(jobListB);
        productChocolateStrawberryCake.setPOJOFormulas(formulaListC);
        productChocolateStrawberryCake.setPOJOJobs(jobListC);
        productDonutWithChocolate.setPOJOFormulas(formulaListD);
        productDonutWithChocolate.setPOJOJobs(jobListD);
    }

    private void jobsRelations() {
        jobCherryCake.setPOJOUser(userStaff);
        jobCherryCake.setPOJOProduct(productCherryCake);
        jobStrawberryCake.setPOJOUser(userStaff);
        jobStrawberryCake.setPOJOProduct(productStrawberryCake);
        jobChocolateStrawberryCake.setPOJOUser(userStaff);
        jobChocolateStrawberryCake.setPOJOProduct(productChocolateStrawberryCake);
        jobDonut1.setPOJOUser(userStaff);
        jobDonut1.setPOJOProduct(productDonutWithChocolate);
        jobDonut2.setPOJOUser(userStaff);
        jobDonut2.setPOJOProduct(productDonutWithChocolate);
    }

    private void formulasRelations() {
        formulaFlourCherry.setPOJOProduct(productCherryCake);
        formulaB.setPOJOProduct(productCherryCake);
        formulaC.setPOJOProduct(productCherryCake);
        formulaD.setPOJOProduct(productCherryCake);
        formulaFlourStrawberry.setPOJOProduct(productStrawberryCake);
        formulaF.setPOJOProduct(productStrawberryCake);
        formulaG.setPOJOProduct(productStrawberryCake);
        formulaH.setPOJOProduct(productStrawberryCake);
        formulaFlourChocolateStrawberryCake.setPOJOProduct(productChocolateStrawberryCake);
        formulaJ.setPOJOProduct(productChocolateStrawberryCake);
        formulaK.setPOJOProduct(productChocolateStrawberryCake);
        formulaL.setPOJOProduct(productChocolateStrawberryCake);
        formulaFlourDonut.setPOJOProduct(productDonutWithChocolate);
        formulaN.setPOJOProduct(productDonutWithChocolate);
        formulaO.setPOJOProduct(productDonutWithChocolate);
        formulaP.setPOJOProduct(productDonutWithChocolate);
        formulaQ.setPOJOProduct(productCherryCake);
        formulaR.setPOJOProduct(productStrawberryCake);
        formulaS.setPOJOProduct(productChocolateStrawberryCake);
        formulaT.setPOJOProduct(productChocolateStrawberryCake);
        formulaU.setPOJOProduct(productDonutWithChocolate);
        formulaFlourCherry.setPOJOResource(resourceFlour);
        formulaFlourStrawberry.setPOJOResource(resourceFlour);
        formulaFlourChocolateStrawberryCake.setPOJOResource(resourceFlour);
        formulaFlourDonut.setPOJOResource(resourceFlour);
        formulaB.setPOJOResource(resourceMilk);
        formulaF.setPOJOResource(resourceMilk);
        formulaJ.setPOJOResource(resourceMilk);
        formulaN.setPOJOResource(resourceMilk);
        formulaC.setPOJOResource(resourceEgg);
        formulaG.setPOJOResource(resourceEgg);
        formulaK.setPOJOResource(resourceEgg);
        formulaO.setPOJOResource(resourceEgg);
        formulaD.setPOJOResource(resourceSugar);
        formulaH.setPOJOResource(resourceSugar);
        formulaL.setPOJOResource(resourceSugar);
        formulaP.setPOJOResource(resourceSugar);
        formulaQ.setPOJOResource(resourceCherry);
        formulaR.setPOJOResource(resourceStrawberry);
        formulaS.setPOJOResource(resourceStrawberry);
        formulaT.setPOJOResource(resourceChocolate);
        formulaU.setPOJOResource(resourceChocolate);
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
        resourceCategoryBasics = resourceCategoryService.save(resourceCategoryBasics);
        resourceCategoryFruits = resourceCategoryService.save(resourceCategoryFruits);
    }

    private void saveProductCategories() {
        productCategoryCakes = productCategoryService.save(productCategoryCakes);
        productCategoryOthers = productCategoryService.save(productCategoryOthers);
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
    private ResourceCategoryPOJO resourceCategoryBasics;
    private ResourceCategoryPOJO resourceCategoryFruits;
    private ProductCategoryPOJO productCategoryCakes;
    private ProductCategoryPOJO productCategoryOthers;
    private SupplierPOJO fruitSupplier;
    private SupplierPOJO basicSupplier;
    private UserPOJO userAdmin;
    private UserPOJO userManager;
    private UserPOJO userStaff;
    private RolePOJO roleAdmin;
    private RolePOJO roleManager;
    private RolePOJO roleStaff;
    private ResourcePOJO resourceFlour;
    private ResourcePOJO resourceMilk;
    private ResourcePOJO resourceEgg;
    private ResourcePOJO resourceSugar;
    private ResourcePOJO resourceStrawberry;
    private ResourcePOJO resourceCherry;
    private ResourcePOJO resourceChocolate;
    private ProductPOJO productCherryCake;
    private ProductPOJO productStrawberryCake;
    private ProductPOJO productChocolateStrawberryCake;
    private ProductPOJO productDonutWithChocolate;
    private JobPOJO jobCherryCake;
    private JobPOJO jobStrawberryCake;
    private JobPOJO jobChocolateStrawberryCake;
    private JobPOJO jobDonut1;
    private JobPOJO jobDonut2;
    private FormulaPOJO formulaFlourCherry;
    private FormulaPOJO formulaB;
    private FormulaPOJO formulaC;
    private FormulaPOJO formulaD;
    private FormulaPOJO formulaFlourStrawberry;
    private FormulaPOJO formulaF;
    private FormulaPOJO formulaG;
    private FormulaPOJO formulaH;
    private FormulaPOJO formulaFlourChocolateStrawberryCake;
    private FormulaPOJO formulaJ;
    private FormulaPOJO formulaK;
    private FormulaPOJO formulaL;
    private FormulaPOJO formulaFlourDonut;
    private FormulaPOJO formulaN;
    private FormulaPOJO formulaO;
    private FormulaPOJO formulaP;
    private FormulaPOJO formulaQ;
    private FormulaPOJO formulaR;
    private FormulaPOJO formulaS;
    private FormulaPOJO formulaT;
    private FormulaPOJO formulaU;
}
