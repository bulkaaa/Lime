package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.Resource;

import java.util.List;

public class FormulaPOJO extends BasicPOJO{

    private Resource resource;
    private List<Product> products;
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
        this.resource = ParseTools.parse(resource, Resource.class);
    }

    public List<Product> getDBProducts() {
        return products;
    }

    public void setDBProducts(List<Product> products) {
        this.products = products;
    }

    public List<ProductPOJO> getProducts() {
        return ParseTools.parseList(products, ProductPOJO.class);
    }

    public void setProducts(List<ProductPOJO> products) {
        this.products = ParseTools.parseList(products, Product.class);
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}
