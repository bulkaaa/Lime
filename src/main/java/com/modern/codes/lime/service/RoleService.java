package com.modern.codes.lime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.modern.codes.lime.dao.IRoleDAO;
import com.modern.codes.lime.model.Role;
import com.modern.codes.lime.pojo.RolePOJO;
import com.modern.codes.lime.tools.ParseTools;

@Service
public class RoleService extends BasicCRUDService<Role, RolePOJO, IRoleDAO> implements IRoleService {

    @Autowired
    public RoleService(final IRoleDAO dao) {
        super(dao, Role.class, RolePOJO.class);
        this.dao = dao;
    }

    @Override
    public List<RolePOJO> findByName(final String name) {
        return ParseTools.parseList(dao.findByName(name), RolePOJO.class);
    }
    private final IRoleDAO dao;
}