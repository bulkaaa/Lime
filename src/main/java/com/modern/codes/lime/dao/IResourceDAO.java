package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Resource;
import com.modern.codes.lime.model.ResourceCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IResourceDAO extends IBasicCRUDRepository<Resource, String> {
    List<Resource> findByName(String name);
    List<Resource>findByCategoryName(String categoryName);
}
