package com.modern.codes.lime.pojo;

import java.util.Date;
import java.util.List;

import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.Job;
import com.modern.codes.lime.model.ProductCategory;
import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Product pojo.
 */
public class ProductPOJO extends BasicPOJO {

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
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     */
    public void setUnit(final Unit unit) {
        this.unit = unit;
    }

    /**
     * Gets added at.
     *
     * @return the added at
     */
    public Date getAddedAt() {
        return addedAt;
    }

    /**
     * Sets added at.
     *
     * @param addedAt the added at
     */
    public void setAddedAt(final Date addedAt) {
        this.addedAt = addedAt;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public ProductCategory getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(final ProductCategory category) {
        this.category = category;
    }

    /**
     * Gets pojo category.
     *
     * @return the pojo category
     */
    public ProductCategoryPOJO getPOJOCategory() {
        return ParseTools.parse(category, ProductCategoryPOJO.class);
    }

    /**
     * Sets pojo category.
     *
     * @param category the category
     */
    public void setPOJOCategory(final ProductCategoryPOJO category) {
        this.category = ParseTools.parse(category, ProductCategory.class);
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(final String image) {
        this.image = image;
    }

    /**
     * Gets expected value.
     *
     * @return the expected value
     */
    public Double getExpectedValue() {
        return expectedValue;
    }

    /**
     * Sets expected value.
     *
     * @param expectedValue the expected value
     */
    public void setExpectedValue(final Double expectedValue) {
        this.expectedValue = expectedValue;
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
     * Gets formulas.
     *
     * @return the formulas
     */
    public List<Formula> getFormulas() {
        return formulas;
    }

    /**
     * Sets formulas.
     *
     * @param formulas the formulas
     */
    public void setFormulas(final List<Formula> formulas) {
        this.formulas = formulas;
    }

    /**
     * Gets pojo formulas.
     *
     * @return the pojo formulas
     */
    public List<FormulaPOJO> getPOJOFormulas() {
        return ParseTools.parseList(formulas, FormulaPOJO.class);
    }

    /**
     * Sets pojo formulas.
     *
     * @param formulas the formulas
     */
    public void setPOJOFormulas(final List<FormulaPOJO> formulas) {
        this.formulas = ParseTools.parseList(formulas, Formula.class);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !ProductPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ProductPOJO other = (ProductPOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.name
                                                                    == null
                                                                    && other.name
                                                                       == null)
               || (this.name != null && this.name.equals(other.name)) && (this.category == null
                                                                          && other.category == null)
               || (this.category != null && this.category.equals(other.category)) && (this.description == null
                                                                                      && other.description == null)
               || (this.description != null && this.description.equals(other.description)) && (this.formulas == null
                                                                                               && other.formulas
                                                                                                  == null)
               || (this.formulas != null && this.formulas.equals(other.formulas)) && (this.image == null
                                                                                      && other.image == null)
               || (this.image != null && this.image.equals(other.image)) && (this.unit == null && other.unit == null)
               || (this.unit != null && this.unit.equals(other.unit)) && (this.jobs == null && other.jobs == null)
               || (this.jobs != null && this.jobs.equals(other.jobs)) && (this.expectedValue == null
                                                                          && other.expectedValue == null)
               || (this.expectedValue != null && this.expectedValue.equals(other.expectedValue)) && (this.addedAt
                                                                                                     == null
                                                                                                     && other.addedAt
                                                                                                        == null)
               || (this.addedAt != null && this.addedAt.equals(other.addedAt));
    }

    private String name;
    private String description;
    private Unit unit;
    private Date addedAt;
    private ProductCategory category;
    private String image;
    private Double expectedValue;
    private List<Job> jobs;
    private List<Formula> formulas;
}
