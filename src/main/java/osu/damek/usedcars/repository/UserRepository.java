package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Role;
import osu.damek.usedcars.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(Long userId);

    @Query(
            value = "SELECT * FROM \"users\" WHERE username = :username LIMIT 1",
            nativeQuery = true)
    Optional<User> findUserByUsername(@Param("username") String username);
}
