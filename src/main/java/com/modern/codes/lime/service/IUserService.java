package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    List<UserPOJO> findAll();
    void delete(String id);
    List<UserPOJO> getUserByNameAndSurname(String name, String surname);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    UserPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
}
