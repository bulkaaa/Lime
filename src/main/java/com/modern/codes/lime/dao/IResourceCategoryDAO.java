package com.modern.codes.lime.dao;
import com.modern.codes.lime.model.ResourceCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResourceCategoryDAO extends IBasicCRUDRepository<ResourceCategory, String> {
    List<ResourceCategory> findByName(String name);
    ResourceCategory findByResourcesName(String resourceName);
    ResourceCategory findByResourcesId(String resourceId);
}