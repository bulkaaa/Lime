package com.modern.codes.lime.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.User;

/**
 * The interface User dao.
 */
@Repository
public interface IUserDAO extends IBasicCRUDRepository<User, String> {
    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<User> findByName(final String name);

    /**
     * Find by surname list.
     *
     * @param surname the surname
     * @return the list
     */
    List<User> findBySurname(final String surname);

    /**
     * Find by username user.
     *
     * @param username the username
     * @return the user
     */
    User findByUsername(final String username);

    /**
     * Find by joined at between list.
     *
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    List<User> findByJoinedAtBetween(final Date begin, final Date end);

    /**
     * Find by name and surname list.
     *
     * @param name    the name
     * @param surname the surname
     * @return the list
     */
    List<User> findByNameAndSurname(final String name, final String surname);

    /**
     * Find by username or email address user.
     *
     * @param username the username
     * @param email    the email
     * @return the user
     */
    User findByUsernameOrEmailAddress(String username, String email);

    User findByEmailAddress(String email);
}