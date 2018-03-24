package com.modern.codes.lime.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.User;

@Repository
public interface IUserDAO extends IBasicCRUDRepository<User, String> {
    List<User> findByName(final String name);

    List<User> findBySurname(final String surname);

    User findByUsername(final String username);

    List<User> findByJoinedAtBetween(final Date begin, final Date end);

    List<User> findByNameAndSurname(final String name, final String surname);

    User findByUsernameOrEmailAddress(String username, String email);
}