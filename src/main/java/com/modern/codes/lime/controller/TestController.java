package com.modern.codes.lime.controller;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
//import com.modern.codes.lime.service.IUserService;
import com.modern.codes.lime.pojo.UserPOJO;

import com.modern.codes.lime.service.IUserService;
import com.modern.codes.lime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController()
@RequestMapping(path="/api")
public class TestController {
    @Autowired
//    private IBasicCRUDService<User, UserPOJO, IUserDAO> dao;
    IUserService userService;
    @GetMapping(path = "/add-user")
    public boolean addUser(){
        return true;
    }
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        List<UserPOJO> flist = userService.findAll();
        List<Object> newList= new ArrayList<Object>(flist);
        List<User> newLis = ParseTools.parseList(newList, User.class);
        return newLis.get(0).getClass().getSimpleName() + " " + newLis.getClass().getSimpleName() + " " + newLis.size() + " " + newLis.get(0).getName();
    }
}
