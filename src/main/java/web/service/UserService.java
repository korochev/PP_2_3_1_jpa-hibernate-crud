package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    List<User> index();
    User show(int id);
    void save(User User);
    void update(User updatedUser);
    void delete(int id);
}
