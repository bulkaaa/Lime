package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface IResourceDAO extends IBasicCRUDRepository<Resource, String>, IResourceCustomDAO{
}
