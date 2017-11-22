package com.modern.codes.lime.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.modern.codes.lime.model.User;

@Transactional
public class UserDAO implements IUserDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<User> getUserByNameAndSurname(String name, String surname) {
        String hql = "FROM User as usr WHERE usr.name = ? and usr.surname = ?";
        return entityManager.createQuery(hql, User.class)
                            .setParameter(1, name)
                            .setParameter(2, surname)
                            .getResultList();
    }
}
