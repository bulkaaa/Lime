package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.ISupplierDAO;
import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.pojo.SupplierPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService extends BasicCRUDService<Supplier, SupplierPOJO, ISupplierDAO> implements ISupplierService {

    ISupplierDAO dao;
    @Autowired
    public SupplierService(ISupplierDAO dao) {
        super(dao, Supplier.class, SupplierPOJO.class);
        this.dao = dao;
    }
}