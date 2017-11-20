package com.modern.codes.lime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    public static final int MAX_LENGTH_NAME = 50;
    public static final int MAX_LENGTH_DESC = 250;

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
    @NotNull
    private Unit unit;

    @ApiModelProperty(value = "The long description of the resource", required = true)
    @NotNull
    private Integer quantity;

    @ApiModelProperty(value = "The category of the resource", required = true)
    @NotNull
    private String category;

    @ApiModelProperty(value = "The image url of the resource", required = true)
    @NotNull
    @URL
    private String image;

    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName="ID")
    private Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "formula_id", referencedColumnName="ID")
    private Formula formula;

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
    public void setName(String name) {
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
    public void setDescription(String description) {
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
    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }
}
