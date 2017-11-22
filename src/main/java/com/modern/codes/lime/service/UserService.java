package com.modern.codes.lime.service;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BasicCRUDService<User, UserPOJO, IUserDAO> implements IUserService {

    private IUserDAO dao;
    @Autowired
    public UserService(IUserDAO dao) {
        super(dao, User.class, UserPOJO.class);
        this.dao = dao;
    }
    public List<UserPOJO> getUserByNameAndSurname(String name, String surname){
        return ParseTools.parseList(dao.getUserByNameAndSurname(name, surname), UserPOJO.class);
    }
}
