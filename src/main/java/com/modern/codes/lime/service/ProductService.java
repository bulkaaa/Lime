package com.modern.codes.lime.service;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.dao.IProductDAO;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.pojo.ProductPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductService extends BasicCRUDService<Product, ProductPOJO, IProductDAO> implements IProductService {

    private final IProductDAO dao;
    @Autowired
    public ProductService(final IProductDAO dao) {
        super(dao, Product.class, ProductPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<ProductPOJO> findByName(final String name) {
        return ParseTools.parseList(dao.findByName(name), ProductPOJO.class);
    }

    @Override
    public List<ProductPOJO> findByAddedAtBetween(final Date begin, final Date end) {
        return ParseTools.parseList(dao.findByAddedAtBetween(begin, end), ProductPOJO.class);
    }

    @Override
    public List<ProductPOJO> findByCategoryName(final String categoryName) {
        return ParseTools.parseList(dao.findByCategoryName(categoryName), ProductPOJO.class);
    }

    @Override
    public List<ProductPOJO> findByCategoryId(final String categoryId) {
        return ParseTools.parseList(dao.findByCategoryId(categoryId), ProductPOJO.class);
    }
}