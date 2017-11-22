//package com.modern.codes.lime.service;
//
//import com.modern.codes.lime.ParseTools;
//import com.modern.codes.lime.dao.IBasicCRUDRepository;
//import com.modern.codes.lime.exception.IllegalDataException;
//import com.modern.codes.lime.exception.NotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//import java.util.Optional;
//
//public class BasicCRUDService <T, T_POJO,  T_DAO extends IBasicCRUDRepository<T, String>> implements IBasicCRUDService{
//    @Autowired
//    private T_DAO dao;
//    private Class<T> Ttype;
//    private Class<T_POJO> T_POJOtype;
//
//    public BasicCRUDService(Class<T> Ttype, Class<T_POJO> T_POJOtype) {
//        this.Ttype = Ttype;
//        this.T_POJOtype = T_POJOtype;
//    }
//    @Override
//    public List<T_POJO> findAll(){
//        Optional<List<T>> t = Optional.ofNullable(dao.findAll());
//        if(!t.isPresent())
//            throw new NotFoundException(Ttype + " object could not be found in DB");
//        return ParseTools.parseList(t.get(), T_POJOtype);
//    }
//    @Override
//    public T_POJO delete(String id){
//        Optional<T> t = Optional.ofNullable(dao.findOne(id));
//        if(!t.isPresent())
//            throw new NotFoundException(Ttype + " object could not be found in DB");
//        dao.delete(id);
//        return ParseTools.parse(t.get(), T_POJOtype);
//    }
//
//    @Override
//    public void save(Object t) {
//        if (Object.class != T_POJOtype)
//            throw new IllegalDataException("Trying to save wrong type of object it's " + Object.class + " object, should be " + T_POJOtype);
//        dao.save(ParseTools.parse(t, Ttype));
//    }
//}
