package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResourceDAO extends IBasicCRUDRepository<Resource, String> {
    List<Resource> findByName(final String name);
    List<Resource> findByCategoryName(final String categoryName);
    List<Resource> findBySupplierId(final String supplierId);
    List<Resource> findByCategoryId(final String categoryId);
}
