package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.PrivilegePOJO;
import com.modern.codes.lime.pojo.RolePOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IPrivilegeService {
    List<PrivilegePOJO> findAll();
    void delete(String id);
    PrivilegePOJO save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    PrivilegePOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);

    List<PrivilegePOJO> findByName(String name);

    static List<PrivilegePOJO> filterByName(List<PrivilegePOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<PrivilegePOJO> filterByName(Stream<PrivilegePOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
    static List<PrivilegePOJO> filterByRole(List<PrivilegePOJO> list, String roleName){
        return filterByRole(list.stream(), roleName).collect(Collectors.toList());
    }
    static Stream<PrivilegePOJO> filterByRole(Stream<PrivilegePOJO> stream, String roleName){
        return stream.filter(t -> !IRoleService.filterByName(t.getPOJORoles(), roleName).isEmpty());
    }
    static List<PrivilegePOJO> filterByRole(List<PrivilegePOJO> list, RolePOJO role){
        return filterByRole(list.stream(), role).collect(Collectors.toList());
    }
    static Stream<PrivilegePOJO> filterByRole(Stream<PrivilegePOJO> stream, RolePOJO role){
        return stream.filter(t -> t.getRoles().contains(role));
    }
}
