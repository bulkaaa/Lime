package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Role;

import java.util.List;

public interface IRoleCustomDAO {
    List<Role> getRoleByName(String name);
}
