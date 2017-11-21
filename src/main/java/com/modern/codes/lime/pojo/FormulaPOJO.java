package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.Resource;

import java.util.List;

public class FormulaPOJO {
    private String id;
    private List<Resource> resources;
    private List<Product> products;
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
