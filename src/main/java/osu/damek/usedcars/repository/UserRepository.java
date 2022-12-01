package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Role;
import osu.damek.usedcars.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    void deleteUserById(Long id);
    Optional<User> getUserById(Long id);

    List<User> getAllByRoleU(Role roleU);
    Optional<User> getByUsername(String username);
    boolean existsByUsername(String username);
}
