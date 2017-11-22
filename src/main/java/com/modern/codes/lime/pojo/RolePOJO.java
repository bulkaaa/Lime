package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Privilege;
import com.modern.codes.lime.model.User;

import java.util.List;

public class RolePOJO extends BasicPOJO{

    private String name;

    private List<User> users;

    private List<Privilege> privileges;

    public List<User> getDBUsers() { return users; }

    public void setDBUsers(List<User> users) { this.users = users; }

    public List<UserPOJO> getUsers() { return ParseTools.parseList(users, UserPOJO.class); }

    public void setUsers(List<UserPOJO> users) { this.users = ParseTools.parseList(users, User.class); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getDBPrivileges() {
        return privileges;
    }

    public void setDBPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public List<PrivilegePOJO> getPrivileges() {
        return ParseTools.parseList(privileges, PrivilegePOJO.class);
    }

    public void setPrivileges(List<PrivilegePOJO> privileges) {
        this.privileges = ParseTools.parseList(privileges, Privilege.class);
    }


}
