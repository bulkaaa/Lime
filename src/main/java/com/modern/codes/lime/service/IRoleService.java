package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.RolePOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IRoleService {
    List<RolePOJO> findAll();
    void delete(String id);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    RolePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
    static List<RolePOJO> filterByName(List<RolePOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<RolePOJO> filterByName(Stream<RolePOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
}
