package com.modern.codes.lime.controller;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;

import com.modern.codes.lime.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.modern.codes.lime.ParseTools.parseDate;


@RestController()
@RequestMapping(path="/api")
public class TestController {
    @Autowired
    IUserService us;
    @Autowired
    IUserDAO ud;
    @Autowired
    ParseTools ps;
    @GetMapping(path = "/add-user")
    public boolean addUser(){
        return true;
    }
    @GetMapping(path = "/hello-world2")
//    @PreAuthorize("hasAuthority('Manage Users') or hasAuthority('Get Reports')")
    @PreAuthorize("hasAuthority('Manage Users')")
    public int helloWorld2(){
        List<User> filtered = ud.findByJoinedAtBetween(parseDate("2017-05-05 13:00:00"), parseDate("2017-05-06 13:00:00"));
        return filtered.size();
    }
    @GetMapping(path = "/hello-world")
    public String helloWorld(){

        List<User> filtered = (ud.findByJoinedAtBetween(parseDate("2017-05-05 13:00:00"), parseDate("2017-05-06 13:00:00")));
        return ps.parseToJson(filtered);
    }
}
