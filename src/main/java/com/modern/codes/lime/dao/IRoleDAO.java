package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Role;

@Repository
public interface IRoleDAO extends IBasicCRUDRepository<Role, String> {
    List<Role> findByName(final String name);
}
