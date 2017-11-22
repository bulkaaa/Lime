package com.modern.codes.lime.service;

import com.modern.codes.lime.dao.IPrivilegeDAO;
import com.modern.codes.lime.model.Privilege;
import com.modern.codes.lime.pojo.PrivilegePOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegeService extends BasicCRUDService<Privilege, PrivilegePOJO, IPrivilegeDAO> implements IPrivilegeService {

    @Autowired
    IPrivilegeDAO dao;
    public PrivilegeService() {
        super(Privilege.class, PrivilegePOJO.class);
    }
}