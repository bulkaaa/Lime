package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Model representation of a product added in Lime.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of a product in Lime")
@Entity
@Transactional
public class Product implements Serializable {

    private static final long serialVersionUID = 8269473897901384963L;


    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_DESC = 250;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the product", required = true)
    private String id;

    @ApiModelProperty(value = "The name of the product. E.g \"Lime basic\"", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_NAME)
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(value = "The long description of the product", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_DESC)
    @JsonProperty("description")
    private String description;

    @ApiModelProperty(value = "The unit in which product is measured", required = true)
    @NotNull
    @JsonProperty("unit")
    private Unit unit;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "added_at", updatable = false)
    private Date addedAt;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName="ID")
    @JsonManagedReference
    private ProductCategory category;

    @ApiModelProperty(value = "The image url of the product", required = true)
    @NotNull
    @URL
    private String image;


    @ApiModelProperty(value = "?", required = true)
    @NotNull

    private Double expectedValue;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Job> jobs;
    @JsonBackReference
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Formula> formulas;

    @PrePersist
    public void updateTimeStamps() {
        if (addedAt == null) {
            addedAt = new Date();
        }
    }

    /**
     * Get the id of the entity.
     *
     * @return entity id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id of the entity.
     *
     * @param id
     *            entity id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return the product name.
     *
     * @return The product name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the product name.
     *
     * @param name
     *            The product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the product description.
     *
     * @return The product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the product description.
     *
     * @param description
     *            The product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the product creation date.
     *
     * @return The product creation date
     */
    public Date getAddedAt() {
        return addedAt;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
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

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
    }

    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Formula> getFormulas() {
        return formulas;
    }
}
