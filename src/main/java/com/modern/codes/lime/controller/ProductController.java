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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.service.IProductService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    /**
     * Instantiates a new Product controller.
     *
     * @param productService the product service
     */
    public ProductController(final IProductService productService) {
        this.productService = productService;
    }

    /**
     * Create string.
     *
     * @param iProduct      the product
     * @param bindingResult the binding result
     * @param b             the b
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a product object",
                  notes = "Creates a <b>product</b> object " + "Saves it into DB.",
                  response = Product.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved product object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of product object")})
    @ResponseBody
    public String create(@Validated @RequestBody @ApiParam("Product object") final Product iProduct,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Product creation request received: {}", iProduct);

        if (iProduct == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid product creation request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }

        final ProductPOJO product = productService.save(iProduct);
        return ParseTools.parseToJson(product, Product.class);
    }

    /**
     * Update string.
     *
     * @param iProduct      the product
     * @param bindingResult the binding result
     * @param b             the b
     * @return the string
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a product object",
                  notes = "Updates a <b>product</b> object ",
                  response = Product.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved product object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of product object")})
    @ResponseBody
    public String update(@Validated @RequestBody @ApiParam("Product object") final Product iProduct,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Product update request received: {}", iProduct);

        if (iProduct == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid product update request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }
        final ProductPOJO product = productService.save(iProduct);
        return ParseTools.parseToJson(productService.save(product), Product.class);
    }

    /**
     * Gets product.
     *
     * @param productId the product id
     * @return the product
     */
    @RequestMapping(value = "/one/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a product object",
                  notes = "Fetches a <b>product</b> object by id ",
                  response = ProductPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved product object"))
    @ResponseBody
    public String getProduct(@ApiParam("Product object") @PathVariable final String productId) {

        LOG.info("Product fetch request received for id: " + productId);
        return ParseTools.parseToJson(productService.findById(productId), Product.class);
    }

    /**
     * Delete boolean.
     *
     * @param productId the product id
     * @return the boolean
     */
    @RequestMapping(value = "/delete/{productId}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a product object",
                  notes = "Deletes a <b>product</b> object ",
                  response = ProductPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved product object"))
    @ResponseBody
    public Boolean delete(@ApiParam("Product object") @PathVariable final String productId) {

        LOG.info("Product deletion request received for id: " + productId);
        productService.delete(productId);
        return true;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all products", notes = "Fetches all products from DB ", response = List.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Fetch all products"))
    @ResponseBody
    public String getAll() {
        LOG.info("Fetch all product request received");

        return ParseTools.parseToJson(productService.findAll(), Product.class);
    }

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    @ResponseBody
    @ApiResponses({@ApiResponse(code = 200, message = "Saved product object"),
                   @ApiResponse(code = 404, message = "In case of no product object was found in DB for given name")})
    @ApiOperation(value = "Fetch products by name",
                  notes = "Searches for all <b>product</b> objects in DB "
                          + "Returns list of product objects for given name.")
    @RequestMapping(value = "/get-by-name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getByName(@RequestParam("name") final String name) {

        LOG.info("Product get-by-name request received: name = " + name);
        return ParseTools.parseToJson(productService.findByName(name), Product.class);
    }

    private final IProductService productService;
    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
} 