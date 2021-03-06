package com.modern.codes.lime.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model representation of a supplier in Lime.
 *
 * @author jaroszk
 */
@ApiModel(description = "Model representation of a supplier used in Lime")
@Entity
public class Supplier implements Serializable {

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(final String id) {
        this.id = id;
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

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @ApiModelProperty(value = "The unqiue id of the supplier", required = true)
    private String id;

    @ApiModelProperty(value = "The name of the supplier.", required = true)
    @NotNull
    @Size(max = MAX_LENGTH_NAME)
    private String name;

    @ApiModelProperty(value = "The E-Mail address of the supplier.", required = true)
    @NotEmpty
    @Email
    @Size(max = MAX_LENGTH_EMAIL)
    private String emailAddress;

    @ApiModelProperty(value = "ZIP code of the supplier.", required = true)
    @NotEmpty
    @Size(max = MAX_LENGTH_POSTAL_CODE)
    //@Pattern("\d{2}-\d{3}")
    private String postalCode;

    @ApiModelProperty(value = "City of the supplier.", required = true)
    @NotEmpty
    @Size(max = MAX_LENGTH_CITY)
    private String city;

    @ApiModelProperty(value = "Country the supplier.", required = true)
    @NotEmpty
    @Size(max = MAX_LENGTH_COUNTRY)
    private String country;

    @ApiModelProperty(value = "Street of the supplier.", required = true)
    @NotEmpty
    @Size(max = MAX_LENGTH_STREET)
    private String street;

    @ApiModelProperty(value = "The telephone number of supplier.", required = true)
    @NotEmpty
    @Size(max = MAX_LENGTH_TELEPHONE)
    private String telephone;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Resource> resources;

    private static final long serialVersionUID = 8269473897901383432L;
    private static final int MAX_LENGTH_NAME = 50;
    private static final int MAX_LENGTH_POSTAL_CODE = 10;
    private static final int MAX_LENGTH_CITY = 40;
    private static final int MAX_LENGTH_STREET = 35;
    private static final int MAX_LENGTH_COUNTRY = 100;
    private static final int MAX_LENGTH_TELEPHONE = 30;
    private static final int MAX_LENGTH_EMAIL = 240;
}
