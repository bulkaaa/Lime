package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.User;

import java.util.Date;
import java.util.List;

public interface IUserCustomDAO {
    List<User> getUserByName(String name, String surname);
    List<User> getUserBySurname(String name, String surname);
    List<User> getUserByLogin(String name, String surname);
    List<User> getUserJoinedAtBefore(Date date);
    List<User> getUserJoinedAtAfter(Date date);

    List<User> getUserByNameAndSurname(String name, String surname);
}
