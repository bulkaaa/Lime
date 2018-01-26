package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Formula;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFormulaDAO extends IBasicCRUDRepository<Formula, String> {
    List<Formula> findByProductName(String productName);
    List<Formula> findByProductId(String id);
    List<Formula> findByResourceId(String id);
}
