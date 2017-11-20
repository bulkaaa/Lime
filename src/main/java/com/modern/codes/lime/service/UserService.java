package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private IUserDAO userDAO;
    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public User getUserById(String userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public synchronized boolean addUser(User user) {
        if (userDAO.userExists(user.getName())) {
            return false;
        } else {
            userDAO.addUser(user);
            return true;
        }
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUser(String userName) {
        userDAO.deleteUser(userName);
    }

    @Override
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public boolean userExists(String userName) {
        return userDAO.userExists(userName);
    }
}
