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
import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.pojo.FormulaPOJO;
import com.modern.codes.lime.service.IFormulaService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The type Formula controller.
 */
@RestController
@RequestMapping("/formula")
public class FormulaController extends BaseController {

    /**
     * Instantiates a new Formula controller.
     *
     * @param formulaService the formula service
     */
    public FormulaController(final IFormulaService formulaService) {
        this.formulaService = formulaService;
    }

    /**
     * Gets product.
     *
     * @param productId the product id
     * @return the product
     */
    @RequestMapping(value = "/one/{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a job object",
                  notes = "Fetches a <b>job</b> object by id ",
                  response = FormulaPOJO.class)
    @ApiResponses(@ApiResponse(code = 200, message = "Saved job object"))
    @ResponseBody
    public String getProduct(@ApiParam("job object") @PathVariable final String productId) {

        LOG.info("job fetch request received for id: " + productId);
        return ParseTools.parseToJson(formulaService.findByProductId(productId), Formula.class);
    }

    /**
     * Update string.
     *
     * @param iFormula      the formula
     * @param bindingResult the binding result
     * @param b             the b
     * @return the string
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Updates a formula object",
                  notes = "Updates a <b>formula</b> object ",
                  response = Formula.class)
    @ApiResponses({@ApiResponse(code = 200, message = "Saved formula object"),
                   @ApiResponse(code = 422, message = "In case of validation errors of formula object")})
    @ResponseBody
    public String update(@Validated @RequestBody @ApiParam("Formula object") final List<Formula> iFormula,
                         final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("Formula update request received: {}", iFormula);

        if (iFormula == null || bindingResult.hasErrors()) {
            throw new InvalidRequestException(
                    String.format("Invalid formula update request, form data contains %s error(s).",
                                  bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }
        formulaService.deleteByProductId(iFormula.get(0)
                                                 .getProduct()
                                                 .getId());
        return ParseTools.parseToJson(formulaService.save(iFormula), Formula.class);
    }

    private final IFormulaService formulaService;
    private static final Logger LOG = LoggerFactory.getLogger(FormulaController.class);
}