package com.modern.codes.lime.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of a job created by Lime user.
 *
 * @author jaroszk
 */
@ApiModel(description = "Model representation of a job that is created by Lime user")
@Entity
public class Job implements Serializable {

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
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * Gets serial version uid.
     *
     * @return the serial version uid
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the job", required = true)
    @JsonProperty("id")
    private String id;
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "ID")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "ID")
    private User user;
    @ApiModelProperty(value = "The details of the job", required = true)
    @NotNull
    @JsonProperty("details")
    private String details;
    @ApiModelProperty(value = "The start date of the job", required = true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date startDate;
    @ApiModelProperty(value = "The end date of the job", required = true)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date endDate;
    @ApiModelProperty(value = "?", required = true)
    @NotNull
    private Double resultValue;
    private static final long serialVersionUID = 6863653782430587120L;
}
