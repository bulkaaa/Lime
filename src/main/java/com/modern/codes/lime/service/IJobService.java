package com.modern.codes.lime.service;

import com.modern.codes.lime.model.User;
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
    List<JobPOJO> findByUser(User user);

    static List<JobPOJO> filterByUser(List<JobPOJO> list, String userName){
        return filterByUser(list.stream(), userName).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByUser(Stream<JobPOJO> stream, String userName){
        return stream.filter(t -> t.getPOJOUser().getName().equals(userName));
    }
    static List<JobPOJO> filterByUser(List<JobPOJO> list, UserPOJO user){
        return filterByUser(list.stream(), user).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByUser(Stream<JobPOJO> stream, UserPOJO user){
        return stream.filter(t -> t.getPOJOUser().equals(user));
    }

    static List<JobPOJO> filterByProduct(List<JobPOJO> list, String productName){
        return filterByProduct(list.stream(), productName).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByProduct(Stream<JobPOJO> stream, String productName){
        return stream.filter(t -> t.getPOJOProduct().getName().equals(productName));
    }
    static List<JobPOJO> filterByProduct(List<JobPOJO> list, ProductPOJO product){
        return filterByProduct(list.stream(), product).collect(Collectors.toList());
    }
    static Stream<JobPOJO> filterByProduct(Stream<JobPOJO> stream, ProductPOJO product){
        return stream.filter(t -> t.getPOJOProduct().equals(product));
    }
}
