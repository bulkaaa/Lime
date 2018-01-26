package com.modern.codes.lime.pojo;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.User;

import java.util.Date;

public class JobPOJO extends BasicPOJO{
    private Product product;
    private User user;
    private String details;
    private Date startDate;
    private Date endDate;
    private Double resultValue;

    public void setProduct(final Product product) {
        this.product = product;
    }
    public Product getProduct() {
        return product;
    }
    public ProductPOJO getPOJOProduct() {
        return ParseTools.parse(product, ProductPOJO.class);
    }

    public void setPOJOProduct(final ProductPOJO product) {
        this.product = ParseTools.parse(product, Product.class);
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public UserPOJO getPOJOUser() {
        return ParseTools.parse(user, UserPOJO.class);
    }

    public void setPOJOUser(final UserPOJO user) {
        this.user = ParseTools.parse(user, User.class);
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(final String details) {
        this.details = details;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public Double getResultValue() {
        return resultValue;
    }

    public void setResultValue(final Double resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !JobPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final JobPOJO other = (JobPOJO) obj;
        return  (this.id == null && other.id == null) ||
                (this.id != null && this.id.equals(other.id)) &&
                (this.product == null && other.product == null) ||
                (this.product != null && this.product.equals(other.product)) &&
                (this.user == null && other.user == null) ||
                (this.user != null && this.user.equals(other.user)) &&
                (this.details == null && other.details == null) ||
                (this.details != null && this.details.equals(other.details)) &&
                (this.startDate == null && other.startDate == null) ||
                (this.startDate != null && this.startDate.equals(other.startDate)) &&
                (this.endDate == null && other.endDate == null) ||
                (this.endDate != null && this.endDate.equals(other.endDate)) &&
                (this.resultValue == null && other.resultValue == null) ||
                (this.resultValue != null && this.resultValue.equals(other.resultValue));
    }
    @Override
    public int hashCode() {
        int hash = 13;
        hash = 89 * hash + (this.details != null ? this.details.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
