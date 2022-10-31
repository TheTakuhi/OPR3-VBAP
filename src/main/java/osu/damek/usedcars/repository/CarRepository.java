package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import osu.damek.usedcars.model.Car;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    void deleteCarById(Long id);
    Optional<Car> findCarById(Long id);
}
