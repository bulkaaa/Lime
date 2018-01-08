package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.CustomUserDetails;
import com.modern.codes.lime.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private IUserDAO dao;
    @Autowired
    public CustomUserDetailsService(IUserDAO dao){
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = dao.findByUsername(username);

        return new CustomUserDetails(user);
    }
}
