package com.modern.codes.lime.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of a formula used in Lime.
 *
 * @author jaroszk
 */

@ApiModel(description = "Model representation of a formula used in Lime")
@Entity
public class Formula implements Serializable {

    public Resource getResource() {
        return resource;
    }

    public void setResource(final Resource resources) {
        this.resource = resources;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the formula", required = true)
    private String id;
    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "ID")
    private Resource resource;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "ID")
    private Product product;
    @ApiModelProperty(value = "The quantity of resources needed for product", required = true)
    @NotNull
    private Double value;
    private static final long serialVersionUID = 8262323897901383543L;
}
