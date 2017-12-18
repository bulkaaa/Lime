package com.modern.codes.lime.service;

import com.modern.codes.lime.model.ProductCategory;
import com.modern.codes.lime.pojo.ProductPOJO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IProductService {
    List<ProductPOJO> findAll();
    void delete(String id);
    ProductPOJO save(Object t);
    boolean exists(String id);
    boolean exists(Object t);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    ProductPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);

    List<ProductPOJO> findByName(String name);
    List<ProductPOJO> findByAddedAtBetween(Date begin, Date end);
    List<ProductPOJO> findByCategoryName(String categoryName);

    static List<ProductPOJO> filterByName(List<ProductPOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<ProductPOJO> filterByName(Stream<ProductPOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
    static List<ProductPOJO> filterByCategory(List<ProductPOJO> list, String category){
        return filterByCategory(list.stream(), category).collect(Collectors.toList());
    }
    static Stream<ProductPOJO> filterByCategory(Stream<ProductPOJO> stream, String category){
        return stream.filter(t -> t.getCategory().equals(category));
    }
}
