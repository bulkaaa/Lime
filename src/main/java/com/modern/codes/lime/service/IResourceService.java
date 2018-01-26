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
    boolean exists(Object t);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    ResourcePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);

    List<ResourcePOJO> findByName(String name);
    List<ResourcePOJO> findByCategoryName(String categoryName);
    List<ResourcePOJO> findBySupplierId(String supplierId);
    List<ResourcePOJO> findByCategoryId(String categoryId);

    static List<ResourcePOJO> filterByName(final List<ResourcePOJO> list, final String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterByName(final Stream<ResourcePOJO> stream, final String name){
        return stream.filter(t -> t.getName().equals(name));
    }
    static List<ResourcePOJO> filterByUnit(final List<ResourcePOJO> list, final Unit unit){
        return filterByUnit(list.stream(), unit).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterByUnit(final Stream<ResourcePOJO> stream, final Unit unit){
        return stream.filter(t -> t.getUnit().equals(unit));
    }
    static List<ResourcePOJO> filterByCategory(final List<ResourcePOJO> list, final String category){
        return filterByName(list.stream(), category).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterByCategory(final Stream<ResourcePOJO> stream, final String category){
        return stream.filter(t -> t.getName().equals(category));
    }
    static List<ResourcePOJO> filterBySupplier(final List<ResourcePOJO> list, final String supplierName){
        return filterBySupplier(list.stream(), supplierName).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterBySupplier(final Stream<ResourcePOJO> stream, final String supplierName){
        return stream.filter(t -> t.getPOJOSupplier().getName().equals(supplierName));
    }
    static List<ResourcePOJO> filterBySupplier(final List<ResourcePOJO> list, final SupplierPOJO supplier){
        return filterBySupplier(list.stream(), supplier).collect(Collectors.toList());
    }
    static Stream<ResourcePOJO> filterBySupplier(final Stream<ResourcePOJO> stream, final SupplierPOJO supplier) {
        return stream.filter(t -> t.getPOJOSupplier().equals(supplier));
    }
}
