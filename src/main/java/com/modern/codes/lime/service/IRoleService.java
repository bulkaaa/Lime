package com.modern.codes.lime.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;

/**
 * The interface Role service.
 */
public interface IRoleService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<RolePOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save role pojo.
     *
     * @param t the t
     * @return the role pojo
     */
    RolePOJO save(Object t);

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
     * Find by id role pojo.
     *
     * @param id the id
     * @return the role pojo
     */
    RolePOJO findById(String id);

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
    List<RolePOJO> save(List l);

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
    List<RolePOJO> findByName(String name);

    /**
     * Filter by name list.
     *
     * @param list the list
     * @param name the name
     * @return the list
     */
    static List<RolePOJO> filterByName(final List<RolePOJO> list, final String name) {
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }

    /**
     * Filter by name stream.
     *
     * @param stream the stream
     * @param name   the name
     * @return the stream
     */
    static Stream<RolePOJO> filterByName(final Stream<RolePOJO> stream, final String name) {
        return stream.filter(t -> t.getName()
                                   .equals(name));
    }

    /**
     * Filter by user list.
     *
     * @param list the list
     * @param user the user
     * @return the list
     */
    static List<RolePOJO> filterByUser(final List<RolePOJO> list, final UserPOJO user) {
        return filterByUser(list.stream(), user).collect(Collectors.toList());
    }

    /**
     * Filter by user stream.
     *
     * @param stream the stream
     * @param user   the user
     * @return the stream
     */
    static Stream<RolePOJO> filterByUser(final Stream<RolePOJO> stream, final UserPOJO user) {
        return stream.filter(t -> t.getPOJOUsers()
                                   .contains(user));
    }
}