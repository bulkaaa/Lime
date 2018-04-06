package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Formula;

/**
 * The interface Formula dao.
 */
@Repository
public interface IFormulaDAO extends IBasicCRUDRepository<Formula, String> {
    /**
     * Find by product name list.
     *
     * @param productName the product name
     * @return the list
     */
    List<Formula> findByProductName(String productName);

    /**
     * Find by product id list.
     *
     * @param id the id
     * @return the list
     */
    List<Formula> findByProductId(String id);

    /**
     * Find by resource id list.
     *
     * @param id the id
     * @return the list
     */
    List<Formula> findByResourceId(String id);

    /**
     * Delete by product id.
     *
     * @param id the id
     */
    void deleteByProductId(String id);
}
