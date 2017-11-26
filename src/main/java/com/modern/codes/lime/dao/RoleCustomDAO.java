package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Role;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class RoleCustomDAO implements IRoleCustomDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoleByName(String name) {
        String hql = "FROM Role as o WHERE o.name = ?";
        return entityManager.createQuery(hql, Role.class)
                .setParameter(1, name)
                .getResultList();
    }
}