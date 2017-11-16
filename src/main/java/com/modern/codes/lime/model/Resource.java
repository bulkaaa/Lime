package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Model representation of a resource used in Lime.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of a resource used in Lime")
@Entity
public class Resource {

    private static final long serialVersionUID = 8269473897901383543L;

    public static final int MAX_LENGTH_NAME = 50;
    public static final int MAX_LENGTH_DESC = 250;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the resource", required = true)
    @JsonProperty("id")
    private String id;

    @ApiModelProperty(value = "The name of the resource. E.g \"TrackMyTools Basic month\"", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_NAME)
    @JsonProperty("name")
    private String name;

    @ApiModelProperty(value = "The long description of the resource", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_DESC)
    @JsonProperty("description")
    private String description;

    @ApiModelProperty(value = "The unit in which resource is measured", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_DESC)
    @JsonProperty("unit")
    private Unit unit;

    /**
     * Get the id of the entity.
     *
     * @return entity id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id of the entity.
     *
     * @param id
     *            entity id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Return the resource name.
     *
     * @return The resource name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the resource name.
     *
     * @param name
     *            The resource name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the resource description.
     *
     * @return The resource description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the resource description.
     *
     * @param description
     *            The resource description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the resource unit.
     *
     * @return The resource unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Set the resource unit.
     *
     * @param unit
     *            The resource unit
     */
    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
