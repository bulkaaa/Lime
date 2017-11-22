package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IBasicCRUDRepository;
import com.modern.codes.lime.exception.NotFoundException;
import com.modern.codes.lime.pojo.BasicPOJO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class IBasicCRUDService <T, T_POJO,  T_DAO extends IBasicCRUDRepository<T, String>> {
//    @Autowired
//    private T_DAO dao;
//
//    public List<T_POJO> findAll(){
//        Optional<List<T>> t = Optional.ofNullable(dao.findAll());
//        if(!t.isPresent())
//            throw new NotFoundException("T object could not be found in DB");
//        return t.get();
//    }
//
//    public T delete(String id){
//        Optional<T> t = Optional.ofNullable(dao.findOne(id));
//        if(!t.isPresent())
//            throw new NotFoundException("T object could not be found in DB");
//        dao.delete(id);
//        return t.get();
//    }
//    public synchronized boolean add(T t){
//        if (dao.exists(((BasicPOJO)t).getId())) {
//            return false;
//        } else {
//            articleDAO.addArticle(article);
//            return true;
//        }
//    }

}
