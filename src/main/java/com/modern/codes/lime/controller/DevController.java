package com.modern.codes.lime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.service.IUserService;
import com.modern.codes.lime.tools.DBPopulator;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Dev controller.
 */
@RestController()
@RequestMapping(path = "/dev")
public class DevController {

    /**
     * Populate string.
     *
     * @return the string
     */
    @GetMapping(path = "/populate")
    public String populate() {
        pop.populate();
        return "DB reset completed";
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    @GetMapping(path = "/act-user")
    public String getUser() {
        return ParseTools.parseToJson(getActualUser(), User.class);
    }

    private UserPOJO getActualUser() {
        return userService.findByUsername(((UserDetails) SecurityContextHolder.getContext()
                                                                              .getAuthentication()
                                                                              .getPrincipal()).getUsername());
    }

    /**
     * The Pop.
     */
    @Autowired
    DBPopulator pop;
    /**
     * The User service.
     */
    @Autowired
    IUserService userService;

}
