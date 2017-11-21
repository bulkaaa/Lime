package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Model representation of a job created by Lime user.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of a job that is created by Lime user")
@Entity
public class Job {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the job", required = true)
    @JsonProperty("id")
    private String id;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName="ID")
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
    private Integer resultvalue;


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
}
