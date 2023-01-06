package osu.damek.usedcars.service;

import org.springframework.http.ResponseEntity;
import osu.damek.usedcars.model.Car;

public interface CarService {
    Boolean existsByCarId(Long carId);

    Car findCarByCarId(Long carId);

    ResponseEntity<Object> getAllCars();

    ResponseEntity<Object> getAllCarsByUserId(Long userId);

    ResponseEntity<Object> getCarById(Long carId);

    ResponseEntity<Object> addCar(Car car, Long userId);

    ResponseEntity<Object> updateCar(Car car, Long carId);

    ResponseEntity<Object> deleteCar(Long carId);
}
