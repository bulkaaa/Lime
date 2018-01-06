package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Model representation of a formula used in Lime.
 *
 * @author jaroszk
 *
 */

@ApiModel(description = "Model representation of a formula used in Lime")
@Entity
public class Formula implements Serializable {

    private static final long serialVersionUID = 8262323897901383543L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the formula", required = true)
    private String id;

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName="ID")
    @JsonManagedReference
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName="ID")
    @JsonManagedReference
    private Product product;

    @ApiModelProperty(value = "The quantity of resources needed for product", required = true)
    @NotNull
    private Double value;


    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resources) {
        this.resource = resources;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
