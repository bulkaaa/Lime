package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IUserService {
    List<UserPOJO> findAll();
    void delete(String id);
    UserPOJO save(Object t);
    boolean exists(String id);
    boolean exists(Object t);
    long count();
    boolean equals(Object t, Object y);
    void deleteAll();
    UserPOJO findById(String id);
    void delete(Object t);
    void save(List l);
    void delete(List l);

    List<UserPOJO> findByName(String name);
    List<UserPOJO> findBySurname(String surname);
    UserPOJO findByUsername(String username);
    List<UserPOJO> findByJoinedAtBetween(Date begin, Date end);
    List<UserPOJO> findByNameAndSurname(String name, String surname);
    UserPOJO findByUsernameOrEmail(String username, String email);
    PasswordEncoder getPasswordEncoder();

    static List<UserPOJO> filterByName(final List<UserPOJO> list, final String name){
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterByName(final Stream<UserPOJO> stream, final String name){
        return stream.filter(t -> t.getName().equals(name));
    }
    static List<UserPOJO> filterBySurname(final List<UserPOJO> list, final String surname){
        return filterBySurname(list.stream(), surname).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterBySurname(final Stream<UserPOJO> stream, final String surname){
        return stream.filter(t -> t.getName().equals(surname));
    }
    static List<UserPOJO> filterByRole(final List<UserPOJO> list, final String roleName){
        return filterByRole(list.stream(), roleName).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterByRole(final Stream<UserPOJO> stream, final String roleName){
        return stream.filter(t -> !IRoleService.filterByName(t.getPOJORoles(), roleName).isEmpty());
    }
    static List<UserPOJO> filterByRole(final List<UserPOJO> list, final RolePOJO role){
        return filterByRole(list.stream(), role).collect(Collectors.toList());
    }
    static Stream<UserPOJO> filterByRole(final Stream<UserPOJO> stream, final RolePOJO role){
        return stream.filter(t -> t.getPOJORoles().contains(role));
    }
}