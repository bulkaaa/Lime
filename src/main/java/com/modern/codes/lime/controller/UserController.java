package com.modern.codes.lime.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.service.IUserService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a User object", notes = "Creates a <b>User</b> object "
                                                              + "Saves it into DB.", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Saved User object"),
                           @ApiResponse(code = 422, message = "In case of validation errors of User object")})
    @ResponseBody
    public String create(
            @Validated @RequestBody @ApiParam(value = "User object") final User User,
            final BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("User creation request received: {}", User);

        if (User == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid User creation request, form data contains %s error(s).",
                                                            bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        return ParseTools.parseToJson(userService.save(User), User.class);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a User object", notes = "Updates a <b>User</b> object ", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved User object"),
                            @ApiResponse(code = 422, message = "In case of validation errors of User object")})
    @ResponseBody
    public String update(
            @Validated @RequestBody @ApiParam(value = "User object") final User User,
            final BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("User update request received: {}", User);

        if (User == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid User update request, form data contains %s error(s).",
                                                            bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        return ParseTools.parseToJson(userService.save(User), User.class);
    }


    @RequestMapping(value = "/one/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a User object", notes = "Fetches a <b>User</b> object by id ", response = ProductPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved User object")})
    @ResponseBody
    public String getUser(
            @ApiParam(value = "User object") @PathVariable final String userId) {

        LOG.info("User fetch request received for id: " + userId);
        return ParseTools.parseToJson(userService.findById(userId), User.class);
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a User object", notes = "Deletes a <b>User</b> object ", response = ProductPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved User object")})
    @ResponseBody
    public Boolean delete(
            @ApiParam(value = "User object") @PathVariable final String productId) {

        LOG.info("User deletion request received for id: " + productId);


        userService.delete(productId);
        return true;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all users", notes = "Fetches all users from DB ", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Fetch all users")})
    @ResponseBody
    public String getAll() {

        LOG.info("Fetch all User request received");

        return ParseTools.parseToJson(userService.findAll(), User.class);
    }


    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Saved User object"), @ApiResponse(code = 404,
                                                                                                    message = "In case of no User object was found in DB for given name")})
    @ApiOperation(value = "Fetch userss by name",
                  notes = "Searches for all <b>User</b> objects in DB "
                          + "Returns list of User objects for given name.")
    @RequestMapping(value = "/get-by-name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getByName(@RequestParam(value="name") final String name) {

        LOG.info("User get-by-name request received: name = " + name);
        return ParseTools.parseToJson(userService.findByName(name), User.class);
    }

}
