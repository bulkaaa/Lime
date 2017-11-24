package com.modern.codes.lime.dao;

import com.modern.codes.lime.model.Supplier;
import com.modern.codes.lime.model.User;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class SupplierCustomDAO implements ISupplierCustomDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Supplier> getByEmailAddress(String emailAddress) {
        String hql = "FROM User as supplier WHERE supplier.email_address = ?";
        return entityManager.createQuery(hql, Supplier.class)
                .setParameter(1, emailAddress).getResultList();
    }

    @Override
    public List<Supplier> getByPostalCode(String postalCode) {
        String hql = "FROM User as supplier WHERE supplier.postal_code = ?";
        return entityManager.createQuery(hql, Supplier.class)
                .setParameter(1, postalCode).getResultList();
    }

    @Override
    public List<Supplier> getByCity(String city) {
        String hql = "FROM User as supplier WHERE supplier.city = ?";
        return entityManager.createQuery(hql, Supplier.class)
                .setParameter(1, city).getResultList();
    }

    @Override
    public List<Supplier> getByCountry(String country) {
        String hql = "FROM User as supplier WHERE supplier.country = ?";
        return entityManager.createQuery(hql, Supplier.class)
                .setParameter(1, country).getResultList();
    }

    @Override
    public List<Supplier> getByName(String name) {
        String hql = "FROM User as supplier WHERE supplier.name = ?";
        return entityManager.createQuery(hql, Supplier.class)
                .setParameter(1, name).getResultList();
    }
    @Override
    public List<Supplier> getByTelephone(String telephone){
        String hql = "FROM User as supplier WHERE supplier.telephone = ?";
        return entityManager.createQuery(hql, Supplier.class)
                .setParameter(1, telephone).getResultList();
    }
}