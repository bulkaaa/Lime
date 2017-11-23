package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.JobPOJO;
import com.modern.codes.lime.pojo.ProductPOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IProductService {
    List<ProductPOJO> findAll();
    void delete(String id);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    ProductPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
    static List<ProductPOJO> filterByName(List<ProductPOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<ProductPOJO> filterByName(Stream<ProductPOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
    static List<ProductPOJO> filterByCategory(List<ProductPOJO> list, String category){
        return filterByCategory(list.stream(), category).collect(Collectors.toList());
    }
    static Stream<ProductPOJO> filterByCategory(Stream<ProductPOJO> stream, String category){
        return stream.filter(t -> t.getCategory().equals(category));
    }
    static List<ProductPOJO> filterByJob(List<ProductPOJO> list, JobPOJO job){
        return filterByJob(list.stream(), job).collect(Collectors.toList());
    }
    static Stream<ProductPOJO> filterByJob(Stream<ProductPOJO> stream, JobPOJO job){
        return stream.filter(t -> t.getJobs().contains(job));
    }
}
