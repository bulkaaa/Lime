package com.modern.codes.lime.service;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.modern.codes.lime.dao.IBasicCRUDRepository;

/**
 * The interface Basic crud service.
 *
 * @param <T>      the type parameter
 * @param <T_POJO> the type parameter
 * @param <T_DAO>  the type parameter
 */
@NoRepositoryBean
public interface IBasicCRUDService<T, T_POJO, T_DAO extends IBasicCRUDRepository<T, String>> {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<T_POJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save t pojo.
     *
     * @param t the t
     * @return the t pojo
     */
    T_POJO save(T_POJO t);

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
    boolean exists(T_POJO t);

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
    boolean equals(T_POJO t, T_POJO y);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Find by id t pojo.
     *
     * @param id the id
     * @return the t pojo
     */
    T_POJO findById(String id);

    /**
     * Delete.
     *
     * @param t the t
     */
    void delete(T_POJO t);

    /**
     * Save list.
     *
     * @param l the l
     * @return the list
     */
    List<T_POJO> save(List<T_POJO> l);

    /**
     * Delete.
     *
     * @param l the l
     */
    void delete(List<T_POJO> l);
}
