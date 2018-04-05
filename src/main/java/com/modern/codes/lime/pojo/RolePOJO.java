package com.modern.codes.lime.pojo;

import java.util.List;

import com.modern.codes.lime.model.User;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Role pojo.
 */
public class RolePOJO extends BasicPOJO {

    /**
     * Gets users.
     *
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets users.
     *
     * @param users the users
     */
    public void setUsers(final List<User> users) {
        this.users = users;
    }

    /**
     * Gets pojo users.
     *
     * @return the pojo users
     */
    public List<UserPOJO> getPOJOUsers() {
        return ParseTools.parseList(users, UserPOJO.class);
    }

    /**
     * Sets pojo users.
     *
     * @param users the users
     */
    public void setPOJOUsers(final List<UserPOJO> users) {
        this.users = ParseTools.parseList(users, User.class);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !RolePOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final RolePOJO other = (RolePOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.name
                                                                    == null
                                                                    && other.name
                                                                       == null)
               || (this.name != null && this.name.equals(other.name)) && (this.users == null && other.users == null)
               || (this.users != null && this.users.equals(other.users));
    }

    private String name;
    private List<User> users;

}
