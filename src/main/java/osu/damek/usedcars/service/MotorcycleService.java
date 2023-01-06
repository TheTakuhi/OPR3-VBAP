package osu.damek.usedcars.service;

import org.springframework.http.ResponseEntity;
import osu.damek.usedcars.model.Motorcycle;

public interface MotorcycleService {
    Boolean existsByMotorcycleId(Long motorcycleId);

    Motorcycle findMotorcycleByMotorcycleId(Long motorcycleId);

    ResponseEntity<Object> getAllMotorcycles();

    ResponseEntity<Object> getAllMotorcyclesByUserId(Long userId);

    ResponseEntity<Object> getMotorcycleById(Long motorcycleId);

    ResponseEntity<Object> addMotorcycle(Motorcycle motorcycle, Long userId);

    ResponseEntity<Object> updateMotorcycle(Motorcycle motorcycle, Long motorcycleId);

    ResponseEntity<Object> deleteMotorcycle(Long motorcycleId);
}
