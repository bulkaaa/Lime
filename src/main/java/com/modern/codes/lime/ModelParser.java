package com.modern.codes.lime;

import com.modern.codes.lime.model.*;
import com.modern.codes.lime.pojo.*;

public class ModelParser {
    public UserPOJO userParse(User user) {
        UserPOJO usr = new UserPOJO();
        usr.setId(user.getId());
        usr.setName(user.getName());
        usr.setSurname(user.getSurname());
        usr.setJobs(user.getJobs());
        usr.setJoinedAt(user.getJoinedAt());
        usr.setLogin(user.getLogin());
        usr.setPassword(user.getPassword());
        usr.setRoles(user.getRoles());
        return usr;
    }
    public User userParse(UserPOJO user) {
        User usr = new User();
        usr.setId(user.getId());
        usr.setName(user.getName());
        usr.setSurname(user.getSurname());
        usr.setJobs(user.getJobs());
        usr.setJoinedAt(user.getJoinedAt());
        usr.setLogin(user.getLogin());
        usr.setPassword(user.getPassword());
        usr.setRoles(user.getRoles());
        return usr;
    }
    public FormulaPOJO formulaParse(Formula formula){
        FormulaPOJO form = new FormulaPOJO();
        form.setId(formula.getId());
        form.setValue(formula.getValue());
        form.setProducts(formula.getProducts());
        form.setResources(formula.getResources());
        return form;
    }
    public Formula formulaParse(FormulaPOJO formula){
        Formula form = new Formula();
        form.setId(formula.getId());
        form.setValue(formula.getValue());
        form.setProducts(formula.getProducts());
        form.setResources(formula.getResources());
        return form;
    }
    public JobPOJO jobParse(Job job){
        JobPOJO jb = new JobPOJO();
        jb.setDetails(job.getDetails());
        jb.setEndDate(job.getEndDate());
        jb.setId(job.getId());
        jb.setProducts(job.getProducts());
        jb.setResultvalue(job.getResultvalue());
        jb.setStartDate(job.getStartDate());
        jb.setUser(job.getUser());
        jb.setDetails(job.getDetails());
        return jb;
    }
    public Job jobParse(JobPOJO job){
        Job jb = new Job();
        jb.setDetails(job.getDetails());
        jb.setEndDate(job.getEndDate());
        jb.setId(job.getId());
        jb.setProducts(job.getProducts());
        jb.setResultvalue(job.getResultvalue());
        jb.setStartDate(job.getStartDate());
        jb.setUser(job.getUser());
        jb.setDetails(job.getDetails());
        return jb;
    }
    public ProductPOJO productParse(Product product){
        ProductPOJO prdct = new ProductPOJO();
        prdct.setCategory(product.getCategory());
        prdct.setCreatedAt(product.getCreatedAt());
        prdct.setDescription(product.getDescription());
        prdct.setExpectedValue(product.getExpectedValue());
        prdct.setFormula(product.getFormula());
        prdct.setId(product.getId());
        prdct.setImage(product.getImage());
        prdct.setJob(product.getJob());
        prdct.setName(product.getName());
        prdct.setUnit(product.getUnit());
        return prdct;
    }
    public Product productParse(ProductPOJO product){
        Product prdct = new Product();
        prdct.setCategory(product.getCategory());
        prdct.setCreatedAt(product.getCreatedAt());
        prdct.setDescription(product.getDescription());
        prdct.setExpectedValue(product.getExpectedValue());
        prdct.setFormula(product.getFormula());
        prdct.setId(product.getId());
        prdct.setImage(product.getImage());
        prdct.setJob(product.getJob());
        prdct.setName(product.getName());
        prdct.setUnit(product.getUnit());
        return prdct;
    }
    public ResourcePOJO resourceParse(Resource resource){
        ResourcePOJO res = new ResourcePOJO();
        res.setCategory(resource.getCategory());
        res.setDescription(resource.getDescription());
        res.setFormula(resource.getFormula());
        res.setId(resource.getId());
        res.setImage(resource.getImage());
        res.setName(resource.getName());
        res.setQuantity(resource.getQuantity());
        res.setSupplier(resource.getSupplier());
        res.setUnit(resource.getUnit());
        return res;
    }
    public Resource resourceParse(ResourcePOJO resource){
        Resource res = new Resource();
        res.setCategory(resource.getCategory());
        res.setDescription(resource.getDescription());
        res.setFormula(resource.getFormula());
        res.setId(resource.getId());
        res.setImage(resource.getImage());
        res.setName(resource.getName());
        res.setQuantity(resource.getQuantity());
        res.setSupplier(resource.getSupplier());
        res.setUnit(resource.getUnit());
        return res;
    }
    public SupplierPOJO supplierParse(Supplier supplier){
        SupplierPOJO sup = new SupplierPOJO();
        sup.setCity(supplier.getCity());
        sup.setEmailAddress(supplier.getEmailAddress());
        sup.setCountry(supplier.getCountry());
        sup.setId(supplier.getId());
        sup.setName(supplier.getName());
        sup.setPostalCode(supplier.getPostalCode());
        sup.setResources(supplier.getResources());
        sup.setStreet(supplier.getStreet());
        sup.setTelephone(supplier.getTelephone());
        return sup;
    }
    public Supplier supplierParse(SupplierPOJO supplier){
        Supplier sup = new Supplier();
        sup.setCity(supplier.getCity());
        sup.setEmailAddress(supplier.getEmailAddress());
        sup.setCountry(supplier.getCountry());
        sup.setId(supplier.getId());
        sup.setName(supplier.getName());
        sup.setPostalCode(supplier.getPostalCode());
        sup.setResources(supplier.getResources());
        sup.setStreet(supplier.getStreet());
        sup.setTelephone(supplier.getTelephone());
        return sup;
    }
    public RolePOJO roleParse(Role role){
        RolePOJO rl = new RolePOJO();
        rl.setId(role.getId());
        rl.setPrivileges(role.getPrivileges());
        rl.setName(role.getName());
        rl.setUsers(role.getUsers());
        return rl;
    }
    public Role roleParse(RolePOJO role){
        Role rl = new Role();
        rl.setId(role.getId());
        rl.setPrivileges(role.getPrivileges());
        rl.setName(role.getName());
        rl.setUsers(role.getUsers());
        return rl;
    }
    public PrivilegePOJO privilegeParse(Privilege privilege){
        PrivilegePOJO pv = new PrivilegePOJO();
        pv.setId(privilege.getId());
        pv.setName(privilege.getName());
        pv.setRoles(privilege.getRoles());
        return pv;
    }
    public Privilege privilegeParse(PrivilegePOJO privilege){
        Privilege pv = new Privilege();
        pv.setId(privilege.getId());
        pv.setName(privilege.getName());
        pv.setRoles(privilege.getRoles());
        return pv;
    }
}
