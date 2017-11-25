package com.modern.codes.lime.controller;

import com.modern.codes.lime.DBPopulator;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.pojo.UserPOJO;

import com.modern.codes.lime.service.IProductService;
import com.modern.codes.lime.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController()
@RequestMapping(path="/api")
public class TestController {
    @Autowired
    DBPopulator pop;
    @Autowired
    IUserService us;
    @Autowired
    IProductService ps;
    @GetMapping(path = "/add-user")
    public boolean addUser(){
        return true;
    }
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        pop.populate();
    return "job reset";
    }
}
