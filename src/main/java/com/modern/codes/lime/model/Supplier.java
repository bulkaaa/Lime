package com.modern.codes.lime.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Model representation of a supplier in Lime.
 *
 * @author jaroszk
 *
 */
@ApiModel(description = "Model representation of a supplier used in Lime")
@Entity
public class Supplier implements Serializable{

    private static final long serialVersionUID = 8269473897901383432L;

    public static final int MAX_LENGTH_NAME = 50;
    public static final int MAX_LENGTH_POSTAL_CODE = 10;
    public static final int MAX_LENGTH_CITY = 40;
    public static final int MAX_LENGTH_STREET = 35;
    public static final int MAX_LENGTH_COUNTRY = 3;
    public static final int MAX_LENGTH_TELEPHONE = 30;
    public static final int MAX_LENGTH_EMAIL = 240;

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

    @ApiModelProperty(value = "Country the supplier.", required = true, allowableValues = "PL")
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
    private List<Resource> resources;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
