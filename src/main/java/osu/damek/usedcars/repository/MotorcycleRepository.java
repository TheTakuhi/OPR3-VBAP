package osu.damek.usedcars.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import osu.damek.usedcars.model.Motorcycle;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    Motorcycle findByMotorcycleId(Long motorcycleId);
}
