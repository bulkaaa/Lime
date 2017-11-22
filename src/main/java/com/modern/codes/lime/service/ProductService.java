package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IProductDAO;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.pojo.ProductPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BasicCRUDService<Product, ProductPOJO, IProductDAO> implements IProductService {

    @Autowired
    IProductDAO dao;
    public ProductService() {
        super(Product.class, ProductPOJO.class);
    }
}