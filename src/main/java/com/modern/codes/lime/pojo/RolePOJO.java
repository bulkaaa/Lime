package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Privilege;
import com.modern.codes.lime.model.User;

import java.util.List;

public class RolePOJO extends BasicPOJO{

    private String name;

    private List<User> users;

    private List<Privilege> privileges;

    public List<User> getUsers() { return users; }

    public void setUsers(List<User> users) { this.users = users; }

    public List<UserPOJO> getPOJOUsers() { return ParseTools.parseList(users, UserPOJO.class); }

    public void setPOJOUsers(List<UserPOJO> users) { this.users = ParseTools.parseList(users, User.class); }

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

    public List<PrivilegePOJO> getPOJOPrivileges() {
        return ParseTools.parseList(privileges, PrivilegePOJO.class);
    }

    public void setPOJOPrivileges(List<PrivilegePOJO> privileges) {
        this.privileges = ParseTools.parseList(privileges, Privilege.class);
    }


}
