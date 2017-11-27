package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Supplier;

import java.util.List;

public interface ISupplierCustomDAO {
    List<Supplier> findByEmailAddress(String emailAddress);

    List<Supplier> findByPostalCode(String postalCode);

    List<Supplier> findByCity(String city);

    List<Supplier> findByCountry(String country);

    List<Supplier> findByName(String name);

    List<Supplier> findByTelephone(String telephone);
}
