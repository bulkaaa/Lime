package com.modern.codes.lime.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.ProductCategory;
import com.modern.codes.lime.pojo.ProductPOJO;

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

    List<ProductPOJO> save(List l);

    void delete(List l);

    List<ProductPOJO> findByName(String name);

    List<ProductPOJO> findByAddedAtBetween(Date begin, Date end);

    List<ProductPOJO> findByCategoryName(String categoryName);

    List<ProductPOJO> findByCategoryId(String categoryId);

    static List<ProductPOJO> filterByName(final List<ProductPOJO> list, final String name) {
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }

    static Stream<ProductPOJO> filterByName(final Stream<ProductPOJO> stream, final String name) {
        return stream.filter(t -> t.getName()
                                   .equals(name));
    }

    static List<ProductPOJO> filterByCategory(final List<ProductPOJO> list, final ProductCategory category) {
        return filterByCategory(list.stream(), category).collect(Collectors.toList());
    }

    static Stream<ProductPOJO> filterByCategory(final Stream<ProductPOJO> stream, final ProductCategory category) {
        return stream.filter(t -> t.getCategory()
                                   .equals(category));
    }
}
