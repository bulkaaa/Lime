package com.modern.codes.lime.pojo;

import java.util.List;

import com.modern.codes.lime.model.Product;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Product category pojo.
 */
public class ProductCategoryPOJO extends BasicPOJO {

    /**
     * Gets products.
     *
     * @return the products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets products.
     *
     * @param products the products
     */
    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    /**
     * Gets pojo products.
     *
     * @return the pojo products
     */
    public List<ProductPOJO> getPOJOProducts() {
        return ParseTools.parseList(products, ProductPOJO.class);
    }

    /**
     * Sets pojo products.
     *
     * @param products the products
     */
    public void setPOJOProducts(final List<ProductPOJO> products) {
        this.products = ParseTools.parseList(products, Product.class);
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
        int hash = 17;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !ProductCategoryPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ProductCategoryPOJO other = (ProductCategoryPOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.name
                                                                    == null
                                                                    && other.name
                                                                       == null)
               || (this.name != null && this.name.equals(other.name)) && (this.products == null
                                                                          && other.products == null)
               || (this.products != null && this.products.equals(other.products));
    }

    private List<Product> products;
    private String name;
}
