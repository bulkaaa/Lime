package com.modern.codes.lime.controller;

import com.modern.codes.lime.model.Product;

import com.modern.codes.lime.model.User;
import com.modern.codes.lime.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController()
@RequestMapping(path="/api")
public class TestController {
    @Autowired
    private IUserService userService;
    @GetMapping(path = "/hello-world")
    public User helloWorld(){

//
//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(product);
//        session.getTransaction().commit();
        //return userService.userExists("ASDDDSDS");
        return userService.getUserByName("Maciej");


    }
}
