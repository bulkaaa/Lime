package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Supplier;

/**
 * The interface Supplier dao.
 */
@Repository
public interface ISupplierDAO extends IBasicCRUDRepository<Supplier, String> {
    /**
     * Find by email address list.
     *
     * @param emailAddress the email address
     * @return the list
     */
    List<Supplier> findByEmailAddress(final String emailAddress);

    /**
     * Find by postal code list.
     *
     * @param postalCode the postal code
     * @return the list
     */
    List<Supplier> findByPostalCode(final String postalCode);

    /**
     * Find by city list.
     *
     * @param city the city
     * @return the list
     */
    List<Supplier> findByCity(final String city);

    /**
     * Find by country list.
     *
     * @param country the country
     * @return the list
     */
    List<Supplier> findByCountry(final String country);

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Supplier> findByName(final String name);

    /**
     * Find by telephone list.
     *
     * @param telephone the telephone
     * @return the list
     */
    List<Supplier> findByTelephone(final String telephone);
}