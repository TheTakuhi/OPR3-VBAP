package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.NotFoundException;
import osu.damek.usedcars.exception.NotOwnerException;
import osu.damek.usedcars.model.Car;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.CarRepository;
import osu.damek.usedcars.service.CarService;
import osu.damek.usedcars.service.TagService;
import osu.damek.usedcars.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImp implements CarService {
    private final CarRepository carRepository;
    private final TagService tagService;
    private final UserService userService;

    @Autowired
    public CarServiceImp(CarRepository carRepository, TagService tagService, UserService userService) {
        this.carRepository = carRepository;
        this.tagService = tagService;
        this.userService = userService;
    }

    public List<Car> getAllCars(){
//        return carRepository.findAll();
        User currentUser = userService.getCurrentUser();
        return carRepository.getAllByUserId(currentUser.getId());
    }

    public List<Car> getAllByTagId(Long tagId) {
        User currentUser = userService.getCurrentUser();
        return tagService.getTagById(tagId)
                .getCars()
                .stream()
                .filter(note -> note.getUser().getId().equals(currentUser.getId()))
                .collect(Collectors.toList());
//        return carRepository.getAllByTagsId(tagId);
    }

    public Car getCarById(Long id){
        return carRepository.getCarById(id).orElseThrow(() -> new NotFoundException("Car with id: " + id + " was not found"));
    }

    public Car addCar(Car car){
        checkIfOwnedByLoggedUser(car);
        car.setUser(car.getUser());
        car.setTags(
                car.getTags()
                        .stream()
                        .map(tag -> tagService.getTagById(tag.getId()))
                        .collect(Collectors.toList())
        );
        return carRepository.save(car);
    }

    public Car updateCar(Car car){
        tagService.removeUnused();
        return carRepository.save(car);
    }

    public void deleteCar(Long id){
        Car car = getCarById(id);
        checkIfOwnedByLoggedUser(car);

        car.getTags().forEach(tag -> tag.removeCar(car));

        carRepository.deleteCarById(id);
        tagService.removeUnused();
    }

    private void checkIfOwnedByLoggedUser(Car car) {
        User currentUser = userService.getCurrentUser();
        if (!car.getUser().getId().equals(currentUser.getId()))
            throw new NotOwnerException(
                    String.format("User %s does not own car with id %d",
                            currentUser.getUsername(), car.getId()));
    }
}
