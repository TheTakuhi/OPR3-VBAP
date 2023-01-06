package osu.damek.usedcars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osu.damek.usedcars.model.Car;
import osu.damek.usedcars.service.CarService;

@RestController
@RequestMapping("/api/cars/")
@CrossOrigin
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("all")
    ResponseEntity<Object> getAll() {
        return carService.getAllCars();
    }

    @GetMapping("all/{userId}")
    ResponseEntity<Object> getAllByUserId(@PathVariable("userId") Long userId) {
        return carService.getAllCarsByUserId(userId);
    }

    @GetMapping("get/{carId}")
    ResponseEntity<Object> getCarById(@PathVariable("carId") Long carId) {
        return carService.getCarById(carId);
    }

    @PostMapping(value = "add/{userId}")
    ResponseEntity<Object> addCar(@RequestBody Car car, @PathVariable("userId") Long userId) {
        return carService.addCar(car, userId);
    }

    @PutMapping(value = "edit/{carId}")
    ResponseEntity<Object> updateCar(@RequestBody Car newCar, @PathVariable("carId") Long carId) {
        return carService.updateCar(newCar, carId);
    }

    @DeleteMapping(value = "delete/{carId}")
    ResponseEntity<Object> deleteCar(@PathVariable("carId") Long carId) {
        return carService.deleteCar(carId);
    }
}
