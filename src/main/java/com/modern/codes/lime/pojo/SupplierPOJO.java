package com.modern.codes.lime.pojo;

import java.util.List;

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Supplier pojo.
 */
public class SupplierPOJO extends BasicPOJO {

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
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress the email address
     */
    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets postal code.
     *
     * @return the postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets postal code.
     *
     * @param postalCode the postal code
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(final String street) {
        this.street = street;
    }

    /**
     * Gets telephone.
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets telephone.
     *
     * @param telephone the telephone
     */
    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

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
