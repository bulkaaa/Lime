package com.modern.codes.lime.pojo;

import java.util.List;

import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.ResourceCategory;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.model.Unit;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Resource pojo.
 */
public class ResourcePOJO extends BasicPOJO {

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

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public Unit getUnit() {
        return unit;
    }

    /**
     * Sets unit.
     *
     * @param unit the unit
     */
    public void setUnit(final Unit unit) {
        this.unit = unit;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(final Double quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets critical value.
     *
     * @return the critical value
     */
    public Double getCritical_value() {
        return critical_value;
    }

    /**
     * Sets critical value.
     *
     * @param critical_value the critical value
     */
    public void setCritical_value(final Double critical_value) {
        this.critical_value = critical_value;
    }

    /**
     * Gets notifications on.
     *
     * @return the notifications on
     */
    public Boolean getNotifications_on() {
        return notifications_on;
    }

    /**
     * Sets notifications on.
     *
     * @param notifications_on the notifications on
     */
    public void setNotifications_on(final Boolean notifications_on) {
        this.notifications_on = notifications_on;
    }

    /**
     * Gets ordering on.
     *
     * @return the ordering on
     */
    public Boolean getOrdering_on() {
        return ordering_on;
    }

    /**
     * Sets ordering on.
     *
     * @param ordering_on the ordering on
     */
    public void setOrdering_on(final Boolean ordering_on) {
        this.ordering_on = ordering_on;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public ResourceCategory getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(final ResourceCategory category) {
        this.category = category;
    }

    /**
     * Gets pojo category.
     *
     * @return the pojo category
     */
    public ResourceCategoryPOJO getPOJOCategory() {
        return ParseTools.parse(category, ResourceCategoryPOJO.class);
    }

    /**
     * Sets pojo category.
     *
     * @param category the category
     */
    public void setPOJOCategory(final ResourceCategoryPOJO category) {
        this.category = ParseTools.parse(category, ResourceCategory.class);
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(final String image) {
        this.image = image;
    }

    /**
     * Gets supplier.
     *
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets supplier.
     *
     * @param supplier the supplier
     */
    public void setSupplier(final Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Gets pojo supplier.
     *
     * @return the pojo supplier
     */
    public SupplierPOJO getPOJOSupplier() {
        return ParseTools.parse(supplier, SupplierPOJO.class);
    }

    /**
     * Sets pojo supplier.
     *
     * @param supplier the supplier
     */
    public void setPOJOSupplier(final SupplierPOJO supplier) {
        this.supplier = ParseTools.parse(supplier, Supplier.class);
    }

    /**
     * Gets formulas.
     *
     * @return the formulas
     */
    public List<Formula> getFormulas() {
        return formulas;
    }

    /**
     * Sets formulas.
     *
     * @param formulas the formulas
     */
    public void setFormulas(final List<Formula> formulas) {
        this.formulas = formulas;
    }

    /**
     * Gets pojo formulas.
     *
     * @return the pojo formulas
     */
    public List<FormulaPOJO> getPOJOFormulas() {
        return ParseTools.parseList(formulas, FormulaPOJO.class);
    }

    /**
     * Sets pojo formulas.
     *
     * @param formulas the formulas
     */
    public void setPOJOFormulas(final List<FormulaPOJO> formulas) {
        this.formulas = ParseTools.parseList(formulas, Formula.class);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !ResourcePOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ResourcePOJO other = (ResourcePOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.name
                                                                    == null
                                                                    && other.name
                                                                       == null)
               || (this.name != null && this.name.equals(other.name)) && (this.category == null
                                                                          && other.category == null)
               || (this.category != null && this.category.equals(other.category)) && (this.description == null
                                                                                      && other.description == null)
               || (this.description != null && this.description.equals(other.description)) && (this.formulas == null
                                                                                               && other.formulas
                                                                                                  == null)
               || (this.formulas != null && this.formulas.equals(other.formulas)) && (this.image == null
                                                                                      && other.image == null)
               || (this.image != null && this.image.equals(other.image)) && (this.quantity == null
                                                                             && other.quantity == null)
               || (this.quantity != null && this.quantity.equals(other.quantity)) && (this.supplier == null
                                                                                      && other.supplier == null)
               || (this.supplier != null && this.supplier.equals(other.supplier)) && (this.unit == null
                                                                                      && other.unit == null)
               || (this.unit != null && this.unit.equals(other.unit));
    }

    private String name;
    private String description;
    private Unit unit;
    private Double quantity;
    private Double critical_value;
    private Boolean notifications_on;
    private Boolean ordering_on;
    private ResourceCategory category;
    private String image;
    private Supplier supplier;
    private List<Formula> formulas;
}
