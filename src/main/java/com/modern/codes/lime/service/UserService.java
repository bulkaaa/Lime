package com.modern.codes.lime.service;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("userDetailsService")
@Transactional
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
    public UserPOJO findByUsername(String username) {
        return ParseTools.parse(dao.findByUsername(username), UserPOJO.class);
    }


    @Override
    public List<UserPOJO> findByJoinedAtBetween(Date begin, Date end) {
        return ParseTools.parseList(dao.findByJoinedAtBetween(begin, end), UserPOJO.class);
    }

    @Override
    public List<UserPOJO> findByNameAndSurname(String name, String surname){
        return ParseTools.parseList(dao.findByNameAndSurname(name, surname), UserPOJO.class);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
