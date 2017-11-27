package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFormulaDAO extends IBasicCRUDRepository<Formula, String> {
    List<Formula> findByProduct(Product product);
}
