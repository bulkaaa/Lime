package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Formula;

@Repository
public interface IFormulaDAO extends IBasicCRUDRepository<Formula, String> {
    List<Formula> findByProductName(String productName);

    List<Formula> findByProductId(String id);

    List<Formula> findByResourceId(String id);

    void deleteByProductId(String id);
}
