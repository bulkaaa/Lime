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
import com.modern.codes.lime.model.ProductCategory;
import com.modern.codes.lime.pojo.ProductCategoryPOJO;
import com.modern.codes.lime.service.IProductCategoryService;
import com.modern.codes.lime.service.IProductService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The type Product category controller.
 */
@RestController
@RequestMapping("/product-category")
public class ProductCategoryController extends BaseController {

    /**
     * Instantiates a new Product category controller.
     *
     * @param productCategoryService the product category service
     * @param productService         the product service
     */
    public ProductCategoryController(final IProductCategoryService productCategoryService,
                                     final IProductService productService) {
        this.productCategoryService = productCategoryService;
        this.productService = productService;
    }

    /**
     * Create string.
     *
     * @param iProductCategory the product category
     * @param bindingResult    the binding result
     * @param b                the b
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a product category object",
                  notes = "Creates a <b>product category</b> object " + "Saves it into DB.",
                  response = ProductCategory.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved product category object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of product category object")})
    @ResponseBody
    public String create(@Validated @RequestBody @ApiParam("Product object") final ProductCategory iProductCategory,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Product Category creation request received: {}", iProductCategory);

        if (iProductCategory == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid product category creation request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }
        return ParseTools.parseToJson(iProductCategory, ProductCategory.class);
    }

    /**
     * Update string.
     *
     * @param iProductCategory the product category
     * @param bindingResult    the binding result
     * @param b                the b
     * @return the string
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a product category category object",
                  notes = "Updates a <b>product category</b> object ",
                  response = ProductCategory.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved product category object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of product category object")})
    @ResponseBody
    public String update(
            @Validated @RequestBody @ApiParam("Product categoryobject") final ProductCategory iProductCategory,
            final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Product category update request received: {}", iProductCategory);
        if (iProductCategory == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid product category update request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }

        return ParseTools.parseToJson(productCategoryService.save(iProductCategory), ProductCategory.class);
    }

    /**
     * Gets product category.
     *
     * @param productCategoryId the product category id
     * @return the product category
     */
    @RequestMapping(value = "/one/{productCategoryId}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a product category object",
                  notes = "Fetches a <b>product category</b> object by id ",
                  response = ProductCategoryPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved product category object"))
    @ResponseBody
    public String getProductCategory(
            @ApiParam("Product categoty object") @PathVariable final String productCategoryId) {

        LOG.info("Product category fetch request received for id: " + productCategoryId);
        return ParseTools.parseToJson(productCategoryService.findById(productCategoryId), ProductCategory.class);
    }

    /**
     * Delete boolean.
     *
     * @param productCategoryId the product category id
     * @return the boolean
     */
    @RequestMapping(value = "/delete/{productCategoryId}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a product category object",
                  notes = "Deletes a <b>product category</b> object ",
                  response = ProductCategoryPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved product category object"))
    @ResponseBody
    public Boolean delete(@ApiParam("Product category object") @PathVariable final String productCategoryId) {

        LOG.info("Product category deletion request received for id: " + productCategoryId);
        
        if (!productService.findByCategoryId(productCategoryId)
                          .isEmpty()) {
            return false;
        }
        productCategoryService.delete(productCategoryId);
        return true;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all product categories",
                  notes = "Fetches all product categories from DB ",
                  response = List.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Fetch all product categories"))
    @ResponseBody
    public String getAll() {
        LOG.info("Fetch all product categories request received");

        return ParseTools.parseToJson(productCategoryService.findAll(), ProductCategory.class);
    }

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    @ResponseBody
    @ApiResponses({@ApiResponse(code = 200, message = "Saved product category object"), @ApiResponse(code = 404,
                                                                                                     message = "In case of no product category object was found in DB for given name")})
    @ApiOperation(value = "Fetch products category by name",
                  notes = "Searches for all <b>product category</b> objects in DB "
                          + "Returns list of product category objects for given name.")
    @RequestMapping(value = "/get-by-name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getByName(@RequestParam("name") final String name) {

        LOG.info("Product category get-by-name request received: name = " + name);
        return ParseTools.parseToJson(productCategoryService.findByName(name), ProductCategory.class);
    }

    private final IProductCategoryService productCategoryService;
    private final IProductService productService;
    private static final Logger LOG = LoggerFactory.getLogger(ProductCategoryController.class);
}