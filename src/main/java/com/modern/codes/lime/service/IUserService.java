package com.modern.codes.lime.service;

import com.modern.codes.lime.model.User;
import java.util.List;

public interface IUserService {
    List<User> getAllUsers();
    User getUserById(String userId);
    User getUserByName(String name);
    boolean addUser(User user);
    void updateUser(User user);
    void deleteUser(String userName);
    void deleteUser(User user);
    boolean userExists(String userName);
}
