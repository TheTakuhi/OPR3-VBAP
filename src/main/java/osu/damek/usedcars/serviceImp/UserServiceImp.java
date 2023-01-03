package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.UserRepository;
import osu.damek.usedcars.service.UserService;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + userId + " not found"));
    }

    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        return (User) loadUserByUsername(username);
    }

    public User getCurrentUser() {
        String username = getLoggedUser().getUsername();

        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> {
                    String message = String.format("Invalid user authenticated. User with username \"%s\" doesn't exists.", username);
                    throw new IllegalStateException(message);
                });
    }
}
