package osu.damek.usedcars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.CarNotFoundException;
import osu.damek.usedcars.model.Car;
import osu.damek.usedcars.repository.CarRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car){
        car.setBrand("BMW");
        return carRepository.save(car);
    }

    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    public Car updateCar(Car car){
        return carRepository.save(car);
    }

    public Car findCarById(Long id){
        return carRepository.findCarById(id).orElseThrow(() -> new CarNotFoundException("Car with id: " + id + " was not found"));
    }

    public void deleteCar(Long id){
        carRepository.deleteCarById(id);
    }
}
