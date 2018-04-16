package com.modern.codes.lime.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.modern.codes.lime.class_models.TimeSeries;

@DataJpaTest
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com.modern.codes.lime.service"})
public class ISmoothingServiceTest {

    @Test
    public void calculateSmoothingCountTest(){
        final TimeSeries givenTimeSeries = new TimeSeries(){};
        final int days = 5;
        //Creating sample series
        givenTimeSeries.add(1.0);
        givenTimeSeries.add(2.0);
        givenTimeSeries.add(3.0);
        givenTimeSeries.add(4.0);
        givenTimeSeries.add(5.0);

        //Calculating forecast by Exponential Smoothing
        final TimeSeries resultTimeSeries = service.calculateSmoothing(givenTimeSeries, days);

        //Check if proper number of data was appended to time series
        assertEquals(givenTimeSeries.size() + days, resultTimeSeries.size());
    }

    @Autowired
    private ISmoothingService service;
}
