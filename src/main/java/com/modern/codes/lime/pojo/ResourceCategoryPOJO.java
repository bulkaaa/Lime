package com.modern.codes.lime.pojo;

import java.util.List;

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Resource category pojo.
 */
public class ResourceCategoryPOJO extends BasicPOJO {

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
     * Gets pojo resources.
     *
     * @return the pojo resources
     */
    public List<ResourcePOJO> getPOJOResources() {
        return ParseTools.parseList(resources, ResourcePOJO.class);
    }

    /**
     * Sets pojo resources.
     *
     * @param resources the resources
     */
    public void setPOJOResources(final List<ResourcePOJO> resources) {
        this.resources = ParseTools.parseList(resources, Resource.class);
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

    @Override
    public int hashCode() {
        int hash = 2;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !ResourceCategoryPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ResourceCategoryPOJO other = (ResourceCategoryPOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.name
                                                                    == null
                                                                    && other.name
                                                                       == null)
               || (this.name != null && this.name.equals(other.name)) && (this.resources == null
                                                                          && other.resources == null)
               || (this.resources != null && this.resources.equals(other.resources));
    }

    private List<Resource> resources;
    private String name;
}
