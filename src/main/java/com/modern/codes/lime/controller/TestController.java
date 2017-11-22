package com.modern.codes.lime.controller;

import com.modern.codes.lime.ModelParser;
import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
//import com.modern.codes.lime.service.IUserService;
import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.pojo.UserPOJO;
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
    private IUserDAO dao;
    ModelParser parser = new ModelParser();
    List<FormulaPOJO> flist = new ArrayList<>();
    List<Object> newLis = parser.parseList((List)flist);
    @GetMapping(path = "/add-user")
    public boolean addUser(){
        return true;
    }
    @GetMapping(path = "/hello-world")
    public List<User> helloWorld(){
        return dao.findAll();
    }
}
