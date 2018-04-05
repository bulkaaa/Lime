package com.modern.codes.lime.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.SupplierPOJO;

/**
 * The interface Resource service.
 */
public interface IResourceService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<ResourcePOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save resource pojo.
     *
     * @param t the t
     * @return the resource pojo
     */
    ResourcePOJO save(Object t);

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
     * Find by id resource pojo.
     *
     * @param id the id
     * @return the resource pojo
     */
    ResourcePOJO findById(String id);

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
    List<ResourcePOJO> save(List l);

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
    List<ResourcePOJO> findByName(String name);

    /**
     * Find by category name list.
     *
     * @param categoryName the category name
     * @return the list
     */
    List<ResourcePOJO> findByCategoryName(String categoryName);

    /**
     * Find by supplier id list.
     *
     * @param supplierId the supplier id
     * @return the list
     */
    List<ResourcePOJO> findBySupplierId(String supplierId);

    /**
     * Find by category id list.
     *
     * @param categoryId the category id
     * @return the list
     */
    List<ResourcePOJO> findByCategoryId(String categoryId);

    /**
     * Filter by name list.
     *
     * @param list the list
     * @param name the name
     * @return the list
     */
    static List<ResourcePOJO> filterByName(final List<ResourcePOJO> list, final String name) {
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }

    /**
     * Filter by name stream.
     *
     * @param stream the stream
     * @param name   the name
     * @return the stream
     */
    static Stream<ResourcePOJO> filterByName(final Stream<ResourcePOJO> stream, final String name) {
        return stream.filter(t -> t.getName()
                                   .equals(name));
    }

    /**
     * Filter by unit list.
     *
     * @param list the list
     * @param unit the unit
     * @return the list
     */
    static List<ResourcePOJO> filterByUnit(final List<ResourcePOJO> list, final Unit unit) {
        return filterByUnit(list.stream(), unit).collect(Collectors.toList());
    }

    /**
     * Filter by unit stream.
     *
     * @param stream the stream
     * @param unit   the unit
     * @return the stream
     */
    static Stream<ResourcePOJO> filterByUnit(final Stream<ResourcePOJO> stream, final Unit unit) {
        return stream.filter(t -> t.getUnit()
                                   .equals(unit));
    }

    /**
     * Filter by category list.
     *
     * @param list     the list
     * @param category the category
     * @return the list
     */
    static List<ResourcePOJO> filterByCategory(final List<ResourcePOJO> list, final String category) {
        return filterByName(list.stream(), category).collect(Collectors.toList());
    }

    /**
     * Filter by category stream.
     *
     * @param stream   the stream
     * @param category the category
     * @return the stream
     */
    static Stream<ResourcePOJO> filterByCategory(final Stream<ResourcePOJO> stream, final String category) {
        return stream.filter(t -> t.getName()
                                   .equals(category));
    }

    /**
     * Filter by supplier list.
     *
     * @param list         the list
     * @param supplierName the supplier name
     * @return the list
     */
    static List<ResourcePOJO> filterBySupplier(final List<ResourcePOJO> list, final String supplierName) {
        return filterBySupplier(list.stream(), supplierName).collect(Collectors.toList());
    }

    /**
     * Filter by supplier stream.
     *
     * @param stream       the stream
     * @param supplierName the supplier name
     * @return the stream
     */
    static Stream<ResourcePOJO> filterBySupplier(final Stream<ResourcePOJO> stream, final String supplierName) {
        return stream.filter(t -> t.getPOJOSupplier()
                                   .getName()
                                   .equals(supplierName));
    }

    /**
     * Filter by supplier list.
     *
     * @param list     the list
     * @param supplier the supplier
     * @return the list
     */
    static List<ResourcePOJO> filterBySupplier(final List<ResourcePOJO> list, final SupplierPOJO supplier) {
        return filterBySupplier(list.stream(), supplier).collect(Collectors.toList());
    }

    /**
     * Filter by supplier stream.
     *
     * @param stream   the stream
     * @param supplier the supplier
     * @return the stream
     */
    static Stream<ResourcePOJO> filterBySupplier(final Stream<ResourcePOJO> stream, final SupplierPOJO supplier) {
        return stream.filter(t -> t.getPOJOSupplier()
                                   .equals(supplier));
    }
}
