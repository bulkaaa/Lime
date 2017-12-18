package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Model representation of user of Lime.
 *
 * @author jaroszk
 *
 */

@Entity
@Transactional
public class User {

    private static final int MAX_LENGTH_NAME = 25;
    private static final int MAX_LENGTH_SURNAME = 250;
    public static final int MAX_LENGTH_EMAIL = 240;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the resource", required = true)
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
    private String emailAddress;

    @ApiModelProperty(value = "The datetime when the user joined the  \"ChemicalLabs\"", required = true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "joined_at", updatable = false)
    private Date joinedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Job> jobs;

    @ApiModelProperty(value = "The username of the user of the \"ChemicalLabs\"", required = true)
    @NotNull
    private String username;

    @ApiModelProperty(value = "The password of the user of the \"ChemicalLabs\"", required = true)
    @NotNull
    @JsonIgnore
    private String password;


    @Column(name = "enabled", nullable = false)
    private boolean enabled = true;

    @PrePersist
    public void updateTimeStamps() {
        if (joinedAt == null) {
            joinedAt = new Date();
        }
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<Role> roles;

    public boolean getEnabled() {
        return enabled;
    }

    public String getId()   { return id; }

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

    public void setId(String id) {
        this.id = id;
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


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
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
