package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.Unit;

import java.util.Date;
import java.util.List;

public class ProductPOJO extends BasicPOJO{

    private String name;
    private String description;
    private Unit unit;
    private Date addedAt;
    private String category;
    private String image;
    private Integer expectedValue;
    private List<Job> jobs;
    private List<Formula> formulas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Integer expectedValue) {
        this.expectedValue = expectedValue;
    }

    public List<Job> getDBJobs() {
        return jobs;
    }

    public void setDBJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<JobPOJO> getJobs() {
        return ParseTools.parseList(jobs, JobPOJO.class);
    }

    public void setJobs(List<JobPOJO> jobs) {
        this.jobs = ParseTools.parseList(jobs, Job.class);
    }

    public List<Formula> getDBFormulas() {
        return formulas;
    }

    public void setDBFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }

    public List<FormulaPOJO> getFormulas() {
        return ParseTools.parseList(formulas, FormulaPOJO.class);
    }

    public void setFormulas(List<FormulaPOJO> formulas) {
        this.formulas = ParseTools.parseList(formulas, Formula.class);
    }

}
