package com.modern.codes.lime.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.pojo.UserPOJO;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type User service.
 */
@Service("userDetailsService")
public class UserService extends BasicCRUDService<User, UserPOJO, IUserDAO> implements IUserService {

    /**
     * Instantiates a new User service.
     *
     * @param dao the dao
     */
    @Autowired
    public UserService(final IUserDAO dao) {
        super(dao, User.class, UserPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<UserPOJO> findByName(final String name) {
        return ParseTools.parseList(dao.findByName(name), UserPOJO.class);
    }

    @Override
    public List<UserPOJO> findBySurname(final String surname) {
        return ParseTools.parseList(dao.findBySurname(surname), UserPOJO.class);
    }

    @Override
    public UserPOJO findByUsername(final String username) {
        return ParseTools.parse(dao.findByUsername(username), UserPOJO.class);
    }

    @Override
    public List<UserPOJO> findByJoinedAtBetween(final Date begin, final Date end) {
        return ParseTools.parseList(dao.findByJoinedAtBetween(begin, end), UserPOJO.class);
    }

    @Override
    public List<UserPOJO> findByNameAndSurname(final String name, final String surname) {
        return ParseTools.parseList(dao.findByNameAndSurname(name, surname), UserPOJO.class);
    }

    @Override
    public UserPOJO findByUsernameOrEmail(final String username, final String email) {
        return ParseTools.parse(dao.findByUsernameOrEmailAddress(username, email), UserPOJO.class);
    }
    @Override
    public UserPOJO findByEmailAddress(final String email) {
        return ParseTools.parse(dao.findByEmailAddress(email), UserPOJO.class);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final IUserDAO dao;
}
