package com.modern.codes.lime.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of a user role in Lime.
 *
 * @author jaroszk
 */
@ApiModel(description = "Model representation of a user role in Lime")
@Entity

public class Role {
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the user role", required = true)
    private String id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;
}