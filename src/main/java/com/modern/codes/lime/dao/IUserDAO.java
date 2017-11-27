package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IUserDAO extends IBasicCRUDRepository<User, String> {
    List<User> findByName(String name);
    List<User> findBySurname(String surname);
    User findByLogin(String login);
    List<User> findByJoinedAtBetween(Date begin, Date end);
    List<User> findByNameAndSurname(String name, String surname);
}