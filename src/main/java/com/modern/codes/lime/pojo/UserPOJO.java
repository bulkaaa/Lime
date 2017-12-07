package com.modern.codes.lime.pojo;

import com.modern.codes.lime.tools.ParseTools;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !UserPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final UserPOJO other = (UserPOJO) obj;
        return  (this.id == null && other.id == null) ||
                (this.id != null && this.id.equals(other.id)) &&
                (this.name == null && other.name == null) ||
                (this.name != null && this.name.equals(other.name)) &&
                (this.surname == null && other.surname == null) ||
                (this.surname != null && this.surname.equals(other.surname)) &&
                (this.joinedAt == null && other.joinedAt == null) ||
                (this.joinedAt != null && this.joinedAt.equals(other.joinedAt)) &&
                (this.login == null && other.login == null) ||
                (this.login != null && this.login.equals(other.login)) &&
                (this.jobs == null && other.jobs == null) ||
                (this.jobs != null && this.jobs.equals(other.jobs)) &&
                (this.roles == null && other.roles == null) ||
                (this.roles != null && this.roles.equals(other.roles));
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
