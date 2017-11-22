package com.modern.codes.lime.controller;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
//import com.modern.codes.lime.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping(path="/api")
public class TestController {
    @Autowired
    private IUserDAO dao;
    @GetMapping(path = "/add-user")
    public boolean addUser(){

        return true;
    }
    @GetMapping(path = "/hello-world")
    public List<User> helloWorld(){
        return dao.findAll();
    }
}
