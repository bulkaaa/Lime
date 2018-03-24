package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Resource;

@Repository
public interface IResourceDAO extends IBasicCRUDRepository<Resource, String> {
    List<Resource> findByName(final String name);

    List<Resource> findByCategoryName(final String categoryName);

    List<Resource> findBySupplierId(final String supplierId);

    List<Resource> findByCategoryId(final String categoryId);
}
