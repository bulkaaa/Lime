package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.Role;

import java.util.Date;
import java.util.List;

public class UserPOJO extends BasicPOJO<String> {


    private String name;
    private String surname;
    private Date joinedAt;
    private List<Role> roles;
    private List<Job> jobs;
    private String login;
    private String password;

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPOJO() {
    }

    public UserPOJO(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }


    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public String getDescription() {
        return surname;
    }

    public void setDescription(String description) {
        this.surname = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }


}
