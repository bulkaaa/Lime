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
import com.modern.codes.lime.tools.StartUpPopulator;

/**
 * The type Dev controller.
 */
@RestController()
@RequestMapping(path = "/dev")
public class DevController {

    @Autowired
    public DevController(final DBPopulator pop, final StartUpPopulator upPop) {
        this.pop = pop;
        this.upPop = upPop;
    }

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
     * initialize basic db objects.
     *
     * @return the string
     */
    @GetMapping("/startup")
    public String startup(){
        if(!isInitialized){
            pop.clearDB();
            upPop.createRoles();
            upPop.addAdmin();
            // DEVELOPERSKO ZAWSZE Włączone
            // isInitialized = true;
            return "Application initialized succesfully";
        }
        return "Application was arleady initialized";
    }

    private boolean isInitialized = false;
    /**
     * The Populator.
     */
    private final DBPopulator pop;


    /**
     * The Startup Populator.
     */

    private final StartUpPopulator upPop;

}
