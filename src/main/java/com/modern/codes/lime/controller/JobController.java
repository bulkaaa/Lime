package com.modern.codes.lime.controller;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.modern.codes.lime.exception.InvalidRequestException;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.service.IFormulaService;
import com.modern.codes.lime.service.IJobService;
import com.modern.codes.lime.service.IResourceService;
import com.modern.codes.lime.tools.ParseTools;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/job")
public class JobController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(JobController.class);

    @Autowired
    IJobService jobService;

    @Autowired
    IFormulaService formulaService;

    @Autowired
    IResourceService resourceService;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Creates a job object", notes = "Creates a <b>job</b> object "
                                                              +  "Saves it into DB.", response = Job.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved job object"),
                            @ApiResponse(code = 422, message = "In case of validation errors of job object")})
    @ResponseBody
    public String create(
            @Validated @RequestBody @ApiParam(value = "job object") final Job job,
            final BindingResult bindingResult, final UriComponentsBuilder b) {

        LOG.info("job creation request received: {}", job);

        if (job == null || bindingResult.hasErrors())
            throw new InvalidRequestException(String.format("Invalid job creation request, form data contains %s error(s).",
                                                            bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);

        try {
            final String response = ParseTools.parseToJson(jobService.save(job), Job.class);
            formulaService.findByProductId(job.getProduct()
                                              .getId()).forEach(f -> {
                                                  final ResourcePOJO resource = f.getPOJOResource();
                                                  // MIEJSCE DLA SPRAWDZENIE DLA NOTYFIKACJI!!!
                                                  resource.setQuantity(resource.getQuantity() - f.getValue());
                                                  resourceService.save(resource);
            });
            return response;
        }
        catch (final Exception e)
        {
            throw new InvalidRequestException(String.format("Invalid job creation request, form data contains %s error(s).",
                                                            bindingResult.getErrorCount()), bindingResult, Locale.ENGLISH);
        }
    }


    @RequestMapping(value = "/one/{jobId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetch a job object", notes = "Fetches a <b>job</b> object by id ", response = JobPOJO.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Saved job object")})
    @ResponseBody
    public String getProduct(
            @ApiParam(value = "job object") @PathVariable final String jobId) {

        LOG.info("job fetch request received for id: " + jobId);
        return ParseTools.parseToJson(jobService.findById(jobId), Job.class);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all products", notes = "Fetches all products from DB ", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Fetch all products")})
    @ResponseBody
    public String getAll() {

        LOG.info("Fetch all job request received");
        return ParseTools.parseToJson(jobService.findAll(), Job.class);
    }

    @ResponseBody
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Saved job object"), @ApiResponse(code = 404,
                                                                                                    message = "In case of no job object was found in DB for given name")})
    @ApiOperation(value = "Fetch jobs by userId",
                  notes = "Searches for all <b>jobs</b> objects in DB "
                          + "Returns list of jobs objects for given userId.")
    @RequestMapping(value = "/get-by-userId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getByName(@RequestParam(value="userId") final String userId) {

        LOG.info("Jobs get-by-userId request received: userId = " + userId);
        return ParseTools.parseToJson(jobService.findByUserId(userId), Job.class);
    }
}