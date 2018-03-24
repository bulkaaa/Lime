package com.modern.codes.lime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IProductCategoryDAO;
import com.modern.codes.lime.model.ProductCategory;
import com.modern.codes.lime.pojo.ProductCategoryPOJO;
import com.modern.codes.lime.tools.ParseTools;

@Service
public class ProductCategoryService extends BasicCRUDService<ProductCategory, ProductCategoryPOJO, IProductCategoryDAO>
        implements IProductCategoryService {

    @Autowired
    public ProductCategoryService(final IProductCategoryDAO dao) {
        super(dao, ProductCategory.class, ProductCategoryPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<ProductCategoryPOJO> findByName(final String name) {
        return ParseTools.parseList(dao.findByName(name), ProductCategoryPOJO.class);
    }

    @Override
    public ProductCategoryPOJO findByProductsName(final String productName) {
        return ParseTools.parse(dao.findByProductsName(productName), ProductCategoryPOJO.class);
    }

    @Override
    public ProductCategoryPOJO findByProductsId(final String productId) {
        return ParseTools.parse(dao.findByProductsId(productId), ProductCategoryPOJO.class);
    }
    private final IProductCategoryDAO dao;
}