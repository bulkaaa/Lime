package com.modern.codes.lime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(mappedBy = "formula", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Resource> resources;

    @OneToMany(mappedBy = "formula", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @ApiModelProperty(value = "The quantity of resources needed for product", required = true)
    @NotNull
    private Integer value;


    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
