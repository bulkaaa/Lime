package com.modern.codes.lime.pojo;

import com.google.common.collect.Lists;
import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.model.Role;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !PrivilegePOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final PrivilegePOJO other = (PrivilegePOJO) obj;
        return  (this.id == null && other.id == null) ||
                (this.id != null && this.id.equals(other.id)) &&
                (this.name == null && other.name == null) ||
                (this.name != null && this.name.equals(other.name)) &&
                (this.roles == null && other.roles == null) ||
                (this.roles != null && this.roles.equals(other.roles));
    }
    @Override
    public int hashCode() {
        int hash = 11;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
