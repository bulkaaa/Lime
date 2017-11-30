package com.modern.codes.lime.pojo;

import com.google.common.collect.Lists;
import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Role;

import java.util.Collection;
import java.util.List;

public class PrivilegePOJO extends BasicPOJO{

    private String name;
    private List<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<RolePOJO> getPOJORoles() {
        return ParseTools.parseList(Lists.newArrayList(roles), RolePOJO.class);
    }

    public void setPOJORoles(List<RolePOJO> roles) {
        this.roles = ParseTools.parseList(roles, Role.class);
    }

}
