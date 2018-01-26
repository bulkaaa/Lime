package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IProductDAO extends IBasicCRUDRepository<Product, String> {
    List<Product> findByName(final String name);
    List<Product> findByAddedAtBetween(final Date begin, final Date end);
    List<Product> findByCategoryName(final String categoryName);
}
