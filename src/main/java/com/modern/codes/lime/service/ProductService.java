package com.modern.codes.lime.service;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.IProductDAO;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.pojo.ProductPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductService extends BasicCRUDService<Product, ProductPOJO, IProductDAO> implements IProductService {

    IProductDAO dao;
    @Autowired
    public ProductService(IProductDAO dao) {
        super(dao, Product.class, ProductPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<ProductPOJO> findByName(String name) {
        return ParseTools.parseList(dao.findByName(name), ProductPOJO.class);
    }

    @Override
    public List<ProductPOJO> findByAddedAtBetween(Date begin, Date end) {
        return ParseTools.parseList(dao.findByAddedAtBetween(begin, end), ProductPOJO.class);
    }

    @Override
    public List<ProductPOJO> findByCategory(String category) {
        return ParseTools.parseList(dao.findByCategory(category), ProductPOJO.class);
    }
}