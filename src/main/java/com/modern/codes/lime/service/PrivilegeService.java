package com.modern.codes.lime.service;

import com.modern.codes.lime.tools.ParseTools;
import com.modern.codes.lime.dao.IPrivilegeDAO;
import com.modern.codes.lime.model.Privilege;
import com.modern.codes.lime.pojo.PrivilegePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegeService extends BasicCRUDService<Privilege, PrivilegePOJO, IPrivilegeDAO> implements IPrivilegeService {

    private IPrivilegeDAO dao;
    @Autowired
    public PrivilegeService(IPrivilegeDAO dao) {
        super(dao, Privilege.class, PrivilegePOJO.class);
        this.dao = dao;
    }

    @Override
    public List<PrivilegePOJO> findByName(String name) {
        return ParseTools.parseList(dao.findByName(name), PrivilegePOJO.class);
    }
}