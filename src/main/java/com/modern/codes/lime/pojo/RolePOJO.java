package com.modern.codes.lime.pojo;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.model.User;

import java.util.List;

public class RolePOJO extends BasicPOJO{

    private String name;

    private List<User> users;


    public List<User> getUsers() { return users; }

    public void setUsers(final List<User> users) { this.users = users; }

    public List<UserPOJO> getPOJOUsers() { return ParseTools.parseList(users, UserPOJO.class); }

    public void setPOJOUsers(final List<UserPOJO> users) { this.users = ParseTools.parseList(users, User.class); }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !RolePOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final RolePOJO other = (RolePOJO) obj;
        return  (this.id == null && other.id == null) ||
                (this.id != null && this.id.equals(other.id)) &&
                (this.name == null && other.name == null) ||
                (this.name != null && this.name.equals(other.name)) &&
                (this.users == null && other.users == null) ||
                (this.users != null && this.users.equals(other.users));
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
