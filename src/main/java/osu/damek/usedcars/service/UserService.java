package osu.damek.usedcars.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import osu.damek.usedcars.model.Role;
import osu.damek.usedcars.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();
    List<User> getAllByRole(Role role);
    User getUserById(Long id);
    User addUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    User getCurrentUser();
    String getLoggedUsername();
}
