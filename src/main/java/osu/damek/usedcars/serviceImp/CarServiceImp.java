package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.NotFoundException;
import osu.damek.usedcars.model.Car;
import osu.damek.usedcars.repository.CarRepository;
import osu.damek.usedcars.service.CarService;

import java.util.List;

@Service
public class CarServiceImp implements CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public List<Car> getAllByTagId(Long tagId) { return carRepository.getAllByTagsId(tagId);}

    public Car getCarById(Long id){
        return carRepository.getCarById(id).orElseThrow(() -> new NotFoundException("Car with id: " + id + " was not found"));
    }

    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public Car updateCar(Car car){
        return carRepository.save(car);
    }

    public void deleteCar(Long id){
        carRepository.deleteCarById(id);
    }

//    private void checkIfOwnedByLoggedUser(Car car) {
//        User currentUser = userServiceImp.getCurrentUser();
//        if (!car.getUser().getId().equals(currentUser.getId()))
//            throw new NotOwnerException(
//                    String.format("User %s does not own note with id %d",
//                            currentUser.getUsername(), car.getId()));
//    }
}
