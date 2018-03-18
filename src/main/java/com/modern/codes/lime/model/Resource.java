package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Model representation of a resource used in Lime.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of a resource used in Lime")
@Entity
public class Resource implements Serializable{

    private static final long serialVersionUID = 8269473897901383543L;

    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_DESC = 250;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unique id of the resource", required = true)
    private String id;

    @ApiModelProperty(value = "The name of the resource. E.g \"LIME super resource\"", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_NAME)
    private String name;

    @ApiModelProperty(value = "The long description of the resource", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_DESC)
    private String description;

    @ApiModelProperty(value = "The unit in which resource is measured", required = true)
    @Enumerated(EnumType.STRING)
    @NotNull
    @JsonProperty("unit")
    private Unit unit;

    @ApiModelProperty(value = "The quantity of the resource", required = true)
    @NotNull
    private Double quantity;

    @ApiModelProperty(value = "The critical value of the resource", required = true)
    @NotNull
    private Double critical_value;

    @ApiModelProperty(value = "The notifications for the low level of this resource are on if set to true", required = true)
    @NotNull
    private Boolean notifications_on;

    @ApiModelProperty(value = "The automatic ordering of this resource is on if set to true", required = true)
    @NotNull
    private Boolean ordering_on;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName="ID")
    @JsonBackReference("category-resources")
    private ResourceCategory category;

    @ApiModelProperty(value = "The image name of the resource", required = true)
    @NotNull
    private String image;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName="ID")
    @JsonBackReference("supplier-resources")
    private Supplier supplier;


    @OneToMany(mappedBy = "resource", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("resource-formulas")
    private List<Formula> formulas;

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
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Return the resource name.
     *
     * @return The resource name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the resource name.
     *
     * @param name
     *            The resource name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Return the resource description.
     *
     * @return The resource description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the resource description.
     *
     * @param description
     *            The resource description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Return the resource unit.
     *
     * @return The resource unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Set the resource unit.
     *
     * @param unit
     *            The resource unit
     */
    public void setUnit(final Unit unit) {
        this.unit = unit;
    }

    public ResourceCategory getCategory() {
        return category;
    }

    public void setCategory(final ResourceCategory category) {
        this.category = category;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(final Double quantity) {
        this.quantity = quantity;
    }

    public Double getCritical_value() { return critical_value; }

    public void setCritical_value(Double critical_value) { this.critical_value = critical_value; }

    public Boolean getNotifications_on() { return notifications_on; }

    public void setNotifications_on(Boolean notifications_on) { this.notifications_on = notifications_on; }

    public Boolean getOrdering_on() {return ordering_on; }

    public void setOrdering_on(Boolean ordering_on) { this.ordering_on = ordering_on; }

    public String getImage() {
        return image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(final Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(final List<Formula> formulas) {
        this.formulas = formulas;
    }
}
