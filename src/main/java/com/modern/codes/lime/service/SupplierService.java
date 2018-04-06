package com.modern.codes.lime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.ISupplierDAO;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.pojo.SupplierPOJO;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Supplier service.
 */
@Service
public class SupplierService extends BasicCRUDService<Supplier, SupplierPOJO, ISupplierDAO>
        implements ISupplierService {

    /**
     * Instantiates a new Supplier service.
     *
     * @param dao the dao
     */
    @Autowired
    public SupplierService(final ISupplierDAO dao) {
        super(dao, Supplier.class, SupplierPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<SupplierPOJO> findByEmailAddress(final String emailAddress) {
        return ParseTools.parseList(dao.findByEmailAddress(emailAddress), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByPostalCode(final String postalCode) {
        return ParseTools.parseList(dao.findByPostalCode(postalCode), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByCity(final String city) {
        return ParseTools.parseList(dao.findByCity(city), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByCountry(final String country) {
        return ParseTools.parseList(dao.findByCountry(country), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByName(final String name) {
        return ParseTools.parseList(dao.findByName(name), SupplierPOJO.class);
    }

    @Override
    public List<SupplierPOJO> findByTelephone(final String telephone) {
        return ParseTools.parseList(dao.findByTelephone(telephone), SupplierPOJO.class);
    }

    private final ISupplierDAO dao;
}