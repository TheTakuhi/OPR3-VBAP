package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.model.Motorcycle;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.MotorcycleRepository;
import osu.damek.usedcars.service.MotorcycleService;
import osu.damek.usedcars.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotorcycleServiceImp implements MotorcycleService {
    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private UserService userService;

    @Override
    public Boolean existsByMotorcycleId(Long motorcycleId) {
        return motorcycleRepository.existsById(motorcycleId);
    }

    @Override
    public Motorcycle findMotorcycleByMotorcycleId(Long motorcycleId) {
        return motorcycleRepository.findByMotorcycleId(motorcycleId);
    }

    @Override
    public ResponseEntity<Object> getAllMotorcycles() {
        List<Motorcycle> ret = motorcycleRepository.findAll();
        ret.forEach(motorcycle -> {
            motorcycle.getUser().setCars(null);
            motorcycle.getUser().setTags(null);
            motorcycle.getUser().setMotorcycles(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getAllMotorcyclesByUserId(Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        User user = userService.getUserById(userId);
        List<Motorcycle> motorcycles = motorcycleRepository.findAll();

        List<Motorcycle> ret = new ArrayList<>();
        motorcycles.forEach(motorcycle -> {
            motorcycle.getUser().setCars(null);
            motorcycle.getUser().setTags(null);
            motorcycle.getUser().setMotorcycles(null);
            if (motorcycle.getUser().getUserId().equals(user.getUserId()))
                ret.add(motorcycle);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getMotorcycleById(Long motorcycleId) {
        if (!motorcycleRepository.existsById(motorcycleId))
            return ResponseEntity.notFound().build();

        Motorcycle ret = motorcycleRepository.findByMotorcycleId(motorcycleId);
        ret.setUser(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> addMotorcycle(Motorcycle motorcycle, Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        User user = userService.getUserById(userId);
        motorcycle.setUser(user);
        motorcycleRepository.save(motorcycle);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> updateMotorcycle(Motorcycle newMotorcycle, Long motorcycleId) {
        if (!motorcycleRepository.existsById(motorcycleId))
            return ResponseEntity.notFound().build();

        Motorcycle motorcycle = motorcycleRepository.findByMotorcycleId(motorcycleId);
        motorcycle.update(newMotorcycle);
        motorcycleRepository.save(motorcycle);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteMotorcycle(Long motorcycleId) {
        if (!motorcycleRepository.existsById(motorcycleId))
            return ResponseEntity.notFound().build();

        Motorcycle motorcycle = motorcycleRepository.findByMotorcycleId(motorcycleId);
        motorcycle.setUser(null);
        motorcycleRepository.delete(motorcycle);

        return ResponseEntity.ok().build();
    }
}
