package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Supplier;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ISupplierDAO extends IBasicCRUDRepository<Supplier, String>, ISupplierCustomDAO{

}