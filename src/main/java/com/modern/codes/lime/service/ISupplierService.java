package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.ResourcePOJO;
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
    static List<SupplierPOJO> filterByCity(List<SupplierPOJO> list, String city){
        return filterByCity(list.stream(), city).collect(Collectors.toList());
    }
    static Stream<SupplierPOJO> filterByCity(Stream<SupplierPOJO> stream, String city){
        return stream.filter(t -> t.getCity().equals(city));
    }
    static List<SupplierPOJO> filterByEmailAddress(List<SupplierPOJO> list, String emailAddress){
        return filterByEmailAddress(list.stream(), emailAddress).collect(Collectors.toList());
    }
    static Stream<SupplierPOJO> filterByEmailAddress(Stream<SupplierPOJO> stream, String emailAddress){
        return stream.filter(t -> t.getEmailAddress().equals(emailAddress));
    }
    static List<SupplierPOJO> filterByTelephone(List<SupplierPOJO> list, String telephone){
        return filterByTelephone(list.stream(), telephone).collect(Collectors.toList());
    }
    static Stream<SupplierPOJO> filterByTelephone(Stream<SupplierPOJO> stream, String telephone){
        return stream.filter(t -> t.getTelephone().equals(telephone));
    }
    static List<SupplierPOJO> filterByCountry(List<SupplierPOJO> list, String country){
        return filterByCountry(list.stream(), country).collect(Collectors.toList());
    }
    static Stream<SupplierPOJO> filterByCountry(Stream<SupplierPOJO> stream, String country){
        return stream.filter(t -> t.getCountry().equals(country));
    }
    static List<SupplierPOJO> filterByResource(List<SupplierPOJO> list, String resourceName){
        return filterByResource(list.stream(), resourceName).collect(Collectors.toList());
    }
    static Stream<SupplierPOJO> filterByResource(Stream<SupplierPOJO> stream, String resourceName){
        return stream.filter(t -> !IResourceService.filterByName(t.getResources(), resourceName).isEmpty());
    }
    static List<SupplierPOJO> filterByResource(List<SupplierPOJO> list, ResourcePOJO resource){
        return filterByResource(list.stream(), resource).collect(Collectors.toList());
    }
    static Stream<SupplierPOJO> filterByResource(Stream<SupplierPOJO> stream, ResourcePOJO resource){
        return stream.filter(t -> t.getResources().contains(resource));
    }
}
