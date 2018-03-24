package com.modern.codes.lime.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.SupplierPOJO;

public interface ISupplierService {
    List<SupplierPOJO> findAll();

    void delete(String id);

    SupplierPOJO save(Object t);

    boolean exists(String id);

    boolean exists(Object t);

    long count();

    boolean equals(Object t, Object y);

    void deleteAll();

    SupplierPOJO findById(String id);

    void delete(Object t);

    void save(List l);

    void delete(List l);

    List<SupplierPOJO> findByEmailAddress(String emailAddress);

    List<SupplierPOJO> findByPostalCode(String postalCode);

    List<SupplierPOJO> findByCity(String city);

    List<SupplierPOJO> findByCountry(String country);

    List<SupplierPOJO> findByName(String name);

    List<SupplierPOJO> findByTelephone(String telephone);

    static List<SupplierPOJO> filterByName(final List<SupplierPOJO> list, final String name) {
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }

    static Stream<SupplierPOJO> filterByName(final Stream<SupplierPOJO> stream, final String name) {
        return stream.filter(t -> t.getName()
                                   .equals(name));
    }

    static List<SupplierPOJO> filterByCity(final List<SupplierPOJO> list, final String city) {
        return filterByCity(list.stream(), city).collect(Collectors.toList());
    }

    static Stream<SupplierPOJO> filterByCity(final Stream<SupplierPOJO> stream, final String city) {
        return stream.filter(t -> t.getCity()
                                   .equals(city));
    }

    static List<SupplierPOJO> filterByEmailAddress(final List<SupplierPOJO> list, final String emailAddress) {
        return filterByEmailAddress(list.stream(), emailAddress).collect(Collectors.toList());
    }

    static Stream<SupplierPOJO> filterByEmailAddress(final Stream<SupplierPOJO> stream, final String emailAddress) {
        return stream.filter(t -> t.getEmailAddress()
                                   .equals(emailAddress));
    }

    static List<SupplierPOJO> filterByTelephone(final List<SupplierPOJO> list, final String telephone) {
        return filterByTelephone(list.stream(), telephone).collect(Collectors.toList());
    }

    static Stream<SupplierPOJO> filterByTelephone(final Stream<SupplierPOJO> stream, final String telephone) {
        return stream.filter(t -> t.getTelephone()
                                   .equals(telephone));
    }

    static List<SupplierPOJO> filterByCountry(final List<SupplierPOJO> list, final String country) {
        return filterByCountry(list.stream(), country).collect(Collectors.toList());
    }

    static Stream<SupplierPOJO> filterByCountry(final Stream<SupplierPOJO> stream, final String country) {
        return stream.filter(t -> t.getCountry()
                                   .equals(country));
    }

    static List<SupplierPOJO> filterByResource(final List<SupplierPOJO> list, final String resourceName) {
        return filterByResource(list.stream(), resourceName).collect(Collectors.toList());
    }

    static Stream<SupplierPOJO> filterByResource(final Stream<SupplierPOJO> stream, final String resourceName) {
        return stream.filter(t -> !IResourceService.filterByName(t.getPOJOResources(), resourceName)
                                                   .isEmpty());
    }

    static List<SupplierPOJO> filterByResource(final List<SupplierPOJO> list, final ResourcePOJO resource) {
        return filterByResource(list.stream(), resource).collect(Collectors.toList());
    }

    static Stream<SupplierPOJO> filterByResource(final Stream<SupplierPOJO> stream, final ResourcePOJO resource) {
        return stream.filter(t -> t.getPOJOResources()
                                   .contains(resource));
    }
}