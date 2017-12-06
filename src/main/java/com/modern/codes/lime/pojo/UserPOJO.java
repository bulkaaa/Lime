package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.Role;

import java.util.Date;
import java.util.List;

public class UserPOJO extends BasicPOJO {

    private String name;
    private String surname;
    private Date joinedAt;
    private List<Role> roles;
    private List<Job> jobs;
    private String login;
    private String password;

    public UserPOJO() {
    }

    public UserPOJO(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<RolePOJO> getPOJORoles() {
        return ParseTools.parseList(roles, RolePOJO.class);
    }

    public void setPOJORoles(List<RolePOJO> roles) {
        this.roles = ParseTools.parseList(roles, Role.class);
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

    public List<JobPOJO> getPOJOJobs() { return ParseTools.parseList(jobs, JobPOJO.class); }

    public void setPOJOJobs(List<JobPOJO> jobs) {
        this.jobs = ParseTools.parseList(jobs, Job.class);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

}
