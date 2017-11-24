package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.Resource;

import java.util.List;

public class FormulaPOJO extends BasicPOJO{

    private Resource resource;
    private Product product;
    private Integer value;

    public Resource getDBResource() {
        return resource;
    }

    public void setDBResource(Resource resource) {
        this.resource = resource;
    }

    public ResourcePOJO getResource() {
        return ParseTools.parse(resource, ResourcePOJO.class);
    }

    public void setResource(ResourcePOJO resources) {
        this.resource = ParseTools.parse(resources, Resource.class);
    }

    public Product getDBProduct() {
        return product;
    }

    public void setDBProduct(Product product) {
        this.product = product;
    }

    public ProductPOJO getProduct() {
        return ParseTools.parse(product);
    }

    public void setProduct(ProductPOJO product) {
        this.product = ParseTools.parse(product);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
