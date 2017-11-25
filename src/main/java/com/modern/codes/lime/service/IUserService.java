package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IUserService {
    List<UserPOJO> findAll();
    void delete(String id);
    List<UserPOJO> findUserByNameAndSurname(String name, String surname);
    UserPOJO save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    UserPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);
    static List<UserPOJO> filterByName(List<UserPOJO> list, String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterByName(Stream<UserPOJO> stream, String name){
        return stream.filter(t -> t.getName().equals(name));
    }
    static List<UserPOJO> filterBySurname(List<UserPOJO> list, String surname){
        return filterBySurname(list.stream(), surname).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterBySurname(Stream<UserPOJO> stream, String surname){
        return stream.filter(t -> t.getName().equals(surname));
    }
    static List<UserPOJO> filterByRole(List<UserPOJO> list, String roleName){
        return filterByRole(list.stream(), roleName).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterByRole(Stream<UserPOJO> stream, String roleName){
        return stream.filter(t -> !IRoleService.filterByName(t.getRoles(), roleName).isEmpty());
    }
    static List<UserPOJO> filterByRole(List<UserPOJO> list, RolePOJO role){
        return filterByRole(list.stream(), role).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterByRole(Stream<UserPOJO> stream, RolePOJO role){
        return stream.filter(t -> t.getRoles().contains(role));
    }
}
