package osu.damek.usedcars.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import osu.damek.usedcars.model.User;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    User getUserById(Long userId);

    User getLoggedUser();

    User saveUser(User user);

    Boolean existsByUserId(Long userId);

    Boolean existsByUsername(String username);

    ResponseEntity<Object> getAllUsers();

    ResponseEntity<Object> getUserDataByUserId(Long userId);
}
