package com.modern.codes.lime.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.modern.codes.lime.model.Role;

/**
 * The interface Role dao.
 */
@Repository
public interface IRoleDAO extends IBasicCRUDRepository<Role, String> {
    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Role> findByName(final String name);
}
