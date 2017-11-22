package com.modern.codes.lime.pojo;

import com.google.common.collect.Lists;
import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Role;

import java.util.Collection;
import java.util.List;

public class PrivilegePOJO extends BasicPOJO{

    private String name;
    private Collection<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Role> getDBRoles() {
        return roles;
    }

    public void setDBRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public List<RolePOJO> getRoles() {
        return ParseTools.parseList(Lists.newArrayList(roles), RolePOJO.class);
    }

    public void setRoles(List<RolePOJO> roles) {
        this.roles = ParseTools.parseList(roles, Role.class);
    }

}
