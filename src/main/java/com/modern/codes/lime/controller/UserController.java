package com.modern.codes.lime.controller;

import java.util.List;
import java.util.Locale;

import com.modern.codes.lime.model.Role;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.service.IRoleService;
import com.modern.codes.lime.service.IUserService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;
    @Autowired
    IRoleService roleService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a User object", notes = "Creates a <b>User</b> object "
                                                              + "Saves it into DB.", response = User.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Saved User object"),
                           @ApiResponse(code = 422, message = "In case of validation errors of User object")})
    @ResponseBody
    public String create(
            @Validated @RequestBody @ApiParam(value = "User object") final User user,
            final BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("User creation request received: {}", user);

        if (user == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid User creation request, form data contains %s error(s).",
                                                            bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        return ParseTools.parseToJson(userService.save(user), User.class);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a User object", notes = "Updates a <b>User</b> object ", response = User.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved User object"),
                            @ApiResponse(code = 422, message = "In case of validation errors of User object")})
    @ResponseBody
    public String update(
            @Validated @RequestBody @ApiParam(value = "User object") final User user,
            final BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("User update request received: {}", user);

        if (user == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid User update request, form data contains %s error(s).",
                                                            bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        return ParseTools.parseToJson(userService.save(user), User.class);
    }


    @RequestMapping(value = "/one/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a User object", notes = "Fetches a <b>User</b> object by id ", response = UserPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved User object")})
    @ResponseBody
    public String getUser(
            @ApiParam(value = "User object") @PathVariable final String userId) {

        LOG.info("User fetch request received for id: " + userId);
        return ParseTools.parseToJson(userService.findById(userId), User.class);
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a User object", notes = "Deletes a <b>User</b> object ", response = UserPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved User object")})
    @ResponseBody
    public Boolean delete(
            @ApiParam(value = "User object") @PathVariable final String userId) {

        LOG.info("User deletion request received for id: " + userId);

        userService.delete(userId);
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
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Fetch users by username"), @ApiResponse(code = 404,
                                                                                                    message = "In case of no User object was found in DB for given username")})
    @ApiOperation(value = "Fetch users by username",
                  notes = "Searches for all <b>User</b> objects in DB "
                          + "Returns list of User objects for given username.")
    @RequestMapping(value = "/get-by-username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getByName(@PathVariable(value="username") final String username) {

        LOG.info("User get-by-name request received: name = " + username);
        return ParseTools.parseToJson(userService.findByUsername(username), User.class);
    }

    @RequestMapping(value = "/get-roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all roles", notes = "Fetches all roles from DB ", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Fetch all roles")})
    @ResponseBody
    public String getRoles() {

        LOG.info("Fetch all Roles request received");

        return ParseTools.parseToJson(roleService.findAll(), Role.class);
    }
}
