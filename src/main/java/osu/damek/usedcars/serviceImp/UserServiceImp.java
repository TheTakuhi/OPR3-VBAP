package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.UserRepository;
import osu.damek.usedcars.service.UserService;

import java.util.List;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        return (User) loadUserByUsername(username);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean existsByUserId(Long userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public ResponseEntity<Object> getAllUsers() {
        List<User> ret = userRepository.findAll();
        ret.forEach(user -> {
            user.setCars(null);
            user.setMotorcycles(null);
            user.setTags(null);
            user.setPassword(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getUserDataByUserId(Long userId) {
        if (!userRepository.existsById(userId))
            return ResponseEntity.notFound().build();

        User ret = userRepository.findByUserId(userId);
        ret.getTags().forEach(tag -> tag.setUser(null));
        ret.getCars().forEach(car -> car.setUser(null));
        ret.getMotorcycles().forEach(motorcycle -> motorcycle.setUser(null));
        ret.setPassword(null);

        return ResponseEntity.ok(ret);
    }
}
