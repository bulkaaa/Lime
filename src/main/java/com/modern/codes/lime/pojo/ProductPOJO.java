package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.Unit;

import java.util.Date;

public class ProductPOJO {

    private String id;
    private String name;
    private String description;
    private Unit unit;
    private Date createdAt;
    private String category;
    private String image;
    private Integer expectedValue;
    private Job job;
    private Formula formula;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }
}
