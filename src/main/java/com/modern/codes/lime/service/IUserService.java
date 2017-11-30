package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IUserService extends UserDetailsService {
    List<UserPOJO> findAll();
    void delete(String id);
    UserPOJO save(Object t);
    boolean exists(String id);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    UserPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);

    List<UserPOJO> findByName(String name);
    List<UserPOJO> findBySurname(String surname);
    UserPOJO findByLogin(String login);
    List<UserPOJO> findByJoinedAtBetween(Date begin, Date end);
    List<UserPOJO> findByNameAndSurname(String name, String surname);

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
        return stream.filter(t -> !IRoleService.filterByName(t.getPOJORoles(), roleName).isEmpty());
    }
    static List<UserPOJO> filterByRole(List<UserPOJO> list, RolePOJO role){
        return filterByRole(list.stream(), role).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterByRole(Stream<UserPOJO> stream, RolePOJO role){
        return stream.filter(t -> t.getRoles().contains(role));
    }
}
