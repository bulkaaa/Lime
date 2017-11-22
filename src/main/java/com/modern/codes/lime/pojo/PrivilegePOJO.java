package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Role;

import java.util.Collection;

public class PrivilegePOJO {

    private String id;
    private String name;
    private Collection<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
