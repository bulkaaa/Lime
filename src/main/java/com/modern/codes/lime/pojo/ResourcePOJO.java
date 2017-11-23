package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Formula;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.model.Unit;

public class ResourcePOJO extends BasicPOJO{

    private String name;
    private String description;
    private Unit unit;
    private Integer quantity;
    private String category;
    private String image;
    private Supplier supplier;

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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Supplier getDBSupplier() {
        return supplier;
    }

    public void setDBSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public SupplierPOJO getSupplier() {
        return ParseTools.parse(supplier);
    }

    public void setSupplier(SupplierPOJO supplier) {
        this.supplier = ParseTools.parse(supplier);
    }


    public Formula getFormula() {
        return formula;
    }

    public void setFormula(Formula formula) {
        this.formula = formula;
    }

    private Formula formula;
}
