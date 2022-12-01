package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    void deleteCarById(Long id);
    Optional<Car> getCarById(Long id);
    List<Car> getAllByTagsId(Long tagId);
    List<Car> getAllByUserId(Long userId);
}
