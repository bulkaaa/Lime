package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.PrivilegePOJO;
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
        return stream.filter(t -> t.getUsers().contains(user));
    }
    static List<RolePOJO> filterByPrivilege(List<RolePOJO> list, PrivilegePOJO privilege){
        return filterByPrivilege(list.stream(), privilege).collect(Collectors.toList());
    }
    static Stream<RolePOJO> filterByPrivilege(Stream<RolePOJO> stream, PrivilegePOJO privilege){
        return stream.filter(t -> t.getPrivileges().contains(privilege));
    }
    static List<RolePOJO> filterByPrivilege(List<RolePOJO> list, String privilegeName){
        return filterByPrivilege(list.stream(), privilegeName).collect(Collectors.toList());
    }
    static Stream<RolePOJO> filterByPrivilege(Stream<RolePOJO> stream, String privilegeName){
        return stream.filter(t -> !IPrivilegeService.filterByName(t.getPOJOPrivileges(), privilegeName).isEmpty());
    }
}
