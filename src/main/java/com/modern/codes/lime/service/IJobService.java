package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;
import com.modern.codes.lime.pojo.UserPOJO;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IJobService {
    List<JobPOJO> findAll();
    void delete(String id);
    JobPOJO save(Object t);
    boolean exists(String id);
    boolean exists(Object t);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    JobPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);

    List<JobPOJO> findByStartDateBetween(Date begin, Date end);
    List<JobPOJO> findByEndDateBetween(Date begin, Date end);
    List<JobPOJO> findByUserId(String id);
    List<JobPOJO> findByProductId(String id);



    static List<JobPOJO> filterByUser(final List<JobPOJO> list, final String userName){
        return filterByUser(list.stream(), userName).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByUser(final Stream<JobPOJO> stream, final String userName){
        return stream.filter(t -> t.getPOJOUser().getName().equals(userName));
    }
    static List<JobPOJO> filterByUser(final List<JobPOJO> list, final UserPOJO user){
        return filterByUser(list.stream(), user).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByUser(final Stream<JobPOJO> stream, final UserPOJO user){
        return stream.filter(t -> t.getPOJOUser().equals(user));
    }

    static List<JobPOJO> filterByProduct(final List<JobPOJO> list, final String productName){
        return filterByProduct(list.stream(), productName).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByProduct(final Stream<JobPOJO> stream, final String productName){
        return stream.filter(t -> t.getPOJOProduct().getName().equals(productName));
    }
    static List<JobPOJO> filterByProduct(final List<JobPOJO> list, final ProductPOJO product){
        return filterByProduct(list.stream(), product).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByProduct(final Stream<JobPOJO> stream, final ProductPOJO product){
        return stream.filter(t -> t.getPOJOProduct().equals(product));
    }
    static List<JobPOJO> filterByDateBetween(final List<JobPOJO> list, final Date startDate, final Date endDate){
        return filterByDateBetween(list.stream(), startDate, endDate).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByDateBetween(final Stream<JobPOJO> stream, final Date startDate, final Date endDate){
        return stream.filter(t -> t.getEndDate().before(endDate) && t.getEndDate().after(startDate));
    }

}
