package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.tools.ParseTools;

import java.util.List;

public class ResourceCategoryPOJO extends BasicPOJO {
    
    private List<Resource> resources;
    private String name;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<ResourcePOJO> getPOJOResources() {
        return ParseTools.parseList(resources, ResourcePOJO.class);
    }

    public void setPOJOResources(List<ResourcePOJO> resources) {
        this.resources = ParseTools.parseList(resources, Resource.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !ResourceCategoryPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ResourceCategoryPOJO other = (ResourceCategoryPOJO) obj;
        return  (this.id == null && other.id == null) ||
                (this.id != null && this.id.equals(other.id)) &&
                (this.name == null && other.name == null) ||
                (this.name != null && this.name.equals(other.name)) &&
                (this.resources == null && other.resources == null) ||
                (this.resources != null && this.resources.equals(other.resources));
    }
    @Override
    public int hashCode() {
        int hash = 2;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
