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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of a job created by Lime user.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of a job that is created by Lime user")
@Entity
public class Job implements Serializable {

    private static final long serialVersionUID = 6863653782430587120L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the job", required = true)
    @JsonProperty("id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName="ID")
    @JsonBackReference("product-jobs")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName="ID")
    @JsonBackReference("user-jobs")
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Product getProduct() {
        return product;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
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
}
