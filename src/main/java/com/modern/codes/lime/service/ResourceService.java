package com.modern.codes.lime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IResourceDAO;
import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.pojo.ResourcePOJO;
import com.modern.codes.lime.tools.ParseTools;

/**
 * The type Resource service.
 */
@Service
public class ResourceService extends BasicCRUDService<Resource, ResourcePOJO, IResourceDAO>
        implements IResourceService {

    /**
     * Instantiates a new Resource service.
     *
     * @param dao the dao
     */
    @Autowired
    public ResourceService(final IResourceDAO dao) {
        super(dao, Resource.class, ResourcePOJO.class);
        this.dao = dao;
    }

    @Override
    public List<ResourcePOJO> findByName(final String name) {
        return ParseTools.parseList(dao.findByName(name), ResourcePOJO.class);
    }

    @Override
    public List<ResourcePOJO> findByCategoryName(final String categoryName) {
        return ParseTools.parseList(dao.findByCategoryName(categoryName), ResourcePOJO.class);
    }

    @Override
    public List<ResourcePOJO> findBySupplierId(final String supplierId) {
        return ParseTools.parseList(dao.findBySupplierId(supplierId), ResourcePOJO.class);
    }

    @Override
    public List<ResourcePOJO> findByCategoryId(final String categoryId) {
        return ParseTools.parseList(dao.findByCategoryId(categoryId), ResourcePOJO.class);
    }

    private final IResourceDAO dao;
}