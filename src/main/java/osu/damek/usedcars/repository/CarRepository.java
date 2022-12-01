package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    void deleteCarById(Long id);
    Optional<Car> findCarById(Long id);
    List<Car> findAllByTagsId(Long tagId);
    List<Car> findAllByUserId(Long userId);
}
