package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Supplier;

@Repository
public interface ISupplierDAO extends IBasicCRUDRepository<Supplier, String> {
    List<Supplier> findByEmailAddress(final String emailAddress);

    List<Supplier> findByPostalCode(final String postalCode);

    List<Supplier> findByCity(final String city);

    List<Supplier> findByCountry(final String country);

    List<Supplier> findByName(final String name);

    List<Supplier> findByTelephone(final String telephone);
}