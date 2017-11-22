package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Privilege;
import com.modern.codes.lime.model.User;

import java.util.List;

public class RolePOJO {
    private String id;

    private String name;

    private List<User> users;

    private List<Privilege> privileges;

    public String getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public List<User> getUsers() { return users; }

    public void setUsers(List<User> users) { this.users = users; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
