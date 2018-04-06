package com.modern.codes.lime.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of user of Lime.
 *
 * @author jaroszk
 */
@Entity
@Table(name = "\"user\"")
public class User implements Serializable {

    /**
     * Update time stamps.
     */
    @PrePersist
    public void updateTimeStamps() {
        if (joinedAt == null) {
            joinedAt = new Date();
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
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
     * Gets enabled.
     *
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Sets enabled.
     *
     * @param enabled the enabled
     */
    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
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

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the user", required = true)
    @JsonProperty("id")
    private String id;
    @ApiModelProperty(value = "The name of the user of the \"ChemicalLabs\"", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_NAME)
    @JsonProperty("name")
    private String name;
    @ApiModelProperty(value = "The surname of the user", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_SURNAME)
    @JsonProperty("surname")
    private String surname;
    @ApiModelProperty(value = "The E-Mail address of the supplier.", required = true)
    @NotEmpty
    @Email
    @Size(max = MAX_LENGTH_EMAIL)
    @Column(unique = true)
    private String emailAddress;
    @ApiModelProperty(value = "The datetime when the user joined the  \"ChemicalLabs\"", required = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "joined_at", updatable = false)
    private Date joinedAt;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Job> jobs;
    @ApiModelProperty(value = "The username of the user of the \"ChemicalLabs\"", required = true)
    @NotNull
    @Column(unique = true)
    private String username;
    @ApiModelProperty(value = "The password of the user of the \"ChemicalLabs\"", required = true)
    @NotNull
    private String password;
    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
               joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
    private static final long serialVersionUID = 8269421897901384963L;
    private static final int MAX_LENGTH_NAME = 25;
    private static final int MAX_LENGTH_SURNAME = 250;
    private static final int MAX_LENGTH_EMAIL = 240;

}
