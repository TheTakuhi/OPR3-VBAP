package osu.damek.usedcars.service;

import osu.damek.usedcars.model.Car;

import java.util.List;

public interface CarService {
    List<Car> getAllCars();
    List<Car> getAllByTagId(Long tagId);
    Car getCarById(Long id);
    Car addCar(Car car);
    Car updateCar(Car car);
    void deleteCar(Long id);
}
