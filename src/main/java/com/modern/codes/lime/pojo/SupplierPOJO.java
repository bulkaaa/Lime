package com.modern.codes.lime.pojo;

import java.util.List;

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.tools.ParseTools;

public class SupplierPOJO extends BasicPOJO {

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(final List<Resource> resources) {
        this.resources = resources;
    }

    public List<ResourcePOJO> getPOJOResources() {
        return ParseTools.parseList(resources, ResourcePOJO.class);
    }

    public void setPOJOResources(final List<ResourcePOJO> resources) {
        this.resources = ParseTools.parseList(resources, Resource.class);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(final Object obj) {
        if ((obj == null) || !SupplierPOJO.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final SupplierPOJO other = (SupplierPOJO) obj;
        return (this.id == null && other.id == null)
               || (this.id != null && this.id.equals(other.id)) && (this.name
                                                                    == null
                                                                    && other.name
                                                                       == null)
               || (this.name != null && this.name.equals(other.name)) && (this.city == null && other.city == null)
               || (this.city != null && this.city.equals(other.city)) && (this.country == null && other.country == null)
               || (this.country != null && this.country.equals(other.country)) && (this.emailAddress == null
                                                                                   && other.emailAddress == null)
               || (this.emailAddress != null && this.emailAddress.equals(other.emailAddress)) && (this.postalCode
                                                                                                  == null
                                                                                                  && other.postalCode
                                                                                                     == null)
               || (this.postalCode != null && this.postalCode.equals(other.postalCode)) && (this.resources == null
                                                                                            && other.resources == null)
               || (this.resources != null && this.resources.equals(other.resources)) && (this.street == null
                                                                                         && other.street == null)
               || (this.street != null && this.street.equals(other.street)) && (this.telephone == null
                                                                                && other.telephone == null)
               || (this.telephone != null && this.telephone.equals(other.telephone));
    }
    private String name;
    private String emailAddress;
    private String postalCode;
    private String city;
    private String country;
    private String street;
    private String telephone;
    private List<Resource> resources;

}
