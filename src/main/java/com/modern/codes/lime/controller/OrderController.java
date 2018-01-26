package com.modern.codes.lime.controller;

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.order.Order;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.SupplierPOJO;
import com.modern.codes.lime.service.IResourceService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/order")
public class OrderController {

    @Autowired
    IResourceService resourceService;

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/get-resources", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Fetches all resources", notes = "Fetches all resources from DB ", response = List.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Fetch all resources")})
    @ResponseBody
    public String getResources() {
        LOG.info("Fetch all resources request received");
        ParseTools.parseToJson(resourceService.findAll(), Resource.class);
        return ParseTools.parseToJson(resourceService.findAll(), Resource.class);
    }


    @RequestMapping(value = "/send", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Order choosen resources with given amount", notes = "Post choosen List of resource ID "
                                                              +  "Saves it into DB.", response = Boolean.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Resources orderer"),
                            @ApiResponse(code = 422, message = "In case of validation errors")})
    @ResponseBody
    public Boolean send(
            @Validated @RequestBody @ApiParam(value = "Map of Resources and amout") final Map<String, Integer> orderList,
            final BindingResult bindingResult, UriComponentsBuilder b) {
        LOG.info("order request received map resource:amount \n {} ", orderList);
        final Map<SupplierPOJO, Map<ResourcePOJO, Integer>> supplierMap = new HashMap<>();
        orderList.forEach((key, value) -> {
            final ResourcePOJO resource = resourceService.findById(key);
            if(resource.getSupplier() != null){
                Map<ResourcePOJO, Integer> suppVal = supplierMap.get(resource.getPOJOSupplier());
                if(null != suppVal){
                    suppVal.put(resource,value);
                    //supplierMap.put(resource.getPOJOSupplier(), suppVal);
                }
                else{
                    suppVal = new HashMap<>();
                    suppVal.put(resource, value);
                    supplierMap.put(resource.getPOJOSupplier(), suppVal);
                }
            }
        });
          supplierMap.forEach((key, value) -> Order.SendEmail(key.getEmailAddress(), "Order from LIME", Order.ConstructOrderMsg(key.getName(), value)));
        return true;
    }
}
