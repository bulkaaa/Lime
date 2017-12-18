package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.ProductCategory;
import com.modern.codes.lime.tools.ParseTools;
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
    private ProductCategory category;
    private String image;
    private Double expectedValue;
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

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
    public ProductCategoryPOJO getPOJOCategory() {
        return ParseTools.parse(category, ProductCategoryPOJO.class);
    }

    public void setPOJOCategory(ProductCategoryPOJO category) {
        this.category = ParseTools.parse(category, ProductCategory.class);
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Double expectedValue) {
        this.expectedValue = expectedValue;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<JobPOJO> getPOJOJobs() {
        return ParseTools.parseList(jobs, JobPOJO.class);
    }

    public void setPOJOJobs(List<JobPOJO> jobs) {
        this.jobs = ParseTools.parseList(jobs, Job.class);
    }

    public List<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }

    public List<FormulaPOJO> getPOJOFormulas() {
        return ParseTools.parseList(formulas, FormulaPOJO.class);
    }

    public void setPOJOFormulas(List<FormulaPOJO> formulas) {
        this.formulas = ParseTools.parseList(formulas, Formula.class);
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !ProductPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ProductPOJO other = (ProductPOJO) obj;
        return  (this.id == null && other.id == null) ||
                (this.id != null && this.id.equals(other.id)) &&
                (this.name == null && other.name == null) ||
                (this.name != null && this.name.equals(other.name)) &&
                (this.category == null && other.category == null) ||
                (this.category != null && this.category.equals(other.category)) &&
                (this.description == null && other.description == null) ||
                (this.description != null && this.description.equals(other.description)) &&
                (this.formulas == null && other.formulas == null) ||
                (this.formulas != null && this.formulas.equals(other.formulas)) &&
                (this.image == null && other.image == null) ||
                (this.image != null && this.image.equals(other.image)) &&
                (this.unit == null && other.unit == null) ||
                (this.unit != null && this.unit.equals(other.unit)) &&
                (this.jobs == null && other.jobs == null) ||
                (this.jobs != null && this.jobs.equals(other.jobs)) &&
                (this.expectedValue == null && other.expectedValue == null) ||
                (this.expectedValue != null && this.expectedValue.equals(other.expectedValue)) &&
                (this.addedAt == null && other.addedAt == null) ||
                (this.addedAt != null && this.addedAt.equals(other.addedAt));
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
}
