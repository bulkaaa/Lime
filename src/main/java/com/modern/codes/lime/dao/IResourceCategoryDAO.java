package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.ResourceCategory;

/**
 * The interface Resource category dao.
 */
@Repository
public interface IResourceCategoryDAO extends IBasicCRUDRepository<ResourceCategory, String> {
    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<ResourceCategory> findByName(final String name);

    /**
     * Find by resources name resource category.
     *
     * @param resourceName the resource name
     * @return the resource category
     */
    ResourceCategory findByResourcesName(final String resourceName);

    /**
     * Find by resources id resource category.
     *
     * @param resourceId the resource id
     * @return the resource category
     */
    ResourceCategory findByResourcesId(final String resourceId);
}