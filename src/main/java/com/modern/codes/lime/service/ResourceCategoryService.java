package com.modern.codes.lime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IResourceCategoryDAO;
import com.modern.codes.lime.model.ResourceCategory;
import com.modern.codes.lime.pojo.ResourceCategoryPOJO;
import com.modern.codes.lime.tools.ParseTools;

@Service
public class ResourceCategoryService
        extends BasicCRUDService<ResourceCategory, ResourceCategoryPOJO, IResourceCategoryDAO>
        implements IResourceCategoryService {

    @Autowired
    public ResourceCategoryService(final IResourceCategoryDAO dao) {
        super(dao, ResourceCategory.class, ResourceCategoryPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<ResourceCategoryPOJO> findByName(final String name) {
        return ParseTools.parseList(dao.findByName(name), ResourceCategoryPOJO.class);
    }

    @Override
    public ResourceCategoryPOJO findByResourcesName(final String resourceName) {
        return ParseTools.parse(dao.findByResourcesName(resourceName), ResourceCategoryPOJO.class);
    }

    @Override
    public ResourceCategoryPOJO findByResourcesId(final String resourceId) {
        return ParseTools.parse(dao.findByResourcesId(resourceId), ResourceCategoryPOJO.class);
    }
    private final IResourceCategoryDAO dao;
}