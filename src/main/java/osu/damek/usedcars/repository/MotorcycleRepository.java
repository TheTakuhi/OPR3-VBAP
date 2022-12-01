package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Motorcycle;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    void deleteMotorcycleById(Long id);
    Optional<Motorcycle> findMotorcycleById(Long id);
    List<Motorcycle> findAllByTagsId(Long tagId);
    List<Motorcycle> findAllByUserId(Long userId);
}
