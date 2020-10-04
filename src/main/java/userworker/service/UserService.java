package userworker.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import userworker.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    public User add(User user);
    public User delete(User user);
    public User update(User user);

    public List<User> getAll();
    public User getById(long id);
}
