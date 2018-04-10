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
import com.modern.codes.lime.model.ResourceCategory;
import com.modern.codes.lime.pojo.ResourceCategoryPOJO;
import com.modern.codes.lime.service.IResourceCategoryService;
import com.modern.codes.lime.service.IResourceService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The type Resource category controller.
 */
@RestController
@RequestMapping("/resource-category")
public class ResourceCategoryController extends BaseController {

    /**
     * Instantiates a new Resource category controller.
     *
     * @param resourceCategoryService the resource category service
     * @param resourceService         the resource service
     */
    public ResourceCategoryController(final IResourceCategoryService resourceCategoryService,
                                      final IResourceService resourceService) {
        this.resourceCategoryService = resourceCategoryService;
        this.resourceService = resourceService;
    }

    /**
     * Create string.
     *
     * @param iResourceCategory the Resource category
     * @param bindingResult     the binding result
     * @param b                 the b
     * @return the string
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a Resource category object",
                  notes = "Creates a <b>Resource category</b> object " + "Saves it into DB.",
                  response = ResourceCategory.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved Resource category object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of Resource category object")})
    @ResponseBody
    public String create(@Validated @RequestBody @ApiParam("Resource object") final ResourceCategory iResourceCategory,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Resource Category creation request received: {}", iResourceCategory);

        if (iResourceCategory == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid Resource category creation request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }
        return ParseTools.parseToJson(iResourceCategory, ResourceCategory.class);
    }

    /**
     * Update string.
     *
     * @param iResourceCategory the Resource category
     * @param bindingResult     the binding result
     * @param b                 the b
     * @return the string
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a Resource category  object",
                  notes = "Updates a <b>Resource category</b> object ",
                  response = ResourceCategory.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved Resource category object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of Resource category object")})
    @ResponseBody
    public String update(
            @Validated @RequestBody @ApiParam("Resource category object") final ResourceCategory iResourceCategory,
            final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Resource category update request received: {}", iResourceCategory);
        if (iResourceCategory == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid Resource category update request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }

        return ParseTools.parseToJson(resourceCategoryService.save(iResourceCategory), ResourceCategory.class);
    }

    /**
     * Gets Resource category.
     *
     * @param resourceCategoryId the resource category id
     * @return the Resource category
     */
    @RequestMapping(value = "/one/{resourceCategoryId}",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a Resource category object",
                  notes = "Fetches a <b>Resource category</b> object by id ",
                  response = ResourceCategoryPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved Resource category object"))
    @ResponseBody
    public String getResourceCategory(
            @ApiParam("Resource categoty object") @PathVariable final String resourceCategoryId) {

        LOG.info("Resource category fetch request received for id: " + resourceCategoryId);
        return ParseTools.parseToJson(resourceCategoryService.findById(resourceCategoryId), ResourceCategory.class);
    }

    /**
     * Delete boolean.
     *
     * @param resourceCategoryId the resource category id
     * @return the boolean
     */
    @RequestMapping(value = "/delete/{resourceCategoryId}",
                    method = RequestMethod.DELETE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Delete a Resource category object",
                  notes = "Deletes a <b>Resource category</b> object ",
                  response = ResourceCategoryPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved Resource category object"))
    @ResponseBody
    public Boolean delete(@ApiParam("Resource category object") @PathVariable final String resourceCategoryId) {

        LOG.info("Resource category deletion request received for id: " + resourceCategoryId);

        // KLAUDIA CO ZWRÓCIć KIEDY NIE MOŻEMY USUNĄć BO NIEKTÓRE RESOURCEC MAJĄ TE KATEGORIE, RESOURCE NIE MOŻE
        // ZOSTAć BEZ KATEGORII,
        // NA FRONCIE MUSI POJAWIć SIĘ INFORMACJA ŻE UŻYTKOWNIK MUSI NAJPIERW ZMIENIć KATEGORIĘ RESOURCE ABY USUNĄć
        // TĘ KATEGORIĘ.
        if (resourceService.findByCategoryId(resourceCategoryId)
                           .isEmpty()) {
            return false;
        }
        resourceCategoryService.delete(resourceCategoryId);
        return true;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all Resource categories",
                  notes = "Fetches all Resource categories from DB ",
                  response = List.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Fetch all Resource categories"))
    @ResponseBody
    public String getAll() {
        LOG.info("Fetch all Resource categories request received");

        return ParseTools.parseToJson(resourceCategoryService.findAll(), ResourceCategory.class);
    }

    /**
     * Gets by name.
     *
     * @param name the name
     * @return the by name
     */
    @ResponseBody
    @ApiResponses({@ApiResponse(code = 200, message = "Saved Resource category object"), @ApiResponse(code = 404,
                                                                                                      message = "In case of no Resource category object was found in DB for given name")})
    @ApiOperation(value = "Fetch Resources category by name",
                  notes = "Searches for all <b>Resource category</b> objects in DB "
                          + "Returns list of Resource category objects for given name.")
    @RequestMapping(value = "/get-by-name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getByName(@RequestParam("name") final String name) {

        LOG.info("Resource category get-by-name request received: name = " + name);
        return ParseTools.parseToJson(resourceCategoryService.findByName(name), ResourceCategory.class);
    }

    private final IResourceCategoryService resourceCategoryService;
    private final IResourceService resourceService;
    private static final Logger LOG = LoggerFactory.getLogger(ResourceCategoryController.class);
}