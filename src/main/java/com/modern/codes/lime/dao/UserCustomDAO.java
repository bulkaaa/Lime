package com.modern.codes.lime.dao;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.modern.codes.lime.model.User;

@Transactional
public class UserCustomDAO implements IUserCustomDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findUserByName(String name, String surname) {
        String hql = "FROM User as usr WHERE usr.name = ?";
        return entityManager.createQuery(hql, User.class)
                .setParameter(1, name)
                .getResultList();
    }

    @Override
    public List<User> findUserBySurname(String surname) {
        String hql = "FROM User as usr WHERE usr.surname = ?";
        return entityManager.createQuery(hql, User.class)
                .setParameter(1, surname)
                .getResultList();
}

    @Override
    public User findUserByLogin(String login) {
        String hql = "FROM User as usr WHERE usr.login = ?";
        return entityManager.createQuery(hql, User.class)
                .setParameter(1, login)
                .getResultList().get(0);
    }

    @Override
    public List<User> findByJoinedAtBetween(Date begin, Date end) {
        String hql = "FROM User as usr WHERE usr.joinedAt >= ? and usr.joinedAt <= ?";
        return entityManager.createQuery(hql, User.class)
                .setParameter(1, begin)
                .setParameter(2, end)
                .getResultList();
    }

    @Override
    public List<User> findUserByNameAndSurname(String name, String surname) {
        String hql = "FROM User as usr WHERE usr.name = ? and usr.surname = ?";
        return entityManager.createQuery(hql, User.class)
                            .setParameter(1, name)
                            .setParameter(2, surname)
                            .getResultList();
    }
}
