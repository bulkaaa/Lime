package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Resource;

/**
 * The interface Resource dao.
 */
@Repository
public interface IResourceDAO extends IBasicCRUDRepository<Resource, String> {
    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Resource> findByName(final String name);

    /**
     * Find by category name list.
     *
     * @param categoryName the category name
     * @return the list
     */
    List<Resource> findByCategoryName(final String categoryName);

    /**
     * Find by supplier id list.
     *
     * @param supplierId the supplier id
     * @return the list
     */
    List<Resource> findBySupplierId(final String supplierId);

    /**
     * Find by category id list.
     *
     * @param categoryId the category id
     * @return the list
     */
    List<Resource> findByCategoryId(final String categoryId);
}
