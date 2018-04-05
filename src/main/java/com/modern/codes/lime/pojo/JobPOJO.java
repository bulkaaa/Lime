package com.modern.codes.lime.pojo;

import java.util.Date;

import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.User;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Job pojo.
 */
public class JobPOJO extends BasicPOJO {
    /**
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
    public void setProduct(final Product product) {
        this.product = product;
    }

    /**
     * Gets pojo product.
     *
     * @return the pojo product
     */
    public ProductPOJO getPOJOProduct() {
        return ParseTools.parse(product, ProductPOJO.class);
    }

    /**
     * Sets pojo product.
     *
     * @param product the product
     */
    public void setPOJOProduct(final ProductPOJO product) {
        this.product = ParseTools.parse(product, Product.class);
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(final User user) {
        this.user = user;
    }

    /**
     * Gets pojo user.
     *
     * @return the pojo user
     */
    public UserPOJO getPOJOUser() {
        return ParseTools.parse(user, UserPOJO.class);
    }

    /**
     * Sets pojo user.
     *
     * @param user the user
     */
    public void setPOJOUser(final UserPOJO user) {
        this.user = ParseTools.parse(user, User.class);
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     */
    public void setDetails(final String details) {
        this.details = details;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets result value.
     *
     * @return the result value
     */
    public Double getResultValue() {
        return resultValue;
    }

    /**
     * Sets result value.
     *
     * @param resultValue the result value
     */
    public void setResultValue(final Double resultValue) {
        this.resultValue = resultValue;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 89 * hash + (this.details != null ? this.details.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !JobPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final JobPOJO other = (JobPOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.product
                                                                    == null
                                                                    && other.product
                                                                       == null)
               || (this.product != null && this.product.equals(other.product)) && (this.user == null
                                                                                   && other.user == null)
               || (this.user != null && this.user.equals(other.user)) && (this.details == null && other.details == null)
               || (this.details != null && this.details.equals(other.details)) && (this.startDate == null
                                                                                   && other.startDate == null)
               || (this.startDate != null && this.startDate.equals(other.startDate)) && (this.endDate == null
                                                                                         && other.endDate == null)
               || (this.endDate != null && this.endDate.equals(other.endDate)) && (this.resultValue == null
                                                                                   && other.resultValue == null)
               || (this.resultValue != null && this.resultValue.equals(other.resultValue));
    }

    private Product product;
    private User user;
    private String details;
    private Date startDate;
    private Date endDate;
    private Double resultValue;
}
