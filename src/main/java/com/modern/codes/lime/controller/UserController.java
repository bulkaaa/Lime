package com.modern.codes.lime.controller;

import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.modern.codes.lime.exception.AlreadyExistsException;
import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.Role;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.service.IMailService;
import com.modern.codes.lime.service.IRoleService;
import com.modern.codes.lime.service.IUserService;
import com.modern.codes.lime.service.MailService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     * @param roleService the role service
     * @param mailService the mail service
     */
    public UserController(final IUserService userService, final IRoleService roleService,
                          final IMailService mailService) {
        this.userService = userService;
        this.roleService = roleService;
        this.mailService = mailService;
    }

    /**
     * Create string.
     *
     * @param user          the user
     * @param bindingResult the binding result
     * @param b             the b
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a User object",
                  notes = "Creates a <b>User</b> object " + "Saves it into DB.",
                  response = User.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved User object"),
                   @ApiResponse(code = 409, message = "User given username or email already exists"),
                   @ApiResponse(code = 422, message = "In case of validation errors of User object")})
    @ResponseBody
    public String create(@Validated @RequestBody @ApiParam("User object") final User user,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("User creation request received: {}", user);

        if (user == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid User creation request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }

        try {
            final String userJson = ParseTools.parseToJson(userService.save(user), User.class);
            MailService.SendEmail(user.getEmailAddress(), "Welcome to LIME",
                                  mailService.prepareWelcomeEmailBody(user.getUsername(), user.getPassword(),
                                                                      user.getName(), user.getSurname()));
            return userJson;
        } catch (final MessagingException e) {
            LOG.error("FAILED TO SEND WELCOME MESSAGE", e);
            return null;
        } catch (final Exception e) {
            throw new AlreadyExistsException(
                    String.format("Invalid User creation request, username: %s or email: %s already registered.",
                                  user.getUsername(), user.getEmailAddress()));

        }
    }

    /**
     * Update string.
     *
     * @param user          the user
     * @param bindingResult the binding result
     * @param b             the b
     * @return the string
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a User object", notes = "Updates a <b>User</b> object ", response = User.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved User object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of User object")})
    @ResponseBody
    public String update(@Validated @RequestBody @ApiParam("User object") final User user,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("User update request received: {}", user);

        if (user == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid User update request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }
        try {
            return ParseTools.parseToJson(userService.save(user), User.class);
        } catch (final Exception e) {
            throw new AlreadyExistsException(
                    String.format("Invalid User creation request, username: %s or email: %s already registered.",
                                  user.getUsername(), user.getEmailAddress()));

        }
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    @RequestMapping(value = "/one/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a User object",
                  notes = "Fetches a <b>User</b> object by id ",
                  response = UserPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved User object"))
    @ResponseBody
    public String getUser(@ApiParam("User object") @PathVariable final String userId) {

        LOG.info("User fetch request received for id: " + userId);
        try {
            return ParseTools.parseToJson(userService.findById(userId), User.class);
        } catch (final Exception e) {
            return "NO SUCH ID IN DB";
        }
    }

    /**
     * Delete boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    @RequestMapping(value = "/delete/{userId}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a User object", notes = "Deletes a <b>User</b> object ", response = UserPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved User object"))
    @ResponseBody
    public Boolean delete(@ApiParam("User object") @PathVariable final String userId) {

        LOG.info("User deletion request received for id: " + userId);

        userService.delete(userId);
        return true;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all users", notes = "Fetches all users from DB ", response = List.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Fetch all users"))
    @ResponseBody
    public String getAll() {

        LOG.info("Fetch all User request received");

        return ParseTools.parseToJson(userService.findAll(), User.class);
    }

    /**
     * Gets by name.
     *
     * @param username the username
     * @return the by name
     */
    @ResponseBody
    @ApiResponses({@ApiResponse(code = 200, message = "Fetch users by username"),
                   @ApiResponse(code = 404, message = "In case of no User object was found in DB for given username")})
    @ApiOperation(value = "Fetch users by username",
                  notes = "Searches for all <b>User</b> objects in DB "
                          + "Returns list of User objects for given username.")
    @RequestMapping(value = "/get-by-username/{username}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public String getByName(@PathVariable("username") final String username) {

        LOG.info("User get-by-name request received: name = " + username);
        return ParseTools.parseToJson(userService.findByUsername(username), User.class);
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    @RequestMapping(value = "/get-roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all roles", notes = "Fetches all roles from DB ", response = List.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Fetch all roles"))
    @ResponseBody
    public String getRoles() {

        LOG.info("Fetch all Roles request received");

        return ParseTools.parseToJson(roleService.findAll(), Role.class);
    }

    private final IUserService userService;
    private final IRoleService roleService;
    private final IMailService mailService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

}
