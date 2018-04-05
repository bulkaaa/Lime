package com.modern.codes.lime.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.pojo.SupplierPOJO;

/**
 * The interface Supplier service.
 */
public interface ISupplierService {
    /**
     * Find all list.
     *
     * @return the list
     */
    List<SupplierPOJO> findAll();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(String id);

    /**
     * Save supplier pojo.
     *
     * @param t the t
     * @return the supplier pojo
     */
    SupplierPOJO save(Object t);

    /**
     * Exists boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean exists(String id);

    /**
     * Exists boolean.
     *
     * @param t the t
     * @return the boolean
     */
    boolean exists(Object t);

    /**
     * Count long.
     *
     * @return the long
     */
    long count();

    /**
     * Equals boolean.
     *
     * @param t the t
     * @param y the y
     * @return the boolean
     */
    boolean equals(Object t, Object y);

    /**
     * Delete all.
     */
    void deleteAll();

    /**
     * Find by id supplier pojo.
     *
     * @param id the id
     * @return the supplier pojo
     */
    SupplierPOJO findById(String id);

    /**
     * Delete.
     *
     * @param t the t
     */
    void delete(Object t);

    /**
     * Save list.
     *
     * @param l the l
     * @return the list
     */
    List<SupplierPOJO> save(List l);

    /**
     * Delete.
     *
     * @param l the l
     */
    void delete(List l);

    /**
     * Find by email address list.
     *
     * @param emailAddress the email address
     * @return the list
     */
    List<SupplierPOJO> findByEmailAddress(String emailAddress);

    /**
     * Find by postal code list.
     *
     * @param postalCode the postal code
     * @return the list
     */
    List<SupplierPOJO> findByPostalCode(String postalCode);

    /**
     * Find by city list.
     *
     * @param city the city
     * @return the list
     */
    List<SupplierPOJO> findByCity(String city);

    /**
     * Find by country list.
     *
     * @param country the country
     * @return the list
     */
    List<SupplierPOJO> findByCountry(String country);

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<SupplierPOJO> findByName(String name);

    /**
     * Find by telephone list.
     *
     * @param telephone the telephone
     * @return the list
     */
    List<SupplierPOJO> findByTelephone(String telephone);

    /**
     * Filter by name list.
     *
     * @param list the list
     * @param name the name
     * @return the list
     */
    static List<SupplierPOJO> filterByName(final List<SupplierPOJO> list, final String name) {
        return filterByName(list.stream(), name).collect(Collectors.toList());
    }

    /**
     * Filter by name stream.
     *
     * @param stream the stream
     * @param name   the name
     * @return the stream
     */
    static Stream<SupplierPOJO> filterByName(final Stream<SupplierPOJO> stream, final String name) {
        return stream.filter(t -> t.getName()
                                   .equals(name));
    }

    /**
     * Filter by city list.
     *
     * @param list the list
     * @param city the city
     * @return the list
     */
    static List<SupplierPOJO> filterByCity(final List<SupplierPOJO> list, final String city) {
        return filterByCity(list.stream(), city).collect(Collectors.toList());
    }

    /**
     * Filter by city stream.
     *
     * @param stream the stream
     * @param city   the city
     * @return the stream
     */
    static Stream<SupplierPOJO> filterByCity(final Stream<SupplierPOJO> stream, final String city) {
        return stream.filter(t -> t.getCity()
                                   .equals(city));
    }

    /**
     * Filter by email address list.
     *
     * @param list         the list
     * @param emailAddress the email address
     * @return the list
     */
    static List<SupplierPOJO> filterByEmailAddress(final List<SupplierPOJO> list, final String emailAddress) {
        return filterByEmailAddress(list.stream(), emailAddress).collect(Collectors.toList());
    }

    /**
     * Filter by email address stream.
     *
     * @param stream       the stream
     * @param emailAddress the email address
     * @return the stream
     */
    static Stream<SupplierPOJO> filterByEmailAddress(final Stream<SupplierPOJO> stream, final String emailAddress) {
        return stream.filter(t -> t.getEmailAddress()
                                   .equals(emailAddress));
    }

    /**
     * Filter by telephone list.
     *
     * @param list      the list
     * @param telephone the telephone
     * @return the list
     */
    static List<SupplierPOJO> filterByTelephone(final List<SupplierPOJO> list, final String telephone) {
        return filterByTelephone(list.stream(), telephone).collect(Collectors.toList());
    }

    /**
     * Filter by telephone stream.
     *
     * @param stream    the stream
     * @param telephone the telephone
     * @return the stream
     */
    static Stream<SupplierPOJO> filterByTelephone(final Stream<SupplierPOJO> stream, final String telephone) {
        return stream.filter(t -> t.getTelephone()
                                   .equals(telephone));
    }

    /**
     * Filter by country list.
     *
     * @param list    the list
     * @param country the country
     * @return the list
     */
    static List<SupplierPOJO> filterByCountry(final List<SupplierPOJO> list, final String country) {
        return filterByCountry(list.stream(), country).collect(Collectors.toList());
    }

    /**
     * Filter by country stream.
     *
     * @param stream  the stream
     * @param country the country
     * @return the stream
     */
    static Stream<SupplierPOJO> filterByCountry(final Stream<SupplierPOJO> stream, final String country) {
        return stream.filter(t -> t.getCountry()
                                   .equals(country));
    }

    /**
     * Filter by resource list.
     *
     * @param list         the list
     * @param resourceName the resource name
     * @return the list
     */
    static List<SupplierPOJO> filterByResource(final List<SupplierPOJO> list, final String resourceName) {
        return filterByResource(list.stream(), resourceName).collect(Collectors.toList());
    }

    /**
     * Filter by resource stream.
     *
     * @param stream       the stream
     * @param resourceName the resource name
     * @return the stream
     */
    static Stream<SupplierPOJO> filterByResource(final Stream<SupplierPOJO> stream, final String resourceName) {
        return stream.filter(t -> !IResourceService.filterByName(t.getPOJOResources(), resourceName)
                                                   .isEmpty());
    }

    /**
     * Filter by resource list.
     *
     * @param list     the list
     * @param resource the resource
     * @return the list
     */
    static List<SupplierPOJO> filterByResource(final List<SupplierPOJO> list, final ResourcePOJO resource) {
        return filterByResource(list.stream(), resource).collect(Collectors.toList());
    }

    /**
     * Filter by resource stream.
     *
     * @param stream   the stream
     * @param resource the resource
     * @return the stream
     */
    static Stream<SupplierPOJO> filterByResource(final Stream<SupplierPOJO> stream, final ResourcePOJO resource) {
        return stream.filter(t -> t.getPOJOResources()
                                   .contains(resource));
    }
}