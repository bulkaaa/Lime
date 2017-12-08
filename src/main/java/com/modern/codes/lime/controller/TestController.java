package com.modern.codes.lime.controller;

import com.modern.codes.lime.tools.DBPopulator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController()
@RequestMapping(path="/dev")
public class TestController {

    @Autowired
    DBPopulator pop;

    @GetMapping(path = "/populate")
    public String populate(){
        pop.populate();
        return "DB reset completed";
    }
}
