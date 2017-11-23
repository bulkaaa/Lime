package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.User;

import java.util.Date;

public class JobPOJO extends BasicPOJO{
    private Product product;
    private User user;
    private String details;
    private Date startDate;
    private Date endDate;
    private Integer resultValue;
    public void setDBProduct(Product product) {
        this.product = product;
    }
    public Product getDBProduct() {
        return product;
    }
    public ProductPOJO getProduct() {
        return ParseTools.parse(product, ProductPOJO.class);
    }

    public void setProduct(ProductPOJO products) {
        this.product = ParseTools.parse(products, Product.class);
    }

    public User getDBUser() {
        return user;
    }

    public void setDBUser(User user) {
        this.user = user;
    }

    public UserPOJO getUser() {
        return ParseTools.parse(user);
    }

    public void setUser(UserPOJO user) {
        this.user = ParseTools.parse(user);
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

    public Integer getResultValue() {
        return resultValue;
    }

    public void setResultValue(Integer resultValue) {
        this.resultValue = resultValue;
    }

}
