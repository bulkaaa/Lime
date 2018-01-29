package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IUserDAO extends IBasicCRUDRepository<User, String> {
    List<User> findByName(final String name);
    List<User> findBySurname(final String surname);
    User findByUsername(final String username);
    List<User> findByJoinedAtBetween(final Date begin, final Date end);
    List<User> findByNameAndSurname(final String name, final String surname);
    List<User> findByUsernameOrEmailAddress(String username, String email);
}