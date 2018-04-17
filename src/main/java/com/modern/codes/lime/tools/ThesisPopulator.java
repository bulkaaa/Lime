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
     * Instantiates a new Thesis populator.
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
        formulaFlourCherrycake = new FormulaPOJO();
        formulaMilkCherryCake = new FormulaPOJO();
        formulaEggCherryCake = new FormulaPOJO();
        formulaSugarCherryCake = new FormulaPOJO();
        formulaFlourStrawberryCake = new FormulaPOJO();
        formulaMilkStrawberryCake = new FormulaPOJO();
        formulaEggStrawberryCake = new FormulaPOJO();
        formulaSugarStrawberryCake = new FormulaPOJO();
        formulaFlourChocolateStrawberryCake = new FormulaPOJO();
        formulaMilkChocolateStrawberryCake = new FormulaPOJO();
        formulaEggChocolateStrawberryCake = new FormulaPOJO();
        formulaSugarChocolateStrawberryCake = new FormulaPOJO();
        formulaFlourDonut = new FormulaPOJO();
        formulaMilkDonut = new FormulaPOJO();
        formulaEggDonut = new FormulaPOJO();
        formulaSugarDonut = new FormulaPOJO();
        formulaCherryCherryCake = new FormulaPOJO();
        formulaStrawberryStrawberryCake = new FormulaPOJO();
        formulaStrawberryChocolateStrawberryCake = new FormulaPOJO();
        formulaChocolateChocolateStrawberryCake = new FormulaPOJO();
        formulaChocolateDonuts = new FormulaPOJO();
        formulaFlourCherrycake.setValue(0.5);
        formulaMilkCherryCake.setValue(0.25);
        formulaEggCherryCake.setValue((double) 3);
        formulaSugarCherryCake.setValue(0.1);
        formulaFlourStrawberryCake.setValue(0.5);
        formulaMilkStrawberryCake.setValue(0.25);
        formulaEggStrawberryCake.setValue((double) 3);
        formulaSugarStrawberryCake.setValue(0.1);
        formulaFlourChocolateStrawberryCake.setValue(0.5);
        formulaMilkChocolateStrawberryCake.setValue(0.25);
        formulaEggChocolateStrawberryCake.setValue((double) 3);
        formulaSugarChocolateStrawberryCake.setValue(0.1);
        formulaFlourDonut.setValue((double) 1);
        formulaMilkDonut.setValue(0.5);
        formulaEggDonut.setValue((double) 4);
        formulaSugarDonut.setValue(0.1);
        formulaCherryCherryCake.setValue(0.1);
        formulaStrawberryStrawberryCake.setValue(0.1);
        formulaStrawberryChocolateStrawberryCake.setValue(0.1);
        formulaChocolateChocolateStrawberryCake.setValue((double) 2);
        formulaChocolateDonuts.setValue((double) 4);
    }

    /**
     * Save formulas.
     */
    public void saveFormulas() {
        formulaFlourCherrycake = formulaService.save(formulaFlourCherrycake);
        formulaMilkCherryCake = formulaService.save(formulaMilkCherryCake);
        formulaEggCherryCake = formulaService.save(formulaEggCherryCake);
        formulaSugarCherryCake = formulaService.save(formulaSugarCherryCake);
        formulaFlourStrawberryCake = formulaService.save(formulaFlourStrawberryCake);
        formulaMilkStrawberryCake = formulaService.save(formulaMilkStrawberryCake);
        formulaEggStrawberryCake = formulaService.save(formulaEggStrawberryCake);
        formulaSugarStrawberryCake = formulaService.save(formulaSugarStrawberryCake);
        formulaFlourChocolateStrawberryCake = formulaService.save(formulaFlourChocolateStrawberryCake);
        formulaMilkChocolateStrawberryCake = formulaService.save(formulaMilkChocolateStrawberryCake);
        formulaEggChocolateStrawberryCake = formulaService.save(formulaEggChocolateStrawberryCake);
        formulaSugarChocolateStrawberryCake = formulaService.save(formulaSugarChocolateStrawberryCake);
        formulaFlourDonut = formulaService.save(formulaFlourDonut);
        formulaMilkDonut = formulaService.save(formulaMilkDonut);
        formulaEggDonut = formulaService.save(formulaEggDonut);
        formulaSugarDonut = formulaService.save(formulaSugarDonut);
        formulaCherryCherryCake = formulaService.save(formulaCherryCherryCake);
        formulaStrawberryStrawberryCake = formulaService.save(formulaStrawberryStrawberryCake);
        formulaStrawberryChocolateStrawberryCake = formulaService.save(formulaStrawberryChocolateStrawberryCake);
        formulaChocolateChocolateStrawberryCake = formulaService.save(formulaChocolateChocolateStrawberryCake);
        formulaChocolateDonuts = formulaService.save(formulaChocolateDonuts);
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
        List<FormulaPOJO> formulaListMilk = new ArrayList<>();
        List<FormulaPOJO> formulaListEgg = new ArrayList<>();
        List<FormulaPOJO> formulaListSugar = new ArrayList<>();
        List<FormulaPOJO> formulaListStrawberry = new ArrayList<>();
        List<FormulaPOJO> formulaListCherry = new ArrayList<>();
        List<FormulaPOJO> formulaListChocolate = new ArrayList<>();
        formulaListFlour.add(formulaFlourCherrycake);
        formulaListFlour.add(formulaFlourStrawberryCake);
        formulaListFlour.add(formulaFlourChocolateStrawberryCake);
        formulaListFlour.add(formulaFlourDonut);
        formulaListMilk.add(formulaMilkCherryCake);
        formulaListMilk.add(formulaMilkStrawberryCake);
        formulaListMilk.add(formulaMilkChocolateStrawberryCake);
        formulaListMilk.add(formulaMilkDonut);
        formulaListEgg.add(formulaEggCherryCake);
        formulaListEgg.add(formulaEggStrawberryCake);
        formulaListEgg.add(formulaEggChocolateStrawberryCake);
        formulaListEgg.add(formulaEggDonut);
        formulaListSugar.add(formulaSugarCherryCake);
        formulaListSugar.add(formulaSugarStrawberryCake);
        formulaListSugar.add(formulaSugarChocolateStrawberryCake);
        formulaListSugar.add(formulaSugarDonut);
        formulaListCherry.add(formulaCherryCherryCake); // cherrycake
        formulaListStrawberry.add(formulaStrawberryStrawberryCake); // strwaberry  cake
        formulaListStrawberry.add(formulaStrawberryChocolateStrawberryCake); // Strawberry for choc cake
        formulaListChocolate.add(formulaChocolateChocolateStrawberryCake); // chocolate for cake
        formulaListChocolate.add(formulaChocolateDonuts); // chocolate for donuts
        resourceCherry.setPOJOCategory(resourceCategoryFruits);
        resourceStrawberry.setPOJOCategory(resourceCategoryFruits);
        resourceChocolate.setPOJOCategory(resourceCategoryBasics);
        resourceMilk.setPOJOCategory(resourceCategoryBasics);
        resourceFlour.setPOJOCategory(resourceCategoryBasics);
        resourceEgg.setPOJOCategory(resourceCategoryBasics);
        resourceSugar.setPOJOCategory(resourceCategoryBasics);
        resourceFlour.setPOJOFormulas(formulaListFlour);
        resourceMilk.setPOJOFormulas(formulaListMilk);
        resourceEgg.setPOJOFormulas(formulaListEgg);
        resourceSugar.setPOJOFormulas(formulaListSugar);
        resourceStrawberry.setPOJOFormulas(formulaListStrawberry);
        resourceCherry.setPOJOFormulas(formulaListCherry);
        resourceChocolate.setPOJOFormulas(formulaListChocolate);
        resourceFlour.setPOJOSupplier(basicSupplier);
        resourceMilk.setPOJOSupplier(basicSupplier);
        resourceSugar.setPOJOSupplier(basicSupplier);
        resourceStrawberry.setPOJOSupplier(fruitSupplier);
        resourceCherry.setPOJOSupplier(fruitSupplier);
        resourceChocolate.setPOJOSupplier(basicSupplier);
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
        formulaListA.add(formulaFlourCherrycake);
        formulaListA.add(formulaMilkCherryCake);
        formulaListA.add(formulaEggCherryCake);
        formulaListA.add(formulaSugarCherryCake);
        formulaListB.add(formulaFlourStrawberryCake);
        formulaListB.add(formulaMilkStrawberryCake);
        formulaListB.add(formulaEggStrawberryCake);
        formulaListB.add(formulaSugarStrawberryCake);
        formulaListC.add(formulaFlourChocolateStrawberryCake);
        formulaListC.add(formulaMilkChocolateStrawberryCake);
        formulaListC.add(formulaEggChocolateStrawberryCake);
        formulaListC.add(formulaSugarChocolateStrawberryCake);
        formulaListD.add(formulaFlourDonut);
        formulaListD.add(formulaMilkDonut);
        formulaListD.add(formulaEggDonut);
        formulaListD.add(formulaSugarDonut);
        formulaListA.add(formulaCherryCherryCake);
        formulaListB.add(formulaStrawberryStrawberryCake);
        formulaListC.add(formulaStrawberryChocolateStrawberryCake);
        formulaListC.add(formulaChocolateChocolateStrawberryCake);
        formulaListD.add(formulaChocolateDonuts);
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
        formulaFlourCherrycake.setPOJOProduct(productCherryCake);
        formulaMilkCherryCake.setPOJOProduct(productCherryCake);
        formulaEggCherryCake.setPOJOProduct(productCherryCake);
        formulaSugarCherryCake.setPOJOProduct(productCherryCake);
        formulaFlourStrawberryCake.setPOJOProduct(productStrawberryCake);
        formulaMilkStrawberryCake.setPOJOProduct(productStrawberryCake);
        formulaEggStrawberryCake.setPOJOProduct(productStrawberryCake);
        formulaSugarStrawberryCake.setPOJOProduct(productStrawberryCake);
        formulaFlourChocolateStrawberryCake.setPOJOProduct(productChocolateStrawberryCake);
        formulaMilkChocolateStrawberryCake.setPOJOProduct(productChocolateStrawberryCake);
        formulaEggChocolateStrawberryCake.setPOJOProduct(productChocolateStrawberryCake);
        formulaSugarChocolateStrawberryCake.setPOJOProduct(productChocolateStrawberryCake);
        formulaFlourDonut.setPOJOProduct(productDonutWithChocolate);
        formulaMilkDonut.setPOJOProduct(productDonutWithChocolate);
        formulaEggDonut.setPOJOProduct(productDonutWithChocolate);
        formulaSugarDonut.setPOJOProduct(productDonutWithChocolate);
        formulaCherryCherryCake.setPOJOProduct(productCherryCake);
        formulaStrawberryStrawberryCake.setPOJOProduct(productStrawberryCake);
        formulaStrawberryChocolateStrawberryCake.setPOJOProduct(productChocolateStrawberryCake);
        formulaChocolateChocolateStrawberryCake.setPOJOProduct(productChocolateStrawberryCake);
        formulaChocolateDonuts.setPOJOProduct(productDonutWithChocolate);
        formulaFlourCherrycake.setPOJOResource(resourceFlour);
        formulaFlourStrawberryCake.setPOJOResource(resourceFlour);
        formulaFlourChocolateStrawberryCake.setPOJOResource(resourceFlour);
        formulaFlourDonut.setPOJOResource(resourceFlour);
        formulaMilkCherryCake.setPOJOResource(resourceMilk);
        formulaMilkStrawberryCake.setPOJOResource(resourceMilk);
        formulaMilkChocolateStrawberryCake.setPOJOResource(resourceMilk);
        formulaMilkDonut.setPOJOResource(resourceMilk);
        formulaEggCherryCake.setPOJOResource(resourceEgg);
        formulaEggStrawberryCake.setPOJOResource(resourceEgg);
        formulaEggChocolateStrawberryCake.setPOJOResource(resourceEgg);
        formulaEggDonut.setPOJOResource(resourceEgg);
        formulaSugarCherryCake.setPOJOResource(resourceSugar);
        formulaSugarStrawberryCake.setPOJOResource(resourceSugar);
        formulaSugarChocolateStrawberryCake.setPOJOResource(resourceSugar);
        formulaSugarDonut.setPOJOResource(resourceSugar);
        formulaCherryCherryCake.setPOJOResource(resourceCherry);
        formulaStrawberryStrawberryCake.setPOJOResource(resourceStrawberry);
        formulaStrawberryChocolateStrawberryCake.setPOJOResource(resourceStrawberry);
        formulaChocolateChocolateStrawberryCake.setPOJOResource(resourceChocolate);
        formulaChocolateDonuts.setPOJOResource(resourceChocolate);
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
    private FormulaPOJO formulaFlourCherrycake;
    private FormulaPOJO formulaMilkCherryCake;
    private FormulaPOJO formulaEggCherryCake;
    private FormulaPOJO formulaSugarCherryCake;
    private FormulaPOJO formulaFlourStrawberryCake;
    private FormulaPOJO formulaMilkStrawberryCake;
    private FormulaPOJO formulaEggStrawberryCake;
    private FormulaPOJO formulaSugarStrawberryCake;
    private FormulaPOJO formulaFlourChocolateStrawberryCake;
    private FormulaPOJO formulaMilkChocolateStrawberryCake;
    private FormulaPOJO formulaEggChocolateStrawberryCake;
    private FormulaPOJO formulaSugarChocolateStrawberryCake;
    private FormulaPOJO formulaFlourDonut;
    private FormulaPOJO formulaMilkDonut;
    private FormulaPOJO formulaEggDonut;
    private FormulaPOJO formulaSugarDonut;
    private FormulaPOJO formulaCherryCherryCake;
    private FormulaPOJO formulaStrawberryStrawberryCake;
    private FormulaPOJO formulaStrawberryChocolateStrawberryCake;
    private FormulaPOJO formulaChocolateChocolateStrawberryCake;
    private FormulaPOJO formulaChocolateDonuts;
}
