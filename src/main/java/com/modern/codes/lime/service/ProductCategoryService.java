package com.modern.codes.lime.service;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.dao.IProductCategoryDAO;
import com.modern.codes.lime.model.ProductCategory;
import com.modern.codes.lime.pojo.ProductCategoryPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductCategoryService extends BasicCRUDService<ProductCategory, ProductCategoryPOJO, IProductCategoryDAO> implements IProductCategoryService {

    private IProductCategoryDAO dao;
    @Autowired
    public ProductCategoryService(IProductCategoryDAO dao) {
        super(dao, ProductCategory.class, ProductCategoryPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<ProductCategoryPOJO> findByName(String name)
    {
            return ParseTools.parseList(dao.findByName(name), ProductCategoryPOJO.class);
    }

    @Override
    public ProductCategoryPOJO findByProductsName(String productName) {
        return ParseTools.parse(dao.findByProductsName(productName), ProductCategoryPOJO.class);
    }

    @Override
    public ProductCategoryPOJO findByProductsId(String productId) {
        return ParseTools.parse(dao.findByProductsId(productId), ProductCategoryPOJO.class);
    }
}