package com.modern.codes.lime.controller;


import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.service.ProductService;
import io.swagger.annotations.Api;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a product object", notes = "Creates a <b>product</b> object "
            +  "Saves it into DB.", response = Product.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved product object"),
            @ApiResponse(code = 422, message = "In case of validation errors of product object")})
    @ResponseBody
    public ProductPOJO create(
            @RequestParam(value = "Product object") @RequestBody final @Validated ProductPOJO product,
            BindingResult bindingResult, UriComponentsBuilder b) {

        LOG.info("Location creation request received: {}", product);

        if (product == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid product creation request, form data contains %s error(s).",
                    bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        productService.save(product);
        return product;
    }

    @RequestMapping(value = "/get-by-name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch products by name", notes = "Searches for all <b>product</b> objects in DB "
            +  "Returns list of product objects for given name.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved product object"),
            @ApiResponse(code = 404, message = "In case of no product object was found in DB for given name")})

    public @ResponseBody List<ProductPOJO> getByName(@RequestParam(value="name") String name) {

        LOG.info("Product get-by-name request received: name = " + name);
        return productService.findByName(name);
    }


} 