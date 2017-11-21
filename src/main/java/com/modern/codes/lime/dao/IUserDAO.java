package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.User;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
@NoRepositoryBean
public interface IUserDAO {
    List<User> getUserByNameAndSurname(String name, String surname);
}