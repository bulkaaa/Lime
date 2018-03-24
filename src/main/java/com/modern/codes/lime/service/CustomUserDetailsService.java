package com.modern.codes.lime.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.CustomUserDetails;
import com.modern.codes.lime.model.User;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    public CustomUserDetailsService(final IUserDAO dao) {
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        User user = dao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No such user in DB");
        }
        return new CustomUserDetails(user);
    }
    private final IUserDAO dao;
}
