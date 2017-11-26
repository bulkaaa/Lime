package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.User;

import java.util.Date;
import java.util.List;

public interface IUserCustomDAO {
    List<User> findUserByName(String name, String surname);
    List<User> findUserBySurname(String surname);
    User findUserByLogin(String login);
    List<User> findByJoinedAtBetween(Date begin, Date end);
    List<User> findUserByNameAndSurname(String name, String surname);
}
