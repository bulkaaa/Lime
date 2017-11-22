package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISupplierDAO extends IBasicCRUDRepository<Supplier, String>{
    List<Supplier> getByEmailAddress(String emailAddress);

    List<Supplier> getByPostalCode(String postalCode);

    List<Supplier> getByCity(String city);

    List<Supplier> getByCountry(String country);

    List<Supplier> getByName(String name);

    List<Supplier> getByTelephone(String telephone);
}