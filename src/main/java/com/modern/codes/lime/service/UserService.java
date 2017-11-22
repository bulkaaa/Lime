package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class UserService implements IUserService {

    @Autowired
    IUserDAO dao;
//    public UserService() {
//        super(User.class, UserPOJO.class);
//    }
}
