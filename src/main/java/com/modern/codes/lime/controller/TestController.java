package com.modern.codes.lime.controller;

import com.modern.codes.lime.DBPopulator;
import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.IResourceDAO;
import com.modern.codes.lime.model.Privilege;
import com.modern.codes.lime.model.Resource;

import com.modern.codes.lime.pojo.PrivilegePOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.service.IPrivilegeService;
import com.modern.codes.lime.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController()
@RequestMapping(path="/api")
public class TestController {
    @Autowired
    IUserService us;
    @Autowired
    IResourceDAO ud;
    @Autowired
    DBPopulator pop;
    @Autowired
    IPrivilegeService ps;
    @GetMapping(path = "/add-user")
    public boolean addUser(){
        return true;
    }
    @GetMapping(path = "/hello-world2")
//    @PreAuthorize("hasAuthority('Manage Users') or hasAuthority('Get Reports')")
    @PreAuthorize("hasAuthority('Manage Users')")
    public int helloWorld2(){
        List<Resource> filtered = ud.findAll();
        return filtered.size();
    }
    @GetMapping(path = "/hello-world")
    public String helloWorld(){
        pop.populate();
        List<ResourcePOJO> filtered = ParseTools.parseList(ud.findAll(), ResourcePOJO.class);
        return ParseTools.parseToJson(filtered, Resource.class);
    }
    @GetMapping(path = "/populate")
    public String populate(){
        pop.populate();
        return "DB reset completed";
    }
}
