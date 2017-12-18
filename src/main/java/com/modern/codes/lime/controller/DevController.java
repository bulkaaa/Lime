package com.modern.codes.lime.controller;

import com.modern.codes.lime.service.UserService;
import com.modern.codes.lime.tools.DBPopulator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController()
@RequestMapping(path="/dev")
public class DevController {

    @Autowired
    DBPopulator pop;
    @Autowired
    UserService serv;
    @GetMapping(path = "/populate")
    public String populate(){
        pop.populate();
        return "DB reset completed";
    }
    @GetMapping(path = "/hello-world")
    public String hello(){
        return "hello world";
    }
    @GetMapping(path = "/hello-world2")
    public String hello2(){
        return "hello worl2d";
    }


}
