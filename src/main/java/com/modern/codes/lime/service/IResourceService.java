package com.modern.codes.lime.service;

import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.SupplierPOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IResourceService {
    List<ResourcePOJO> findAll();
    void delete(String id);
    ResourcePOJO save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    ResourcePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);

    static List<ResourcePOJO> filterByName(List<ResourcePOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterByName(Stream<ResourcePOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
    static List<ResourcePOJO> filterByUnit(List<ResourcePOJO> list, Unit unit){
        return filterByUnit(list.stream(), unit).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterByUnit(Stream<ResourcePOJO> stream,Unit unit){
        return stream.filter(t -> t.getUnit().equals(unit));
    }
    static List<ResourcePOJO> filterByCategory(List<ResourcePOJO> list, String category){
        return filterByName(list.stream(), category).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterByCategory(Stream<ResourcePOJO> stream, String category){
        return stream.filter(t -> t.getName().equals(category));
    }
    static List<ResourcePOJO> filterBySupplier(List<ResourcePOJO> list, String supplierName){
        return filterBySupplier(list.stream(), supplierName).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterBySupplier(Stream<ResourcePOJO> stream, String supplierName){
        return stream.filter(t -> t.getSupplier().getName().equals(supplierName));
    }
    static List<ResourcePOJO> filterBySupplier(List<ResourcePOJO> list, SupplierPOJO supplier){
        return filterBySupplier(list.stream(), supplier).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterBySupplier(Stream<ResourcePOJO> stream, SupplierPOJO supplier) {
        return stream.filter(t -> t.getSupplier().equals(supplier));
    }
}
