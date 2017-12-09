package com.modern.codes.lime.pojo;

import com.modern.codes.lime.model.ResourceCategory;
import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.model.Unit;

import java.util.List;

public class ResourcePOJO extends BasicPOJO{

    private String name;
    private String description;
    private Unit unit;
    private Double quantity;
    private ResourceCategory category;
    private String image;
    private Supplier supplier;
    private List<Formula> formulas;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public ResourceCategory getCategory() {
        return category;
    }

    public void setCategory(ResourceCategory category) {
        this.category = category;
    }
    public ResourceCategoryPOJO getPOJOCategory() {
        return ParseTools.parse(category, ResourceCategoryPOJO.class);
    }

    public void setPOJOCategory(ResourceCategoryPOJO category) {
        this.category = ParseTools.parse(category, ResourceCategory.class);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public SupplierPOJO getPOJOSupplier() {
        return ParseTools.parse(supplier, SupplierPOJO.class);
    }

    public void setPOJOSupplier(SupplierPOJO supplier) {
        this.supplier = ParseTools.parse(supplier, Supplier.class);
    }


    public List<Formula> getFormulas() {
        return formulas;
    }

    public void setFormulas(List<Formula> formulas) {
        this.formulas = formulas;
    }

    public List<FormulaPOJO> getPOJOFormulas() {
        return ParseTools.parseList(formulas, FormulaPOJO.class);
    }

    public void setPOJOFormulas(List<FormulaPOJO> formulas) {
        this.formulas = ParseTools.parseList(formulas, Formula.class);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !ResourcePOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ResourcePOJO other = (ResourcePOJO) obj;
        return  (this.id == null && other.id == null) ||
                (this.id != null && this.id.equals(other.id)) &&
                (this.name == null && other.name == null) ||
                (this.name != null && this.name.equals(other.name)) &&
                (this.category == null && other.category == null) ||
                (this.category != null && this.category.equals(other.category)) &&
                (this.description == null && other.description == null) ||
                (this.description != null && this.description.equals(other.description)) &&
                (this.formulas == null && other.formulas == null) ||
                (this.formulas != null && this.formulas.equals(other.formulas)) &&
                (this.image == null && other.image == null) ||
                (this.image != null && this.image.equals(other.image)) &&
                (this.quantity == null && other.quantity == null) ||
                (this.quantity != null && this.quantity.equals(other.quantity)) &&
                (this.supplier == null && other.supplier == null) ||
                (this.supplier != null && this.supplier.equals(other.supplier)) &&
                (this.unit == null && other.unit == null) ||
                (this.unit != null && this.unit.equals(other.unit));
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 53 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
}
