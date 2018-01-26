package com.modern.codes.lime.pojo;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Date;
import java.util.List;

public class UserPOJO extends BasicPOJO {
    private String name;
    private String surname;
    private Date joinedAt;
    private List<Role> roles;
    private List<Job> jobs;
    private String username;
    private String password;
    private boolean enabled = true;
    private String emailAddress;

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public UserPOJO() {
    }

    public UserPOJO(final String name, final String surname, final String username, final String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }

    public List<RolePOJO> getPOJORoles() {
        return ParseTools.parseList(roles, RolePOJO.class);
    }

    public void setPOJORoles(final List<RolePOJO> roles) {
        this.roles = ParseTools.parseList(roles, Role.class);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }


    public void setPlainPassword(final String password) {
        this.password = (new BCryptPasswordEncoder()).encode(password);
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(final Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public List<JobPOJO> getPOJOJobs() { return ParseTools.parseList(jobs, JobPOJO.class); }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setPOJOJobs(final List<JobPOJO> jobs) {
        this.jobs = ParseTools.parseList(jobs, Job.class);
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(final List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !UserPOJO.class.isAssignableFrom(obj.getClass())) {
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
                (this.username == null && other.username == null) ||
                (this.username != null && this.username.equals(other.username)) &&
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
