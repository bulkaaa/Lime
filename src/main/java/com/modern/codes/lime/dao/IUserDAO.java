package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.User;

import java.util.List;

public interface IUserDAO {
    List<User> getAllUsers();
    User getUserById(String userId);
    User getUserByName(String name);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(String userName);
    void deleteUser(User user);
    boolean userExists(String userName);
}