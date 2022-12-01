package osu.damek.usedcars.serviceImp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.NotFoundException;
import osu.damek.usedcars.exception.NotOwnerException;
import osu.damek.usedcars.model.Role;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.UserRepository;
import osu.damek.usedcars.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
    private static Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public List<User> getAllByRole(Role role) { return userRepository.getAllByRoleU(role); }

    public User getUserById(Long id){
        return userRepository.getUserById(id).orElseThrow(() -> new NotFoundException("User with id: " + id + " was not found"));
    }

    public User addUser(User user){
        if(userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException("User with username \"" + user.getUsername() + "\" already exists");
        user.setRole(Role.USER);
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteUserById(id);
    }

    public User getCurrentUser() {
        String username = getLoggedUsername();

        return userRepository.getByUsername(username)
                .orElseThrow(() -> {
                    String message = String.format("Invalid user authenticated. User with username \"%s\" doesn't exists.", username);
                    log.error(message);
                    throw new IllegalStateException(message);
                });
    }

    public String getLoggedUsername() {
        return ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username \"" + username + "\" was not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>(List.of(new SimpleGrantedAuthority(user.getRoleU().toString())))
        );
    }

    private User checkIfLoggedUserOwnsAccount(User account) {
        if (account.getUsername().equals(getLoggedUsername()))
            return account;

        User loggedUser = getCurrentUser();
        if (loggedUser.getRoleU().equals(Role.ADMIN))
            return loggedUser;
        else
            throw new NotOwnerException(
                    String.format("User %s is not permitted to manipulate with account with id %d",
                            loggedUser.getUsername(), account.getId()));
    }
}
