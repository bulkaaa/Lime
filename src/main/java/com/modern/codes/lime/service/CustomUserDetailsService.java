package com.modern.codes.lime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.CustomUserDetails;
import com.modern.codes.lime.model.User;

/**
 * The type Custom user details service.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    /**
     * Instantiates a new Custom user details service.
     *
     * @param dao the dao
     */
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
