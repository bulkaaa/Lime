package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleDAO extends IBasicCRUDRepository<Role, String>, IResourceCustomDAO{

}
