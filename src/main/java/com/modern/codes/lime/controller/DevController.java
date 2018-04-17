package com.modern.codes.lime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modern.codes.lime.tools.DBPopulator;
import com.modern.codes.lime.tools.StartUpPopulator;
import com.modern.codes.lime.tools.ThesisPopulator;

/**
 * The type Dev controller.
 */
@RestController()
@RequestMapping(path = "/dev")
public class DevController {

    @Autowired
    public DevController(final DBPopulator pop, final StartUpPopulator upPop, final ThesisPopulator thesisPop) {
        this.pop = pop;
        this.upPop = upPop;
        this.thesisPop = thesisPop;
    }

    /**
     * Populate string.
     *
     * @return the string
     */
    @GetMapping(path = "/thesis")
    public String populate() {
        thesisPop.populate();
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

    /**
     * The Thesis Populator.
     */

    private final ThesisPopulator thesisPop;
}
