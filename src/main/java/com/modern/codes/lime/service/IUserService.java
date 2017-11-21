package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IUserDAO;
import com.modern.codes.lime.model.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService extends IBasicCRUDService <User, String>, IUserDAO {
    //methods For Users that does not access strictly database
}
