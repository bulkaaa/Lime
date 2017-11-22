package com.modern.codes.lime.controller;

import com.modern.codes.lime.pojo.UserPOJO;

import com.modern.codes.lime.service.IUserService;
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
    IUserService userService;
    @GetMapping(path = "/add-user")
    public boolean addUser(){
        return true;
    }
    @GetMapping(path = "/hello-world")
    public List<UserPOJO> helloWorld(){
        UserPOJO t = new UserPOJO("testtest", "testtest","a", "a" );
        userService.save(t);
        t = userService.getUserByNameAndSurname("testtest", "testtest").get(0);
        userService.save(t);
        List<UserPOJO> flist = userService.findAll();
        return flist;
    }
}
