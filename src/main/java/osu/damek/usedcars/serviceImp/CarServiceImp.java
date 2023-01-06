package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.model.Car;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.CarRepository;
import osu.damek.usedcars.service.CarService;
import osu.damek.usedcars.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImp implements CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserService userService;

    @Override
    public Boolean existsByCarId(Long carId) {
        return carRepository.existsById(carId);
    }

    @Override
    public Car findCarByCarId(Long carId) {
        return carRepository.findByCarId(carId);
    }

    @Override
    public ResponseEntity<Object> getAllCars() {
        List<Car> ret = carRepository.findAll();
        ret.forEach(car -> {
            car.getUser().setCars(null);
            car.getUser().setTags(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getAllCarsByUserId(Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        User user = userService.getUserById(userId);
        List<Car> cars = carRepository.findAll();

        List<Car> ret = new ArrayList<>();
        cars.forEach(car -> {
            car.getUser().setCars(null);
            car.getUser().setTags(null);
            if (car.getUser().getUserId().equals(user.getUserId()))
                ret.add(car);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getCarById(Long carId) {
        if (!carRepository.existsById(carId))
            return ResponseEntity.notFound().build();

        Car ret = carRepository.findByCarId(carId);
        ret.setUser(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> addCar(Car car, Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        User user = userService.getUserById(userId);
        car.setUser(user);
        carRepository.save(car);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> updateCar(Car newCar, Long carId) {
        if (!carRepository.existsById(carId))
            return ResponseEntity.notFound().build();

        Car car = carRepository.findByCarId(carId);
        car.update(newCar);
        carRepository.save(car);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteCar(Long carId) {
        if (!carRepository.existsById(carId))
            return ResponseEntity.notFound().build();

        Car car = carRepository.findByCarId(carId);
        car.setUser(null);
        carRepository.delete(car);

        return ResponseEntity.ok().build();
    }
}
