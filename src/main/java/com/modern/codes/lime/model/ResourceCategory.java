package com.modern.codes.lime.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ApiModel(description = "Model representation of a resource category used in Lime")
@Entity
@Transactional
public class ResourceCategory  implements Serializable {

    private static final int MAX_LENGTH_NAME = 50;
    private static final long serialVersionUID = -334555207904101780L;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unique id of the resource category", required = true)
    private String id;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Resource> resources;

    @ApiModelProperty(value = "The name of the resource category. E.g \"vegetables\"", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_NAME)
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
