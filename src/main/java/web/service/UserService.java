package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void delete(Long id);
    void update(User user);
    List<User> listUser();
    User getUser(Long id);
}
