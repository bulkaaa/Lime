package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.Resource;

import java.util.List;

public class FormulaPOJO extends BasicPOJO{

    private Resource resource;
    private Product product;
    private Double value;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public ResourcePOJO getPOJOResource() {
        return ParseTools.parse(resource, ResourcePOJO.class);
    }

    public void setPOJOResource(ResourcePOJO resources) {
        this.resource = ParseTools.parse(resources, Resource.class);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductPOJO getPOJOProduct() {
        return ParseTools.parse(product, ProductPOJO.class);
    }

    public void setPOJOProduct(ProductPOJO product) {
        this.product = ParseTools.parse(product, Product.class);
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
