package com.modern.codes.lime.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Product;

@Repository
public interface IProductDAO extends IBasicCRUDRepository<Product, String> {
    List<Product> findByName(final String name);

    List<Product> findByAddedAtBetween(final Date begin, final Date end);

    List<Product> findByCategoryName(final String categoryName);

    List<Product> findByCategoryId(final String categoryId);
}
