package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.User;

import java.util.Date;
import java.util.List;

public class JobPOJO extends BasicPOJO{
    private List<Product> products;
    private User user;
    private String details;
    private Date startDate;
    private Date endDate;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getResultvalue() {
        return resultvalue;
    }

    public void setResultvalue(Integer resultvalue) {
        this.resultvalue = resultvalue;
    }

    private Integer resultvalue;
}
