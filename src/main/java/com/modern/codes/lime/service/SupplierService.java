package com.modern.codes.lime.service;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.ISupplierDAO;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.pojo.SupplierPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService extends BasicCRUDService<Supplier, SupplierPOJO, ISupplierDAO> implements ISupplierService {

    ISupplierDAO dao;
    @Autowired
    public SupplierService(ISupplierDAO dao) {
        super(dao, Supplier.class, SupplierPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<SupplierPOJO> findByEmailAddress(String emailAddress) {
        return ParseTools.parseList(dao.findByEmailAddress(emailAddress), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByPostalCode(String postalCode) {
        return ParseTools.parseList(dao.findByPostalCode(postalCode), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByCity(String city) {
        return ParseTools.parseList(dao.findByCity(city), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByCountry(String country) {
        return ParseTools.parseList(dao.findByCountry(country), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByName(String name) {
        return ParseTools.parseList(dao.findByName(name), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByTelephone(String telephone) {
        return ParseTools.parseList(dao.findByTelephone(telephone), SupplierPOJO.class);
    }
}