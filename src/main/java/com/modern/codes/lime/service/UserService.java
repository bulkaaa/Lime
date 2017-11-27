package com.modern.codes.lime.service;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService extends BasicCRUDService<User, UserPOJO, IUserDAO> implements IUserService {

    private IUserDAO dao;
    @Autowired
    public UserService(IUserDAO dao) {
        super(dao, User.class, UserPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<UserPOJO> findByName(String name) {
        return ParseTools.parseList(dao.findByName(name), UserPOJO.class);
    }

    @Override
    public List<UserPOJO> findBySurname(String surname) {
        return ParseTools.parseList(dao.findBySurname(surname), UserPOJO.class);
    }

    @Override
    public UserPOJO findByLogin(String login) {
        return ParseTools.parse(dao.findByLogin(login), UserPOJO.class);
    }

    @Override
    public List<UserPOJO> findByJoinedAtBetween(Date begin, Date end) {
        return ParseTools.parseList(dao.findByJoinedAtBetween(begin, end), UserPOJO.class);
    }

    @Override
    public List<UserPOJO> findByNameAndSurname(String name, String surname){
        return ParseTools.parseList(dao.findByNameAndSurname(name, surname), UserPOJO.class);
    }



    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = dao.findByLogin(login);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("User with login %s doesn't exist", login));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(r -> {
            r.getPrivileges().forEach(p -> {
                authorities.add(new SimpleGrantedAuthority(p.getName()));
            });
        });

        return new org.springframework.security.core.userdetails.
                User(user.getLogin(), user.getPassword(), authorities);
    }
}
