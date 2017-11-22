package com.modern.codes.lime;

import com.modern.codes.lime.exception.IllegalDataException;
import com.modern.codes.lime.model.*;
import com.modern.codes.lime.pojo.*;

import java.util.List;
import java.util.stream.Collectors;

public class ModelParser {
        enum ClassName {
            FormulaPOJO,
            Formula,
            JobPOJO,
            Job,
            PrivilegePOJO,
            Privilege,
            ProductPOJO,
            Product,
            ResourcePOJO,
            Resource,
            RolePOJO,
            Role,
            SupplierPOJO,
            Supplier,
            UserPOJO,
            User
        }
    public List<Object> parseList(List<Object> obj){
        if(obj.isEmpty())
            throw new IllegalDataException("Trying to parse empty list.");
        ClassName cl = ClassName.valueOf(obj.get(0).getClass().getSimpleName());
        switch(cl) {
            case FormulaPOJO:
                return obj.stream().map(t->formulaParse((FormulaPOJO)t)).collect(Collectors.toList());
            default:
                throw new IllegalDataException("Illegal class to parse. Trying to parse: " + cl.toString());
        }
    }
    public Object parse(Object obj){
        ClassName cl = ClassName.valueOf(obj.getClass().getSimpleName());
        switch(cl) {
            case FormulaPOJO:
                return formulaParse((FormulaPOJO) obj);
            case Formula:
                return formulaParse((Formula) obj);
            case JobPOJO:
                return jobParse((JobPOJO) obj);
            case Job:
                return jobParse((Job) obj);
            case PrivilegePOJO:
                return privilegeParse((PrivilegePOJO) obj);
            case Privilege:
                return privilegeParse((Privilege) obj);
            case ProductPOJO:
                return productParse((ProductPOJO) obj);
            case Product:
                return productParse((Product) obj);
            case ResourcePOJO:
                return resourceParse((ResourcePOJO) obj);
            case Resource:
                return resourceParse((Resource) obj);
            case RolePOJO:
                return roleParse((RolePOJO) obj);
            case Role:
                return roleParse((Role) obj);
            case SupplierPOJO:
                return supplierParse((SupplierPOJO) obj);
            case Supplier:
                return supplierParse((Supplier) obj);
            case UserPOJO:
                return userParse((UserPOJO) obj);
            case User :
                return userParse((User) obj);
            default:
                throw new IllegalDataException("Illegal class to parse. Trying to parse: " + cl.toString());
        }
    }
    private UserPOJO userParse(User user) {
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
    private User userParse(UserPOJO user) {
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
    private Formula formulaParse(FormulaPOJO formula){
        Formula form = new Formula();
        form.setId(formula.getId());
        form.setValue(formula.getValue());
        form.setProducts(formula.getProducts());
        form.setResources(formula.getResources());
        return form;
    }
    private JobPOJO jobParse(Job job){
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
    private Job jobParse(JobPOJO job){
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
    private ProductPOJO productParse(Product product){
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
    private Product productParse(ProductPOJO product){
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
    private ResourcePOJO resourceParse(Resource resource){
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
    private Resource resourceParse(ResourcePOJO resource){
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
    private SupplierPOJO supplierParse(Supplier supplier){
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
    private Supplier supplierParse(SupplierPOJO supplier){
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
    private RolePOJO roleParse(Role role){
        RolePOJO rl = new RolePOJO();
        rl.setId(role.getId());
        rl.setPrivileges(role.getPrivileges());
        rl.setName(role.getName());
        rl.setUsers(role.getUsers());
        return rl;
    }
    private Role roleParse(RolePOJO role){
        Role rl = new Role();
        rl.setId(role.getId());
        rl.setPrivileges(role.getPrivileges());
        rl.setName(role.getName());
        rl.setUsers(role.getUsers());
        return rl;
    }
    private PrivilegePOJO privilegeParse(Privilege privilege){
        PrivilegePOJO pv = new PrivilegePOJO();
        pv.setId(privilege.getId());
        pv.setName(privilege.getName());
        pv.setRoles(privilege.getRoles());
        return pv;
    }
    private Privilege privilegeParse(PrivilegePOJO privilege){
        Privilege pv = new Privilege();
        pv.setId(privilege.getId());
        pv.setName(privilege.getName());
        pv.setRoles(privilege.getRoles());
        return pv;
    }
}
