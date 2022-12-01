package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.NotFoundException;
import osu.damek.usedcars.exception.NotOwnerException;
import osu.damek.usedcars.model.Car;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.CarRepository;

import java.util.List;

@Service
public class CarServiceImp {
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImp(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    public List<Car> getAllByTagId(Long tagId) { return carRepository.findAllByTagsId(tagId);}

    public Car updateCar(Car car){
        return carRepository.save(car);
    }

    public Car findCarById(Long id){
        return carRepository.findCarById(id).orElseThrow(() -> new NotFoundException("Car with id: " + id + " was not found"));
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
