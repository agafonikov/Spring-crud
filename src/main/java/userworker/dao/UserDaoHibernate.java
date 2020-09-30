package userworker.dao;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import userworker.model.User;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(isolation = Isolation.REPEATABLE_READ)
public class UserDaoHibernate implements UserDao{

    @PersistenceContext
    EntityManager entityManager;

    public UserDaoHibernate(){}

    @Override
    public void add(User user){
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);
    }

    @Override
    public void update(User user){
        entityManager.merge(user);
    }

    @Override
    public List<User> getAll(){
        return entityManager
                .createQuery("from User ")
                .getResultList();
    }

    @Override
    public User getByUsername(String username){
        return (User) entityManager.
                createQuery("from User where username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User getById(long id){
        return (User) entityManager
                .createQuery("from User where id=:id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
