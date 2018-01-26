package com.modern.codes.lime.controller;

import com.modern.codes.lime.model.CustomUserDetails;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.service.IUserService;
import com.modern.codes.lime.tools.DBPopulator;
import com.modern.codes.lime.tools.ParseTools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping(path="/dev")
public class DevController {

    @Autowired
    DBPopulator pop;

    @Autowired
    IUserService userService;

    @GetMapping(path = "/populate")
    public String populate(){
        pop.populate();
        return "DB reset completed";
    }
    @GetMapping(path = "/act-user")
    public String getUser(){
        return ParseTools.parseToJson(getActualUser(), User.class);
    }
    private UserPOJO getActualUser(){
        return userService.findByUsername(((UserDetails) SecurityContextHolder.getContext()
                                                                                      .getAuthentication()
                                                                                      .getPrincipal()).getUsername());
    }
}
