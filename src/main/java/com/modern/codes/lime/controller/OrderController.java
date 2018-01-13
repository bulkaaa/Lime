package com.modern.codes.lime.controller;

import com.modern.codes.lime.order.Order;
import com.modern.codes.lime.tools.DBPopulator;
import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController()
@RequestMapping(path="/order")
public class OrderController {

    @Autowired
    Order ord;
    @GetMapping(path = "/test")
    public String sendTest(){
        String msg = ord.ConstructOrderMsg("Some Company", "Aloe", 8);
        ord.SendEmail("aleksandrabulka1@gmail.com","Order Email form LIME", msg);
        return "Email Sent";
    }
}
