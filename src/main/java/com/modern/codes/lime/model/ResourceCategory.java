package com.modern.codes.lime.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Model representation of a resource category used in Lime")
@Entity
public class ResourceCategory implements Serializable {

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(final List<Resource> resources) {
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unique id of the resource category", required = true)
    private String id;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference("category-resources")
    private List<Resource> resources;
    @ApiModelProperty(value = "The name of the resource category. E.g \"vegetables\"", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_NAME)
    private String name;
    private static final int MAX_LENGTH_NAME = 50;
    private static final long serialVersionUID = -334555207904101780L;
}
