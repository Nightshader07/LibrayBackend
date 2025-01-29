package main.libraybackend.interfaces;

import main.libraybackend.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    Optional<User> getUserByEmail(String email);
    User saveUser(User user);
    User updateUser(Long id, User userDetails);
    void deleteUser(Long id);
}
