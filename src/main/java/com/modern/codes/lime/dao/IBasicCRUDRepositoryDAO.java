//package com.modern.codes.lime.dao;
//
//import com.modern.codes.lime.exception.NotFoundException;
//import com.modern.codes.lime.model.User;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public abstract class IBasicCRUDRepositoryDAO<T, ID extends Serializable> implements IBasicCRUDRepository<T, ID> {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public void getListById(){
//        String hql = "FROM User as usr WHERE usr.name = ? and usr.surname = ?";
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAASDSDASDASDASDASDSADASDASDASDSA  " + this.getClass());
//
////        Optional<List<T>> products = Optional.ofNullable(        entityManager.createQuery(hql).getResultList(););
////        if(!products.isPresent())
////            throw new NotFoundException("Product object could not be found in DB");
////        return products.get();
////        return new ArrayList<>();
//
////    }
////        if(TList.isEmpty())
////                throw new NotFoundException("Product object could not be found in DB");
////        return TList;
//    }
//}
