package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Formula pojo.
 */
public class FormulaPOJO extends BasicPOJO {

    /**
     * Gets resource.
     *
     * @return the resource
     */
    public Resource getResource() {
        return resource;
    }

    /**
     * Sets resource.
     *
     * @param resource the resource
     */
    public void setResource(final Resource resource) {
        this.resource = resource;
    }

    /**
     * Gets pojo resource.
     *
     * @return the pojo resource
     */
    public ResourcePOJO getPOJOResource() {
        return ParseTools.parse(resource, ResourcePOJO.class);
    }

    /**
     * Sets pojo resource.
     *
     * @param resources the resources
     */
    public void setPOJOResource(final ResourcePOJO resources) {
        this.resource = ParseTools.parse(resources, Resource.class);
    }

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
     * Gets pojo product.
     *
     * @return the pojo product
     */
    public ProductPOJO getPOJOProduct() {
        return ParseTools.parse(product, ProductPOJO.class);
    }

    /**
     * Sets pojo product.
     *
     * @param product the product
     */
    public void setPOJOProduct(final ProductPOJO product) {
        this.product = ParseTools.parse(product, Product.class);
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public Double getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(final Double value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 11 * hash + (this.product.getName() != null ? this.product.getName()
                                                                         .hashCode() : 0);
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !FormulaPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final FormulaPOJO other = (FormulaPOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.resource
                                                                    == null
                                                                    && other.resource
                                                                       == null)
               || (this.resource != null && this.resource.equals(other.resource)) && (this.product == null
                                                                                      && other.product == null)
               || (this.product != null && this.product.equals(other.product)) && (this.value == null
                                                                                   && other.value == null)
               || (this.value != null && this.value.equals(other.value));
    }

    private Resource resource;
    private Product product;
    private Double value;
}
