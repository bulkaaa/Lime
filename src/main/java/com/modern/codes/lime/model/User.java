package com.modern.codes.lime.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class User {

    public static final int MAX_LENGTH_NAME = 25;
    public static final int MAX_LENGTH_SURNAME = 250;

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

    @ApiModelProperty(value = "The datetime when the user joined the  \"ChemicalLabs\"", required = true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "joined_at", updatable = false)
    private Date joinedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Job> jobs;

    @PrePersist
    public void updateTimeStamps() {
        if (joinedAt == null) {
            joinedAt = new Date();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    // Login, pass and roles will be added later with Spring security

}
