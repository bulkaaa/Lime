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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of user of Lime.
 *
 * @author jaroszk
 *
 */

@Entity
@Table(name="\"user\"")
public class User implements Serializable{

    private static final long serialVersionUID = 8269421897901384963L;

    private static final int MAX_LENGTH_NAME = 25;
    private static final int MAX_LENGTH_SURNAME = 250;
    private static final int MAX_LENGTH_EMAIL = 240;

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
    @Column(unique=true)
    private String emailAddress;

    @ApiModelProperty(value = "The datetime when the user joined the  \"ChemicalLabs\"", required = true)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "joined_at", updatable = false)
    private Date joinedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference(value="user-jobs")
    private List<Job> jobs;

  
    @ApiModelProperty(value = "The username of the user of the \"ChemicalLabs\"", required = true)
    @NotNull
    @Column(unique=true)
    private String username;

    @ApiModelProperty(value = "The password of the user of the \"ChemicalLabs\"", required = true)
    @NotNull
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;


    @PrePersist
    public void updateTimeStamps() {
        if (joinedAt == null) {
            joinedAt = new Date();
        }
    }

    public String getId()   { return id; }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) { this.surname = surname; }

    public String getSurname() {
        return surname;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public Date getJoinedAt() {

        return joinedAt;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

 
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

 
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
}
