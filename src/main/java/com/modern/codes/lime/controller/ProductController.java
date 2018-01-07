package com.modern.codes.lime.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.service.ProductService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/product")
public class ProductController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a product object", notes = "Creates a <b>product</b> object "
            +  "Saves it into DB.", response = Product.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved product object"),
            @ApiResponse(code = 422, message = "In case of validation errors of product object")})
    @ResponseBody
    public String create(
            @ApiParam(value = "Product object") @RequestBody final @Validated Product product,
            BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("Product creation request received: {}", product);

        if (product == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid product creation request, form data contains %s error(s).",
                    bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        return ParseTools.parseToJson(productService.save(product), Product.class);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a product object", notes = "Updates a <b>product</b> object ", response = Product.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved product object"),
            @ApiResponse(code = 422, message = "In case of validation errors of product object")})
    @ResponseBody
    public String update(
            @ApiParam(value = "Product object") @RequestBody final @Validated Product product,
            BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("Product update request received: {}", product);

        if (product == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid product update request, form data contains %s error(s).",
                    bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        return ParseTools.parseToJson(productService.save(product), Product.class);
    }
    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a product object", notes = "Deletes a <b>product</b> object ", response = ProductPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved product object")})
    @ResponseBody
    public Boolean delete(
            @ApiParam(value = "Product object") @PathVariable final String productId) {

        LOG.info("Product deletion request received for id: " + productId);


        productService.delete(productId);
        return true;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all products", notes = "Fetches all products from DB ", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Fetch all products")})
    @ResponseBody
    public String getAll() {

        LOG.info("Fetch all product request received");

        ParseTools.parseToJson(productService.findAll(), Product.class);
        return ParseTools.parseToJson(productService.findAll(), Product.class);
    }


    @RequestMapping(value = "/get-by-name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch products by name", notes = "Searches for all <b>product</b> objects in DB "
            +  "Returns list of product objects for given name.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved product object"),
            @ApiResponse(code = 404, message = "In case of no product object was found in DB for given name")})

    public @ResponseBody String getByName(@RequestParam(value="name") String name) {

        LOG.info("Product get-by-name request received: name = " + name);
        ParseTools.parseToJson(productService.findByName(name), Product.class);
        return ParseTools.parseToJson(productService.findByName(name), Product.class);
    }



} 