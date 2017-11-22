package com.modern.codes.lime.pojo;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.model.Resource;
import java.util.List;

public class SupplierPOJO extends BasicPOJO{

    private String name;
    private String emailAddress;
    private String postalCode;
    private String city;
    private String country;
    private String street;
    private String telephone;
    private List<Resource> resources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Resource> getDBResources() {
        return resources;
    }

    public void setDBResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<ResourcePOJO> getResources() {
        return ParseTools.parseList(resources, ResourcePOJO.class);
    }

    public void setResources(List<ResourcePOJO> resources) {
        this.resources = ParseTools.parseList(resources, Resource.class);
    }


}
