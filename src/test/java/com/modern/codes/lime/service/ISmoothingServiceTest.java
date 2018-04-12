// package com.modern.codes.lime.service;
//
// import static org.junit.Assert.assertEquals;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.test.context.junit4.SpringRunner;
//
// import com.modern.codes.lime.class_models.TimeSeries;
//
// @DataJpaTest
// @RunWith(SpringRunner.class)
// @ComponentScan(basePackages = {"com.modern.codes.lime.service"})
// public class ISmoothingServiceTest {
//
//     @Test
//     public void calculateSmoothingPositiveTest(){
//         //given
//         final TimeSeries givenTimeSeries = new TimeSeries();
//
//         //Dodaj co potrzebujesz do tego obiektu givenTimeSeries.
//
//         //when
//         final TimeSeries resultTimeSeries = service.calculateSmoothing(givenTimeSeries, 5);
//
//         //then
//
//         // porównaj wszystkie komponenty które miały się zmienić za pomocą tej metody.
//
//         assertEquals(2, resultTimeSeries.size());
//     }
//
//     @Autowired
//     private ISmoothingService service;
// }
