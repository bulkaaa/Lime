package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.Resource;

import java.util.List;

public class FormulaPOJO extends BasicPOJO{

    private List<Resource> resources;
    private List<Product> products;
    private Integer value;

    public List<Resource> getDBResources() {
        return resources;
    }

    public void setDBResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<ResourcePOJO> getResources() {
        return ParseTools.parseList(resources, ResourcePOJO.class);
    }

    public void setResources(List<ResourcePOJO> resources) {
        this.resources = ParseTools.parseList(resources, Resource.class);
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
