package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDAO extends IBasicCRUDRepository<User, String>, IUserCustomDAO{

}