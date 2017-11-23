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
    private Date createdAt;
    private String category;
    private String image;
    private Integer expectedValue;
    private List<Job> jobs;
    private Formula formula;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Formula getDBFormula() {
        return formula;
    }

    public void setDBFormula(Formula formula) {
        this.formula = formula;
    }
    public FormulaPOJO getFormula() {
        return ParseTools.parse(formula);
    }

    public void setFormula(FormulaPOJO formula) {
        this.formula = ParseTools.parse(formula);
    }

}
