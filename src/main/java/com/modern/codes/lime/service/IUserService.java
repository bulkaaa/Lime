package com.modern.codes.lime.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;

/**
 * The interface User service.
 */
public interface IUserService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<UserPOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save user pojo.
     *
     * @param t the t
     * @return the user pojo
     */
    UserPOJO save(Object t);

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
     * Find by id user pojo.
     *
     * @param id the id
     * @return the user pojo
     */
    UserPOJO findById(String id);

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
    List<UserPOJO> save(List l);

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
    List<UserPOJO> findByName(String name);

    /**
     * Find by surname list.
     *
     * @param surname the surname
     * @return the list
     */
    List<UserPOJO> findBySurname(String surname);

    /**
     * Find by username user pojo.
     *
     * @param username the username
     * @return the user pojo
     */
    UserPOJO findByUsername(String username);

    /**
     * Find by joined at between list.
     *
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    List<UserPOJO> findByJoinedAtBetween(Date begin, Date end);

    /**
     * Find by name and surname list.
     *
     * @param name    the name
     * @param surname the surname
     * @return the list
     */
    List<UserPOJO> findByNameAndSurname(String name, String surname);

    /**
     * Find by username or email user pojo.
     *
     * @param username the username
     * @param email    the email
     * @return the user pojo
     */
    UserPOJO findByUsernameOrEmail(String username, String email);

    /**
     * Gets password encoder.
     *
     * @return the password encoder
     */
    PasswordEncoder getPasswordEncoder();

    /**
     * Filter by name list.
     *
     * @param list the list
     * @param name the name
     * @return the list
     */
    static List<UserPOJO> filterByName(final List<UserPOJO> list, final String name) {
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }

    /**
     * Filter by name stream.
     *
     * @param stream the stream
     * @param name   the name
     * @return the stream
     */
    static Stream<UserPOJO> filterByName(final Stream<UserPOJO> stream, final String name) {
        return stream.filter(t -> t.getName()
                                   .equals(name));
    }

    /**
     * Filter by surname list.
     *
     * @param list    the list
     * @param surname the surname
     * @return the list
     */
    static List<UserPOJO> filterBySurname(final List<UserPOJO> list, final String surname) {
        return filterBySurname(list.stream(), surname).collect(Collectors.toList());
    }

    /**
     * Filter by surname stream.
     *
     * @param stream  the stream
     * @param surname the surname
     * @return the stream
     */
    static Stream<UserPOJO> filterBySurname(final Stream<UserPOJO> stream, final String surname) {
        return stream.filter(t -> t.getName()
                                   .equals(surname));
    }

    /**
     * Filter by role list.
     *
     * @param list     the list
     * @param roleName the role name
     * @return the list
     */
    static List<UserPOJO> filterByRole(final List<UserPOJO> list, final String roleName) {
        return filterByRole(list.stream(), roleName).collect(Collectors.toList());
    }

    /**
     * Filter by role stream.
     *
     * @param stream   the stream
     * @param roleName the role name
     * @return the stream
     */
    static Stream<UserPOJO> filterByRole(final Stream<UserPOJO> stream, final String roleName) {
        return stream.filter(t -> !IRoleService.filterByName(t.getPOJORoles(), roleName)
                                               .isEmpty());
    }

    /**
     * Filter by role list.
     *
     * @param list the list
     * @param role the role
     * @return the list
     */
    static List<UserPOJO> filterByRole(final List<UserPOJO> list, final RolePOJO role) {
        return filterByRole(list.stream(), role).collect(Collectors.toList());
    }

    /**
     * Filter by role stream.
     *
     * @param stream the stream
     * @param role   the role
     * @return the stream
     */
    static Stream<UserPOJO> filterByRole(final Stream<UserPOJO> stream, final RolePOJO role) {
        return stream.filter(t -> t.getPOJORoles()
                                   .contains(role));
    }
}