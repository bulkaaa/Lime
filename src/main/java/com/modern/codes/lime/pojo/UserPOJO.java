package com.modern.codes.lime.pojo;

import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.Role;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type User pojo.
 */
public class UserPOJO extends BasicPOJO {
    /**
     * Instantiates a new User pojo.
     */
    public UserPOJO() {
    }

    /**
     * Instantiates a new User pojo.
     *
     * @param name     the name
     * @param surname  the surname
     * @param username the username
     * @param password the password
     */
    public UserPOJO(final String name, final String surname, final String username, final String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets enabled.
     *
     * @return the enabled
     */
    public boolean getEnabled() {
        return enabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Gets pojo roles.
     *
     * @return the pojo roles
     */
    public List<RolePOJO> getPOJORoles() {
        return ParseTools.parseList(roles, RolePOJO.class);
    }

    /**
     * Sets pojo roles.
     *
     * @param roles the roles
     */
    public void setPOJORoles(final List<RolePOJO> roles) {
        this.roles = ParseTools.parseList(roles, Role.class);
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Sets plain password.
     *
     * @param password the password
     */
    public void setPlainPassword(final String password) {
        this.password = (new BCryptPasswordEncoder()).encode(password);
    }

    /**
     * Gets joined at.
     *
     * @return the joined at
     */
    public Date getJoinedAt() {
        return joinedAt;
    }

    /**
     * Sets joined at.
     *
     * @param joinedAt the joined at
     */
    public void setJoinedAt(final Date joinedAt) {
        this.joinedAt = joinedAt;
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

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(final String surname) {
        this.surname = surname;
    }

    /**
     * Gets pojo jobs.
     *
     * @return the pojo jobs
     */
    public List<JobPOJO> getPOJOJobs() {
        return ParseTools.parseList(jobs, JobPOJO.class);
    }

    /**
     * Sets pojo jobs.
     *
     * @param jobs the jobs
     */
    public void setPOJOJobs(final List<JobPOJO> jobs) {
        this.jobs = ParseTools.parseList(jobs, Job.class);
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets jobs.
     *
     * @return the jobs
     */
    public List<Job> getJobs() {
        return jobs;
    }

    /**
     * Sets jobs.
     *
     * @param jobs the jobs
     */
    public void setJobs(final List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !UserPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final UserPOJO other = (UserPOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.name
                                                                    == null
                                                                    && other.name
                                                                       == null)
               || (this.name != null && this.name.equals(other.name)) && (this.surname == null && other.surname == null)
               || (this.surname != null && this.surname.equals(other.surname)) && (this.joinedAt == null
                                                                                   && other.joinedAt == null)
               || (this.joinedAt != null && this.joinedAt.equals(other.joinedAt)) && (this.username == null
                                                                                      && other.username == null)
               || (this.username != null && this.username.equals(other.username)) && (this.jobs == null
                                                                                      && other.jobs == null)
               || (this.jobs != null && this.jobs.equals(other.jobs)) && (this.roles == null && other.roles == null)
               || (this.roles != null && this.roles.equals(other.roles));
    }

    private String name;
    private String surname;
    private Date joinedAt;
    private List<Role> roles;
    private List<Job> jobs;
    private String username;
    private String password;
    private boolean enabled = true;
    private String emailAddress;
}
