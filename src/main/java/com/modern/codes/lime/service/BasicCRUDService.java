package com.modern.codes.lime.service;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.dao.IBasicCRUDRepository;
import com.modern.codes.lime.exception.IllegalDataException;
import com.modern.codes.lime.exception.NotFoundException;
import com.modern.codes.lime.pojo.BasicPOJO;
import org.springframework.transaction.TransactionSystemException;

import java.util.List;
import java.util.Optional;

public class BasicCRUDService <T, T_POJO,  T_DAO extends IBasicCRUDRepository<T, String>> implements IBasicCRUDService{
    private T_DAO dao;
    private Class<T> Ttype;
    private Class<T_POJO> T_POJOtype;

    BasicCRUDService(T_DAO dao, Class<T> Ttype, Class<T_POJO> T_POJOtype) {
        this.dao = dao;
        this.Ttype = Ttype;
        this.T_POJOtype = T_POJOtype;
    }
    @Override
    public List<T_POJO> findAll(){
        Optional<List<T>> t = Optional.ofNullable(dao.findAll());
        if(!t.isPresent())
            throw new NotFoundException(Ttype + " object could not be found in DB");
        return ParseTools.parseList(t.get(), T_POJOtype);
    }
    @Override
    public void delete(String id){
        if(!exists(id))
            throw new NotFoundException(Ttype + " object could not be found in DB");
        dao.delete(id);
    }

    @Override
    public T_POJO save(Object t) {
        try {
            return ParseTools.parse(dao.save(ParseTools.parse(t, Ttype)), T_POJOtype);
        } catch (TransactionSystemException rollbackException) {
            throw rollbackException;
        }
    }
    @Override
    public boolean exists(String id){
        return dao.exists(id);
    }

    @Override
    public boolean exists(Object t) {
        try{
            return exists(((BasicPOJO)t).getId());
        } catch (TransactionSystemException rollbackException) {
            throw rollbackException;
        }
    }

    @Override
    public boolean equals(Object t, Object y){
        try {
        return t == y;
        }catch (Exception e){
            throw new IllegalDataException("Trying to compare wrong type of objects it's " + t.getClass() + " and " + y.getClass() + " objects, should be " + T_POJOtype);
        }
    }
    @Override
    public void deleteAll(){
        dao.deleteAll();
    }
    @Override
    public T_POJO findById(String id){
        try{
        return ParseTools.parse(dao.findOne(id), T_POJOtype);
        } catch (Exception e){
            throw new NotFoundException(Ttype + " object could not be found in DB");
        }
    }

    @Override
    public void delete(Object t) {
        try {
            if(!exists(((BasicPOJO)t).getId()))
                throw new NotFoundException(Ttype + " object could not be found in DB");
            dao.delete(ParseTools.parse(t, Ttype));
        } catch (TransactionSystemException rollbackException) {
            throw rollbackException;
        }
    }

    @Override
    public void save(List l) {
        try {
            dao.save(ParseTools.parseList(l, Ttype));
        } catch (TransactionSystemException rollbackException) {
            throw rollbackException;
        }
    }
    @Override
    public void delete(List l) {
        try {
            dao.delete(ParseTools.parseList(l, Ttype));
        } catch (TransactionSystemException rollbackException) {
            throw rollbackException;
        }
    }

    @Override
    public long count() {
        return dao.count();
    }
}
