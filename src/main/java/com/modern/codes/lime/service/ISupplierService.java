package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.SupplierPOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface ISupplierService {
    List<SupplierPOJO> findAll();
    void delete(String id);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    SupplierPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
    static List<SupplierPOJO> filterByName(List<SupplierPOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<SupplierPOJO> filterByName(Stream<SupplierPOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
}
