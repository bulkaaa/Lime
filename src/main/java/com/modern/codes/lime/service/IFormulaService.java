package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IFormulaService {
    List<FormulaPOJO> findAll();
    void delete(String id);
    FormulaPOJO save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    FormulaPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);

    static List<FormulaPOJO> filterByResource(List<FormulaPOJO> list, String resourceName){
        return filterByResource(list.stream(), resourceName).collect(Collectors.toList());
    }
    static Stream<FormulaPOJO> filterByResource(Stream<FormulaPOJO> stream, String resourceName) {
        return stream.filter(t -> t.getResource().getName().equals(resourceName));
    }
    static List<FormulaPOJO> filterByResource(List<FormulaPOJO> list, ResourcePOJO resource){
        return filterByResource(list.stream(), resource).collect(Collectors.toList());
    }
    static Stream<FormulaPOJO> filterByResource(Stream<FormulaPOJO> stream, ResourcePOJO resource){
        return stream.filter(t -> t.getResource().equals(resource));
    }
    static List<FormulaPOJO> filterByProduct(List<FormulaPOJO> list, String productName){
        return filterByProduct(list.stream(), productName).collect(Collectors.toList());
    }
    static Stream<FormulaPOJO> filterByProduct(Stream<FormulaPOJO> stream, String productName){
        return stream.filter(t -> t.getProduct().getName().equals(productName));
    }
    static List<FormulaPOJO> filterByProduct(List<FormulaPOJO> list, ProductPOJO product){
        return filterByProduct(list.stream(), product).collect(Collectors.toList());
    }
    static Stream<FormulaPOJO> filterByProduct(Stream<FormulaPOJO> stream, ProductPOJO product){
        return stream.filter(t -> t.getProduct().equals(product));
    }
}
