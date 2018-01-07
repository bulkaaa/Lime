package com.modern.codes.lime.controller;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.service.ResourceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/resource")
public class ResourceController extends BaseController{

    private static final Logger LOG = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    ResourceService resourceService;


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a resource object", notes = "Creates a <b>resource</b> object "
            +  "Saves it into DB.", response = Resource.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved resource object"),
            @ApiResponse(code = 422, message = "In case of validation errors of resource object")})
    @ResponseBody
    public Resource create(
            @ApiParam(value = "Resource object") @RequestBody final @Validated Resource resource,
            BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("Resource creation request received: {}", resource);

        if (resource == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid resource creation request, form data contains %s error(s).",
                    bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        resourceService.save(resource);
        return resource;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a resource object", notes = "Updates a <b>resource</b> object ", response = Resource.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved resource object"),
            @ApiResponse(code = 422, message = "In case of validation errors of resource object")})
    @ResponseBody
    public Resource update(
            @ApiParam(value = "Resource object") @RequestBody final @Validated Resource resource,
            BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("Resource update request received: {}", resource);

        if (resource == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid resource update request, form data contains %s error(s).",
                    bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        resourceService.save(resource);
        return resource;
    }

    @RequestMapping(value = "/delete/{resourceId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a resource object", notes = "Deletes a <b>resource</b> object ", response = ResourcePOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved resource object")})
    @ResponseBody
    public Boolean delete(
            @ApiParam(value = "Resource object") @PathVariable final String resourceId) {

        LOG.info("Resource deletion request received for id: " + resourceId);

            resourceService.delete(resourceId);
            return true;
    }

    @RequestMapping(value = "/{resourceId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a resource object", notes = "Fetches a <b>resource</b> object by id ", response = ResourcePOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved resource object")})
    @ResponseBody
    public ResourcePOJO getResource(
            @ApiParam(value = "Resource object") @PathVariable final String resourceId) {

        LOG.info("Resource fetch request received for id: " + resourceId);

        return resourceService.findById(resourceId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all resources", notes = "Fetches all resources from DB ", response = List.class)
            @ApiResponses(value = { @ApiResponse(code = 200, message = "Fetch all resources")})
    @ResponseBody
    public List<ResourcePOJO> getAll() {

        LOG.info("Fetch all resources request received");

        return resourceService.findAll();
    }


}