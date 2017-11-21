package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Model representation of a product created in Lime.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of a product created in Lime")
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 8269473897901384963L;


    public static final int MAX_LENGTH_NAME = 50;
    public static final int MAX_LENGTH_DESC = 250;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the product", required = true)
    @JsonProperty("id")
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
    @Size(max = MAX_LENGTH_DESC)
    @JsonProperty("unit")
    private Unit unit;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, name = "created_at", updatable = false)
    private Date createdAt;

    @ApiModelProperty(value = "The category of the product", required = true)
    @NotNull
    private String category;

    @ApiModelProperty(value = "The image url of the product", required = true)
    @NotNull
    @URL
    private String image;

    @ApiModelProperty(value = "?", required = true)
    @NotNull
    private Integer expectedValue;


    @ManyToOne
    @JoinColumn(name = "job_id", referencedColumnName="ID")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "formula_id", referencedColumnName="ID")
    private Formula formula;

    @PrePersist
    public void updateTimeStamps() {
        if (createdAt == null) {
            createdAt = new Date();
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
    public Date getCreatedAt() {
        return createdAt;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
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
}
