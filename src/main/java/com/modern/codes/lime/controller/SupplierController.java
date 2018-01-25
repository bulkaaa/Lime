package com.modern.codes.lime.controller;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.SupplierPOJO;
import com.modern.codes.lime.service.IResourceService;
import com.modern.codes.lime.service.ISupplierService;
import com.modern.codes.lime.service.SupplierService;
import com.modern.codes.lime.tools.ParseTools;

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
@RequestMapping(value = "/supplier")
public class SupplierController extends BaseController{
    private static final Logger LOG = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    ISupplierService supplierService;
    @Autowired
    IResourceService resourceService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a supplier object", notes = "Creates a <b>supplier</b> object "
            +  "Saves it into DB.", response = SupplierPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved supplier object"),
            @ApiResponse(code = 422, message = "In case of validation errors of supplier object")})
    @ResponseBody
    public String create(
            @ApiParam(value = "Supplier object") @RequestBody final @Validated SupplierPOJO supplier,
            BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("Supplier creation request received: {}", supplier);

        if (supplier == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid supplier creation request, form data contains %s error(s).",
                    bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        supplierService.save(supplier);
        return ParseTools.parseToJson(supplier, Supplier.class);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a supplier object", notes = "Updates a <b>supplier</b> object ", response = SupplierPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved supplier object"),
            @ApiResponse(code = 422, message = "In case of validation errors of supplier object")})
    @ResponseBody
    public String update(
            @ApiParam(value = "Supplier object") @RequestBody final @Validated SupplierPOJO supplier,
            BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("Supplier update request received: {}", supplier);

        if (supplier == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid supplier update request, form data contains %s error(s).",
                    bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        supplierService.save(supplier);
        return ParseTools.parseToJson(supplier, Supplier.class);
    }

    @RequestMapping(value = "/delete/{supplierId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a supplier object", notes = "Deletes a <b>supplier</b> object ", response = SupplierPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved supplier object")})
    @ResponseBody
    public void delete(
            @ApiParam(value = "Supplier object") @PathVariable final String supplierId) {

        LOG.info("Supplier deletion request received for id: " + supplierId);
        final List<ResourcePOJO> resources = resourceService.findBySupplierId(supplierId);
        resources.forEach(r -> r.setSupplier(null));
        resourceService.save(resources);
        supplierService.delete(supplierId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all suppliers", notes = "Fetches all suppliers from DB ", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Fetch all suppliers")})
    @ResponseBody
    public String getAll() {

        LOG.info("Fetch all supplier request received");

        return ParseTools.parseToJson(supplierService.findAll(), Supplier.class);
    }

}
