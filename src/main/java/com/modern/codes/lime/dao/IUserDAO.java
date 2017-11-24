package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface IUserDAO extends IBasicCRUDRepository<User, String>{
    List<User> getUserByName(String name, String surname);
    List<User> getUserBySurname(String name, String surname);
    List<User> getUserByLogin(String name, String surname);
    List<User> getJoinedBefore(Date date);
    List<User> getJoinedAfter(Date date);

    List<User> getUserByNameAndSurname(String name, String surname);
}