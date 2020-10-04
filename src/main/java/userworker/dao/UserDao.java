package userworker.dao;

import userworker.model.User;

import java.util.List;

public interface UserDao {
    public User add(User user);
    public User delete(User user);
    public User update(User user);

    public List<User> getAll();
    public User getById(long id);
    public User getByUsername(String username);
}
