package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.ResourceCategory;

@Repository
public interface IResourceCategoryDAO extends IBasicCRUDRepository<ResourceCategory, String> {
    List<ResourceCategory> findByName(final String name);

    ResourceCategory findByResourcesName(final String resourceName);

    ResourceCategory findByResourcesId(final String resourceId);
}