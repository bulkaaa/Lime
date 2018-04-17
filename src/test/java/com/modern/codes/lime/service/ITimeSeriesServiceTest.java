package com.modern.codes.lime.service;

import static org.junit.Assert.assertEquals;

import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.tools.DBPopulator;
import com.modern.codes.lime.tools.ParseTools;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.modern.codes.lime.class_models.TimeSeries;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.modern.codes.lime.tools"})
@ComponentScan(basePackages = {"com.modern.codes.lime.service"})
public class ITimeSeriesServiceTest {
    @Before
    public void ResetDB() {

        pop.clearDB();
        pop.populate();
    }


    @Test
    public void extractTest(){
        final Date givenDate = ParseTools.parseDate("2018-01-14 12:00:00");
        final int givenDays = 5;
        final List<String> givenIds = new ArrayList<String>();
        ProductPOJO p1 = productService.findByName("Donut").get(0);
        ProductPOJO p2 = productService.findByName("Cherry Cake").get(0);
        givenIds.add(p1.getId());
        givenIds.add(p2.getId());


        //Extract Time Series from Products
        final ArrayList<TimeSeries> resultTimeSeries = service.Extract(givenDate, givenDays, givenIds);

        //Check if proper number of Days was appended to time series
       for (final TimeSeries ts:resultTimeSeries) {
            assertEquals(givenDays, ts.size());
        }

     assertEquals(2,resultTimeSeries.size());
    }

    @Test
    public void extractResourceTest(){
        final Date givenDate = ParseTools.parseDate("2018-01-14 12:00:00");
        final int givenDays = 5;
        final List<String> givenIds = new ArrayList<String>();
        ResourcePOJO r1 = resourceService.findByName("EGG").get(0);
        ResourcePOJO r2 = resourceService.findByName("Cherry").get(0);
        givenIds.add(r1.getId());
        givenIds.add(r2.getId());


        //Extract Time Series from Resources
        final ArrayList<TimeSeries> resultTimeSeries = service.ExtractforResource(givenDate, givenDays, givenIds);

        //Check if proper number of Days was appended to time series
        for (final TimeSeries ts:resultTimeSeries) {
            assertEquals(givenDays, ts.size());
        }

        assertEquals(2,resultTimeSeries.size());
    }


    @Autowired
    DBPopulator pop;
    @Autowired
    private ITimeSeriesService service;
    @Autowired
    private ProductService productService;
    @Autowired
    private ResourceService resourceService;
}
