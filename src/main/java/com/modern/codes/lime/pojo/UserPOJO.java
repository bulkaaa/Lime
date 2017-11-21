package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.User;

import java.util.Date;
import java.util.List;

public class UserPOJO {

    private String id;
    private String name;
    private String surname;
    private Date joinedAt;

    private List<Job> jobs;

    public UserPOJO() {}

    public UserPOJO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Date getJoinedAt() {
        return joinedAt;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setJoinedAt(Date joinedAt) {
        this.joinedAt = joinedAt;
    }

    public String getId() {
        return id;
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
