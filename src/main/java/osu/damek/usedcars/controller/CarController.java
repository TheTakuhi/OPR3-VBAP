package osu.damek.usedcars.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import osu.damek.usedcars.model.Car;
import osu.damek.usedcars.service.CarService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars(){
        List<Car> cars = carService.getAllCars();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable("id") Long id){
        Car car = carService.getCarById(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<List<Car>> getAllByTagId(@PathVariable Long tagId){
        List<Car> cars = carService.getAllByTagId(tagId);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        Car newCar = carService.addCar(car);
        return new ResponseEntity<>(newCar, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car){
        Car updateCar = carService.updateCar(car);
        return new ResponseEntity<>(updateCar, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id){
        carService.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
