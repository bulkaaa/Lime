package com.modern.codes.lime.service;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.dao.IResourceCategoryDAO;
import com.modern.codes.lime.model.ResourceCategory;
import com.modern.codes.lime.pojo.ResourceCategoryPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResourceCategoryService extends BasicCRUDService<ResourceCategory, ResourceCategoryPOJO, IResourceCategoryDAO> implements IResourceCategoryService {

    private IResourceCategoryDAO dao;
    @Autowired
    public ResourceCategoryService(IResourceCategoryDAO dao) {
        super(dao, ResourceCategory.class, ResourceCategoryPOJO.class);
        this.dao = dao;
    }

    @Override
    public List<ResourceCategoryPOJO> findByName(String name)
    {
        return ParseTools.parseList(dao.findByName(name), ResourceCategoryPOJO.class);
    }

    @Override
    public ResourceCategoryPOJO findByResourcesName(String resourceName) {
        return ParseTools.parse(dao.findByResourcesName(resourceName), ResourceCategoryPOJO.class);
    }

    @Override
    public ResourceCategoryPOJO findByResourcesId(String resourceId) {
        return ParseTools.parse(dao.findByResourcesId(resourceId), ResourceCategoryPOJO.class);
    }
}