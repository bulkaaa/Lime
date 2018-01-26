package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDAO extends IBasicCRUDRepository<Role, String> {
    List<Role> findByName(final String name);
}
