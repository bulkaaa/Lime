package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.User;

import java.util.Date;
import java.util.List;

public class JobPOJO {
    private String id;
    private List<Product> products;
    private User user;
    private String details;
    private Date startDate;
    private Date endDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
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
