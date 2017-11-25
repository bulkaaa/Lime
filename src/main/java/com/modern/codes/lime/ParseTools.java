package com.modern.codes.lime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modern.codes.lime.exception.IllegalDataException;
import com.modern.codes.lime.model.*;
import com.modern.codes.lime.pojo.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Service
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
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        try {
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
        }catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static User parse(UserPOJO user) {
        if(null == user)
            return null;
        try {
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
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static FormulaPOJO parse(Formula formula){
        if(null == formula)
            return null;
        try {
            FormulaPOJO form = new FormulaPOJO();
            form.setId(formula.getId());
            form.setValue(formula.getValue());
            form.setDBProduct(formula.getProducts());
            form.setDBResource(formula.getResources());
            return form;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static Formula parse(FormulaPOJO formula){
        if(null == formula)
            return null;
        try {
            Formula form = new Formula();
            form.setId(formula.getId());
            form.setValue(formula.getValue());
            form.setProducts(formula.getDBProduct());
            form.setResources(formula.getDBResource());
            return form;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static JobPOJO parse(Job job){
        if(null == job)
            return null;
        try {
            JobPOJO jb = new JobPOJO();
            jb.setDetails(job.getDetails());
            jb.setEndDate(job.getEndDate());
            jb.setId(job.getId());
            jb.setDBProduct(job.getProduct());
            jb.setResultValue(job.getResultValue());
            jb.setStartDate(job.getStartDate());
            jb.setDBUser(job.getUser());
            jb.setDetails(job.getDetails());
            return jb;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static Job parse(JobPOJO job){
        if(null == job)
            return null;
        try {
            Job jb = new Job();
            jb.setDetails(job.getDetails());
            jb.setEndDate(job.getEndDate());
            jb.setId(job.getId());
            jb.setProduct(job.getDBProduct());
            jb.setResultValue(job.getResultValue());
            jb.setStartDate(job.getStartDate());
            jb.setUser(job.getDBUser());
            jb.setDetails(job.getDetails());
            return jb;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static ProductPOJO parse(Product product){
        if(null == product)
            return null;
        try {
            ProductPOJO prdct = new ProductPOJO();
            prdct.setCategory(product.getCategory());
            prdct.setAddedAt(product.getAddedAt());
            prdct.setDescription(product.getDescription());
            prdct.setExpectedValue(product.getExpectedValue());
            prdct.setDBFormulas(product.getFormula());
            prdct.setId(product.getId());
            prdct.setImage(product.getImage());
            prdct.setDBJobs(product.getJob());
            prdct.setName(product.getName());
            prdct.setUnit(product.getUnit());
            return prdct;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static Product parse(ProductPOJO product){
        if(null == product)
            return null;
        try {
            Product prdct = new Product();
            prdct.setCategory(product.getCategory());
            prdct.setAddedAt(product.getAddedAt());
            prdct.setDescription(product.getDescription());
            prdct.setExpectedValue(product.getExpectedValue());
            prdct.setFormula(product.getDBFormulas());
            prdct.setId(product.getId());
            prdct.setImage(product.getImage());
            prdct.setJob(product.getDBJobs());
            prdct.setName(product.getName());
            prdct.setUnit(product.getUnit());
            return prdct;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static ResourcePOJO parse(Resource resource){
        if(null == resource)
            return null;
        try {
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
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static Resource parse(ResourcePOJO resource){
        if(null == resource)
            return null;
        try {
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
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static SupplierPOJO parse(Supplier supplier){
        if(null == supplier)
            return null;
        try {
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
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static Supplier parse(SupplierPOJO supplier){
        if(null == supplier)
            return null;
        try {
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
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static RolePOJO parse(Role role){
        if(null == role)
            return null;
        try {
            RolePOJO rl = new RolePOJO();
            rl.setId(role.getId());
            rl.setDBPrivileges(role.getPrivileges());
            rl.setName(role.getName());
            rl.setDBUsers(role.getUsers());
            return rl;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static Role parse(RolePOJO role){
        if(null == role)
            return null;
        try {
            Role rl = new Role();
            rl.setId(role.getId());
            rl.setPrivileges(role.getDBPrivileges());
            rl.setName(role.getName());
            rl.setUsers(role.getDBUsers());
            return rl;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static PrivilegePOJO parse(Privilege privilege){
        if(null == privilege)
            return null;
        try {
            PrivilegePOJO pv = new PrivilegePOJO();
            pv.setId(privilege.getId());
            pv.setName(privilege.getName());
            pv.setDBRoles(privilege.getRoles());
            return pv;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
    }
    public static Privilege parse(PrivilegePOJO privilege){
        if(null == privilege)
            return null;
        try {
            Privilege pv = new Privilege();
            pv.setId(privilege.getId());
            pv.setName(privilege.getName());
            pv.setRoles(privilege.getDBRoles());
            return pv;
        } catch (Exception e) {
            throw new IllegalDataException(e.getMessage());
        }
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
    public static <T> List<T> parseListObject(List<Object> o, Class<T> cl){
        return o.stream().map(t -> convertInstanceOfObject(t, cl)).collect(Collectors.toList());
    }
    // Pattern "yyyy-MM-dd HH:mm:ss"
    public static Date parseDate(String date){
        try {
            return dateFormat.parse(date);
        }
        catch(ParseException e){
            System.out.println("trying to parse wrong patern string date");
            throw new IllegalDataException("trying to parse wrong patern string date");
        }
    }
    public static String parseDate(Date date){
        return date.toString();
    }
    public static String parseToJson(Object obj){

        if(obj.getClass().toString().contains("POJO"))
            obj = parse(obj);
        if(obj instanceof List) {
            List<Object> list = (List<Object>)obj;
            if(!list.isEmpty() && list.get(0).getClass().toString().contains("POJO"))
            obj = parseList(list);
        }
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
