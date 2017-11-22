package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserDAO{
    List<User> getUserByNameAndSurname(String name, String surname);
}