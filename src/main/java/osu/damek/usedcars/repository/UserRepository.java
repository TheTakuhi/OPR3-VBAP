package osu.damek.usedcars.repository;

import osu.damek.usedcars.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findByUserId(Long id);

    Boolean existsByUserId(Long userId);

    Boolean existsByUsername(String username);
}
