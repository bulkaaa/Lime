package com.modern.codes.lime.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;

public interface IFormulaService {
    List<FormulaPOJO> findAll();

    void delete(String id);

    FormulaPOJO save(Object t);

    boolean exists(String id);

    boolean exists(Object t);

    long count();

    boolean equals(Object t, Object y);

    void deleteAll();

    FormulaPOJO findById(String id);

    void delete(Object t);

    void save(List l);

    void delete(List l);

    List<FormulaPOJO> findByProductName(String productName);

    List<FormulaPOJO> findByProductId(String id);

    List<FormulaPOJO> findByResourceId(String id);

    void addFormula(Map<Resource, Double> iFormula, ProductPOJO product);

    static List<FormulaPOJO> filterByResource(final List<FormulaPOJO> list, final String resourceName) {
        return filterByResource(list.stream(), resourceName).collect(Collectors.toList());
    }

    static Stream<FormulaPOJO> filterByResource(final Stream<FormulaPOJO> stream, final String resourceName) {
        return stream.filter(t -> t.getPOJOResource()
                                   .getName()
                                   .equals(resourceName));
    }

    static List<FormulaPOJO> filterByResource(final List<FormulaPOJO> list, final ResourcePOJO resource) {
        return filterByResource(list.stream(), resource).collect(Collectors.toList());
    }

    static Stream<FormulaPOJO> filterByResource(final Stream<FormulaPOJO> stream, final ResourcePOJO resource) {
        return stream.filter(t -> t.getPOJOResource()
                                   .equals(resource));
    }

    static List<FormulaPOJO> filterByResources(final List<FormulaPOJO> list, final List<String> resources) {
        return filterByResources(list.stream(), resources).collect(Collectors.toList());
    }

    static Stream<FormulaPOJO> filterByResources(final Stream<FormulaPOJO> stream, final List<String> resources) {
        return stream.filter(t -> resources.contains(t.getPOJOResource()
                                                      .getId()));
    }

    static List<FormulaPOJO> filterByProduct(final List<FormulaPOJO> list, final String productName) {
        return filterByProduct(list.stream(), productName).collect(Collectors.toList());
    }

    static Stream<FormulaPOJO> filterByProduct(final Stream<FormulaPOJO> stream, final String productName) {
        return stream.filter(t -> t.getPOJOProduct()
                                   .getName()
                                   .equals(productName));
    }

    static List<FormulaPOJO> filterByProduct(final List<FormulaPOJO> list, final ProductPOJO product) {
        return filterByProduct(list.stream(), product).collect(Collectors.toList());
    }

    static Stream<FormulaPOJO> filterByProduct(final Stream<FormulaPOJO> stream, final ProductPOJO product) {
        return stream.filter(t -> t.getPOJOProduct()
                                   .equals(product));
    }
}
