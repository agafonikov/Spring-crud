package userworker.dao;

import userworker.model.User;

import java.util.List;

public interface UserDao {
    public void add(User user);
    public void delete(User user);
    public void update(User user);

    public List<User> getAll();
    public User getById(long id);
    public User getByUsername(String username);
}
