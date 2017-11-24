package com.modern.codes.lime;

import com.modern.codes.lime.exception.IllegalDataException;
import com.modern.codes.lime.model.*;
import com.modern.codes.lime.pojo.*;

import java.util.List;
import java.util.stream.Collectors;

public class ParseTools {
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

    public static Object parse(Object obj){
        ClassName cl = ClassName.valueOf(obj.getClass().getSimpleName());
        switch(cl) {
            case FormulaPOJO:
                return parse((FormulaPOJO) obj);
            case Formula:
                return parse((Formula) obj);
            case JobPOJO:
                return parse((JobPOJO) obj);
            case Job:
                return parse((Job) obj);
            case PrivilegePOJO:
                return parse((PrivilegePOJO) obj);
            case Privilege:
                return parse((Privilege) obj);
            case ProductPOJO:
                return parse((ProductPOJO) obj);
            case Product:
                return parse((Product) obj);
            case ResourcePOJO:
                return parse((ResourcePOJO) obj);
            case Resource:
                return parse((Resource) obj);
            case RolePOJO:
                return parse((RolePOJO) obj);
            case Role:
                return parse((Role) obj);
            case SupplierPOJO:
                return parse((SupplierPOJO) obj);
            case Supplier:
                return parse((Supplier) obj);
            case UserPOJO:
                return parse((UserPOJO) obj);
            case User :
                return parse((User) obj);
            default:
                throw new IllegalDataException("Illegal class to parse. Trying to parse: " + cl.toString());
        }
    }

    public static<T> T parse (Object obj, Class<T> cl){
        return convertInstanceOfObject(parse(obj), cl);

    }
    public static UserPOJO parse(User user) {
        if(null == user)
            return null;
        UserPOJO usr = new UserPOJO();
        usr.setId(user.getId());
        usr.setName(user.getName());
        usr.setSurname(user.getSurname());
        usr.setDBJobs(user.getJobs());
        usr.setJoinedAt(user.getJoinedAt());
        usr.setLogin(user.getLogin());
        usr.setPassword(user.getPassword());
        usr.setDBRoles(user.getRoles());
        return usr;
    }
    public static User parse(UserPOJO user) {
        if(null == user)
            return null;
        User usr = new User();
        usr.setId(user.getId());
        usr.setName(user.getName());
        usr.setSurname(user.getSurname());
        usr.setJobs(user.getDBJobs());
        usr.setJoinedAt(user.getJoinedAt());
        usr.setLogin(user.getLogin());
        usr.setPassword(user.getPassword());
        usr.setRoles(user.getDBRoles());
        return usr;
    }
    public static FormulaPOJO parse(Formula formula){
        if(null == formula)
            return null;
        FormulaPOJO form = new FormulaPOJO();
        form.setId(formula.getId());
        form.setValue(formula.getValue());
        form.setDBProducts(formula.getProducts());
        form.setDBResource(formula.getResources());
        return form;
    }
    public static Formula parse(FormulaPOJO formula){
        if(null == formula)
            return null;
        Formula form = new Formula();
        form.setId(formula.getId());
        form.setValue(formula.getValue());
        form.setProducts(formula.getDBProducts());
        form.setResources(formula.getDBResource());
        return form;
    }
    public static JobPOJO parse(Job job){
        if(null == job)
            return null;
        JobPOJO jb = new JobPOJO();
        jb.setDetails(job.getDetails());
        jb.setEndDate(job.getEndDate());
        jb.setId(job.getId());
        jb.setDBProduct(job.getProducts());
        jb.setResultValue(job.getResultValue());
        jb.setStartDate(job.getStartDate());
        jb.setDBUser(job.getUser());
        jb.setDetails(job.getDetails());
        return jb;
    }
    public static Job parse(JobPOJO job){
        if(null == job)
            return null;
        Job jb = new Job();
        jb.setDetails(job.getDetails());
        jb.setEndDate(job.getEndDate());
        jb.setId(job.getId());
        jb.setProducts(job.getDBProduct());
        jb.setResultValue(job.getResultValue());
        jb.setStartDate(job.getStartDate());
        jb.setUser(job.getDBUser());
        jb.setDetails(job.getDetails());
        return jb;
    }
    public static ProductPOJO parse(Product product){
        if(null == product)
            return null;
        ProductPOJO prdct = new ProductPOJO();
        prdct.setCategory(product.getCategory());
        prdct.setCreatedAt(product.getCreatedAt());
        prdct.setDescription(product.getDescription());
        prdct.setExpectedValue(product.getExpectedValue());
        prdct.setDBFormula(product.getFormula());
        prdct.setId(product.getId());
        prdct.setImage(product.getImage());
        prdct.setDBJobs(product.getJob());
        prdct.setName(product.getName());
        prdct.setUnit(product.getUnit());
        return prdct;
    }
    public static Product parse(ProductPOJO product){
        if(null == product)
            return null;
        Product prdct = new Product();
        prdct.setCategory(product.getCategory());
        prdct.setCreatedAt(product.getCreatedAt());
        prdct.setDescription(product.getDescription());
        prdct.setExpectedValue(product.getExpectedValue());
        prdct.setFormula(product.getDBFormula());
        prdct.setId(product.getId());
        prdct.setImage(product.getImage());
        prdct.setJob(product.getDBJobs());
        prdct.setName(product.getName());
        prdct.setUnit(product.getUnit());
        return prdct;
    }
    public static ResourcePOJO parse(Resource resource){
        if(null == resource)
            return null;
        ResourcePOJO res = new ResourcePOJO();
        res.setCategory(resource.getCategory());
        res.setDescription(resource.getDescription());
        res.setDBFormula(resource.getFormula());
        res.setId(resource.getId());
        res.setImage(resource.getImage());
        res.setName(resource.getName());
        res.setQuantity(resource.getQuantity());
        res.setDBSupplier(resource.getSupplier());
        res.setUnit(resource.getUnit());
        return res;
    }
    public static Resource parse(ResourcePOJO resource){
        if(null == resource)
            return null;
        Resource res = new Resource();
        res.setCategory(resource.getCategory());
        res.setDescription(resource.getDescription());
        res.setFormula(resource.getDBFormula());
        res.setId(resource.getId());
        res.setImage(resource.getImage());
        res.setName(resource.getName());
        res.setQuantity(resource.getQuantity());
        res.setSupplier(resource.getDBSupplier());
        res.setUnit(resource.getUnit());
        return res;
    }
    public static SupplierPOJO parse(Supplier supplier){
        if(null == supplier)
            return null;
        SupplierPOJO sup = new SupplierPOJO();
        sup.setCity(supplier.getCity());
        sup.setEmailAddress(supplier.getEmailAddress());
        sup.setCountry(supplier.getCountry());
        sup.setId(supplier.getId());
        sup.setName(supplier.getName());
        sup.setPostalCode(supplier.getPostalCode());
        sup.setDBResources(supplier.getResources());
        sup.setStreet(supplier.getStreet());
        sup.setTelephone(supplier.getTelephone());
        return sup;
    }
    public static Supplier parse(SupplierPOJO supplier){
        if(null == supplier)
            return null;
        Supplier sup = new Supplier();
        sup.setCity(supplier.getCity());
        sup.setEmailAddress(supplier.getEmailAddress());
        sup.setCountry(supplier.getCountry());
        sup.setId(supplier.getId());
        sup.setName(supplier.getName());
        sup.setPostalCode(supplier.getPostalCode());
        sup.setResources(supplier.getDBResources());
        sup.setStreet(supplier.getStreet());
        sup.setTelephone(supplier.getTelephone());
        return sup;
    }
    public static RolePOJO parse(Role role){
        if(null == role)
            return null;
        RolePOJO rl = new RolePOJO();
        rl.setId(role.getId());
        rl.setDBPrivileges(role.getPrivileges());
        rl.setName(role.getName());
        rl.setDBUsers(role.getUsers());
        return rl;
    }
    public static Role parse(RolePOJO role){
        if(null == role)
            return null;
        Role rl = new Role();
        rl.setId(role.getId());
        rl.setPrivileges(role.getDBPrivileges());
        rl.setName(role.getName());
        rl.setUsers(role.getDBUsers());
        return rl;
    }
    public static PrivilegePOJO parse(Privilege privilege){
        if(null == privilege)
            return null;
        PrivilegePOJO pv = new PrivilegePOJO();
        pv.setId(privilege.getId());
        pv.setName(privilege.getName());
        pv.setDBRoles(privilege.getRoles());
        return pv;
    }
    public static Privilege parse(PrivilegePOJO privilege){
        if(null == privilege)
            return null;
        Privilege pv = new Privilege();
        pv.setId(privilege.getId());
        pv.setName(privilege.getName());
        pv.setRoles(privilege.getDBRoles());
        return pv;
    }

    public static List<?> parseList(List<?> list) {
        if(list == null)
            return null;
        return list.stream().map(ParseTools::parse).collect(Collectors.toList());
    }
    public static <T> List<T>  parseList(List<?> list, Class<T> cl){
        if(list == null)
            return null;
        return list.stream().map(t-> ParseTools.parse(t, cl)).collect(Collectors.toList());
    }
    public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch(ClassCastException e) {
            return null;
        }
    }
}
