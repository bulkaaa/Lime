package com.modern.codes.lime.service;

import java.util.List;

import com.modern.codes.lime.pojo.ResourceCategoryPOJO;

/**
 * The interface Resource category service.
 */
public interface IResourceCategoryService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<ResourceCategoryPOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save resource category pojo.
     *
     * @param t the t
     * @return the resource category pojo
     */
    ResourceCategoryPOJO save(Object t);

    /**
     * Exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean exists(String id);

    /**
     * Exists boolean.
     *
     * @param t the t
     * @return the boolean
     */
    boolean exists(Object t);

    /**
     * Count long.
     *
     * @return the long
     */
    long count();

    /**
     * Equals boolean.
     *
     * @param t the t
     * @param y the y
     * @return the boolean
     */
    boolean equals(Object t, Object y);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Find by id resource category pojo.
     *
     * @param id the id
     * @return the resource category pojo
     */
    ResourceCategoryPOJO findById(String id);

    /**
     * Delete.
     *
     * @param t the t
     */
    void delete(Object t);

    /**
     * Save list.
     *
     * @param l the l
     * @return the list
     */
    List<ResourceCategoryPOJO> save(List l);

    /**
     * Delete.
     *
     * @param l the l
     */
    void delete(List l);

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<ResourceCategoryPOJO> findByName(String name);

    /**
     * Find by resources name resource category pojo.
     *
     * @param resourceName the resource name
     * @return the resource category pojo
     */
    ResourceCategoryPOJO findByResourcesName(String resourceName);

    /**
     * Find by resources id resource category pojo.
     *
     * @param resourceId the resource id
     * @return the resource category pojo
     */
    ResourceCategoryPOJO findByResourcesId(String resourceId);
}