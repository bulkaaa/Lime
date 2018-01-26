package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISupplierDAO extends IBasicCRUDRepository<Supplier, String> {
    List<Supplier> findByEmailAddress(final String emailAddress);

    List<Supplier> findByPostalCode(final String postalCode);

    List<Supplier> findByCity(final String city);

    List<Supplier> findByCountry(final String country);

    List<Supplier> findByName(final String name);

    List<Supplier> findByTelephone(final String telephone);
}