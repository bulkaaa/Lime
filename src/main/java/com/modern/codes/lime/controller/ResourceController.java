package com.modern.codes.lime.controller;

import java.util.List;
import java.util.Locale;

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

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.service.IResourceService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The type Resource controller.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    /**
     * Instantiates a new Resource controller.
     *
     * @param resourceService the resource service
     */
    public ResourceController(final IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Create string.
     *
     * @param resource      the resource
     * @param bindingResult the binding result
     * @param b             the b
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a resource object",
                  notes = "Creates a <b>resource</b> object " + "Saves it into DB.",
                  response = Resource.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved resource object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of resource object")})
    @ResponseBody
    public String create(@Validated @RequestBody @ApiParam("Resource object") final Resource resource,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Resource creation request received: {}", resource);

        if (resource == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid resource creation request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }

        return ParseTools.parseToJson(resourceService.save(resource), Resource.class);
    }

    /**
     * Update string.
     *
     * @param resource      the resource
     * @param bindingResult the binding result
     * @param b             the b
     * @return the string
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a resource object",
                  notes = "Updates a <b>resource</b> object ",
                  response = Resource.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved resource object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of resource object")})
    @ResponseBody
    public String update(@Validated @RequestBody @ApiParam("Resource object") final Resource resource,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Resource update request received: {}", resource);

        if (resource == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid resource update request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }

        return ParseTools.parseToJson(resourceService.save(resource), Resource.class);
    }

    /**
     * Delete boolean.
     *
     * @param resourceId the resource id
     * @return the boolean
     */
    @RequestMapping(value = "/delete/{resourceId}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a resource object",
                  notes = "Deletes a <b>resource</b> object ",
                  response = ResourcePOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved resource object"))
    @ResponseBody
    public Boolean delete(@ApiParam("Resource object") @PathVariable final String resourceId) {

        LOG.info("Resource deletion request received for id: " + resourceId);
        resourceService.delete(resourceId);
        return true;
    }

    /**
     * Gets resource.
     *
     * @param resourceId the resource id
     * @return the resource
     */
    @RequestMapping(value = "/one/{resourceId}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a resource object",
                  notes = "Fetches a <b>resource</b> object by id ",
                  response = ResourcePOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved resource object"))
    @ResponseBody
    public String getResource(@ApiParam("Resource object") @PathVariable final String resourceId) {

        LOG.info("Resource fetch request received for id: " + resourceId);
        return ParseTools.parseToJson(resourceService.findById(resourceId), Resource.class);
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all resources", notes = "Fetches all resources from DB ", response = List.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Fetch all resources"))
    @ResponseBody
    public String getAll() {

        LOG.info("Fetch all resources request received");

        return ParseTools.parseToJson(resourceService.findAll(), Resource.class);
    }

    /**
     * Toggle notification.
     *
     * @param toggle the toggle
     */
    @RequestMapping(value = "/toggle-notification/{toggle}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all resources", notes = "Fetches all resources from DB ", response = List.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Fetch all resources"))
    @ResponseBody
    public void toggleNotification(@ApiParam("Resource object") @PathVariable final boolean toggle) {

        final List<ResourcePOJO> resources = resourceService.findAll();
        resources.forEach(res -> res.setNotifications_on(toggle));
        resourceService.save(resources);
    }

    /**
     * Toggle order.
     *
     * @param toggle the toggle
     */
    @RequestMapping(value = "/toggle-order/{toggle}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all resources", notes = "Fetches all resources from DB ", response = List.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Fetch all resources"))
    @ResponseBody
    public void toggleOrder(@ApiParam("Resource object") @PathVariable final boolean toggle) {

        final List<ResourcePOJO> resources = resourceService.findAll();
        resources.forEach(res -> res.setOrdering_on(toggle));
        resourceService.save(resources);
    }

    private final IResourceService resourceService;

    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);
}