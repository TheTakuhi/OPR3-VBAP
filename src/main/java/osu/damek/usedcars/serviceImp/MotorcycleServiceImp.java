package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.model.Motorcycle;
import osu.damek.usedcars.model.Tag;
import osu.damek.usedcars.model.User;
import osu.damek.usedcars.repository.MotorcycleRepository;
import osu.damek.usedcars.service.MotorcycleService;
import osu.damek.usedcars.service.TagService;
import osu.damek.usedcars.service.UserService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MotorcycleServiceImp implements MotorcycleService {
    @Autowired
    private MotorcycleRepository motorcycleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

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
            motorcycle.setTags(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> getAllMotorcyclesByUserId(Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        List<Motorcycle> motorcycles = motorcycleRepository.findAllByUserUserId(userId)
                .stream()
                .map(motorcycle -> {
                    motorcycle.setTags(motorcycle.getTags());
                    return motorcycle;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(motorcycles);
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

        Set<Tag> foundTags = new HashSet<>();
        for (Tag tag : motorcycle.getTags()) {
            System.out.println(tag.toString());
            tagService.getTagById(tag.getTagId());
            foundTags.add(tag);
        }

        motorcycle.setTags(foundTags);
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
