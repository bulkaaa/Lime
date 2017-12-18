package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IRoleService {
    List<RolePOJO> findAll();
    void delete(String id);
    RolePOJO save(Object t);
    boolean exists(String id);
    boolean exists(Object t);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    RolePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
    List<RolePOJO> findByName(String name);

    static List<RolePOJO> filterByName(List<RolePOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<RolePOJO> filterByName(Stream<RolePOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
    static List<RolePOJO> filterByUser(List<RolePOJO> list, UserPOJO user){
        return filterByUser(list.stream(), user).collect(Collectors.toList());
    }
    static Stream<RolePOJO> filterByUser(Stream<RolePOJO> stream, UserPOJO user){
        return stream.filter(t -> t.getPOJOUsers().contains(user));
    }
}
