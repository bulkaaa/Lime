package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDAO extends IBasicCRUDRepository<Product, String>, IProductCustomDAO{

}
