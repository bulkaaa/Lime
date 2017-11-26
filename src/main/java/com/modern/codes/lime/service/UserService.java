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
    public List<UserPOJO> findUserByNameAndSurname(String name, String surname){
        return ParseTools.parseList(dao.findUserByNameAndSurname(name, surname), UserPOJO.class);
    }



    @Override
    public UserDetails loadUserByUsername(String login) {
        User user = dao.findUserByLogin(login);
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
