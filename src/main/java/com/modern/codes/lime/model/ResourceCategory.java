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

/**
 * The type Resource category.
 */
@ApiModel(description = "Model representation of a resource category used in Lime")
@Entity
public class ResourceCategory implements Serializable {

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
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Gets resources.
     *
     * @return the resources
     */
    public List<Resource> getResources() {
        return resources;
    }

    /**
     * Sets resources.
     *
     * @param resources the resources
     */
    public void setResources(final List<Resource> resources) {
        this.resources = resources;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
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
