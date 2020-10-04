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
    public User add(User user) {
        return dao.add(user);
    }

    @Override
    public User delete(User user) {
        return dao.delete(user);
    }

    @Override
    public User update(User user) {
        return dao.update(user);
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
