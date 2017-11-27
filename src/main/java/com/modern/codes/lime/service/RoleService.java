package com.modern.codes.lime.service;

import com.modern.codes.lime.ParseTools;
import com.modern.codes.lime.dao.IRoleDAO;
import com.modern.codes.lime.model.Role;
import com.modern.codes.lime.pojo.RolePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BasicCRUDService<Role, RolePOJO, IRoleDAO> implements IRoleService {

    IRoleDAO dao;
    @Autowired
    public RoleService(IRoleDAO dao) {
        super(dao, Role.class, RolePOJO.class);
        this.dao = dao;
    }

    @Override
    public List<RolePOJO> findByName(String name) {
        return ParseTools.parseList(dao.findByName(name), RolePOJO.class);
    }
}