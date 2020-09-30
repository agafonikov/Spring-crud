package userworker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import userworker.dao.UserDao;
import userworker.model.User;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao dao;

    @Override
    public void add(User user) {
        dao.add(user);
    }

    @Override
    public void delete(User user) {
        dao.delete(user);
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public User getById(long id) {
        return dao.getById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return dao.getByUsername(s);
    }
}
