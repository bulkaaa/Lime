package com.modern.codes.lime.service;

import com.modern.codes.lime.pojo.UserPOJO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    List<UserPOJO> findAll();
    UserPOJO delete(String id);
    void save(Object t);
}
