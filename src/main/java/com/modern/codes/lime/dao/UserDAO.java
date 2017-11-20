package com.modern.codes.lime.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.modern.codes.lime.model.User;
@Transactional
@Repository
public class UserDAO implements IUserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAllUsers() {
        String hql = "FROM Article as usr ORDER BY usr.id";
        return (List<User>) entityManager.createQuery(hql).getResultList();
    }

    @Override
    public User getUserById(String userId) {
        return entityManager.find(User.class, userId);
    }
    @Override
    public User getUserByName(String name) {
        String hql = "FROM User as usr WHERE usr.name = ?";
        return entityManager.createQuery(hql, User.class).setParameter(1, name).getSingleResult();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        User usr = getUserById(user.getId());
        usr.setName(user.getName());
        usr.setDescription(user.getDescription());
        entityManager.flush();
    }

    @Override
    public void deleteUser(String userName) {
        entityManager.remove(getUserByName(userName));
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(user);
    }

    @Override
    public boolean userExists(String userName) {
        String hql = "FROM User as usr WHERE usr.name = ?";
        int count = entityManager.createQuery(hql).setParameter(1, userName).getResultList().size();
        return count > 0;
    }
}
