package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IResourceDAO;
import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.pojo.ResourcePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService extends BasicCRUDService<Resource, ResourcePOJO, IResourceDAO> implements IResourceService {

    @Autowired
    IResourceDAO dao;
    public ResourceService() {
        super(Resource.class, ResourcePOJO.class);
    }
}