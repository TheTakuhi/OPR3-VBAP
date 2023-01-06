package osu.damek.usedcars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osu.damek.usedcars.model.Motorcycle;
import osu.damek.usedcars.service.MotorcycleService;

@RestController
@RequestMapping("/api/motorcycles/")
@CrossOrigin
public class MotorcycleController {
    @Autowired
    private MotorcycleService motorcycleService;

    @GetMapping("all")
    ResponseEntity<Object> getAll() {
        return motorcycleService.getAllMotorcycles();
    }

    @GetMapping("all/{userId}")
    ResponseEntity<Object> getAllByUserId(@PathVariable("userId") Long userId) {
        return motorcycleService.getAllMotorcyclesByUserId(userId);
    }

    @GetMapping("get/{motorcycleId}")
    ResponseEntity<Object> getMotorcycleById(@PathVariable("motorcycleId") Long motorcycleId) {
        return motorcycleService.getMotorcycleById(motorcycleId);
    }

    @PostMapping(value = "add/{userId}")
    ResponseEntity<Object> addMotorcycle(@RequestBody Motorcycle motorcycle, @PathVariable("userId") Long userId) {
        return motorcycleService.addMotorcycle(motorcycle, userId);
    }

    @PutMapping(value = "edit/{motorcycleId}")
    ResponseEntity<Object> updateMotorcycle(@RequestBody Motorcycle newMotorcycle, @PathVariable("motorcycleId") Long motorcycleId) {
        return motorcycleService.updateMotorcycle(newMotorcycle, motorcycleId);
    }

    @DeleteMapping(value = "delete/{motorcycleId}")
    ResponseEntity<Object> deleteMotorcycle(@PathVariable("motorcycleId") Long motorcycleId) {
        return motorcycleService.deleteMotorcycle(motorcycleId);
    }

}
