package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Privilege;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPrivilegeDAO extends IBasicCRUDRepository<Privilege, String> {
    List<Privilege> findByName(String name);
}
