package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Car;
import osu.damek.usedcars.model.Motorcycle;

import java.util.List;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    Motorcycle findByMotorcycleId(Long motorcycleId);
    List<Motorcycle> findAllByUserUserId(Long userId);
}
