package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Model representation of a job created by Lime user.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of a job that is created by Lime user")
@Entity
@Transactional
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
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName="ID")
    @JsonManagedReference
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

    public void setProduct(Product product) {
        this.product = product;
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

    public Double getResultValue() {
        return resultValue;
    }

    public void setResultValue(Double resultValue) {
        this.resultValue = resultValue;
    }
}
