package osu.damek.usedcars.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import osu.damek.usedcars.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long userId);
    User getLoggedUser();
    User getCurrentUser();
}
