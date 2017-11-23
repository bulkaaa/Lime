package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.PrivilegePOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IPrivilegeService {
    List<PrivilegePOJO> findAll();
    void delete(String id);
    void save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    PrivilegePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
    static List<PrivilegePOJO> filterByName(List<PrivilegePOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<PrivilegePOJO> filterByName(Stream<PrivilegePOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
}
