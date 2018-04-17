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
public class IReportServiceTest {

    @Before
    public void ResetDB() {
        pop.populate();
    }


    @Test
    public void generateReportTest(){
        final String givenDate = "14-01-2018";
        final int givenDays = 5;
        final String givenChart = "Bar";
        final List<String> givenIds = new ArrayList<String>();
        ProductPOJO p1 = productService.findByName("Donut").get(0);
        ProductPOJO p2 = productService.findByName("Cherry Cake").get(0);
        givenIds.add(p1.getId());
        givenIds.add(p2.getId());


        //Create Byte from Products Series
        final byte[] resultByte = service.getReportBytes(givenDate,givenDays,givenChart,givenIds);

        assertEquals(15088,resultByte.length);
    }

    @Test
    public void extractResourceTest(){
        final String givenDate = "14-01-2018";
        final int givenDays = 5;
        final String givenChart = "Line";
        final List<String> givenIds = new ArrayList<String>();
        ResourcePOJO r1 = resourceService.findByName("EGG").get(0);
        ResourcePOJO r2 = resourceService.findByName("Cherry").get(0);
        givenIds.add(r1.getId());
        givenIds.add(r2.getId());



        //Create Byte from Resource Series
        final byte[] resultByte = service.getReportBytesResource(givenDate,givenDays,givenChart,givenIds);
        assertEquals(15788,resultByte.length);

    }


    @Autowired
    DBPopulator pop;
    @Autowired
    private IReportService service;
    @Autowired
    private ProductService productService;
    @Autowired
    private ResourceService resourceService;
}
