package osu.damek.usedcars.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import osu.damek.usedcars.model.Motorcycle;
import osu.damek.usedcars.serviceImp.MotorcycleServiceImp;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/motorcycles")
public class MotorcycleController {
    private final MotorcycleServiceImp motorcycleService;

    public MotorcycleController(MotorcycleServiceImp motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Motorcycle>> getAllMotorcycles(){
        List<Motorcycle> motorcycles = motorcycleService.findAllMotorcycles();
        return new ResponseEntity<>(motorcycles, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Motorcycle> getMotorcycleById(@PathVariable("id") Long id){
        Motorcycle motorcycle = motorcycleService.findMotorcycleById(id);
        return new ResponseEntity<>(motorcycle, HttpStatus.OK);
    }

    @GetMapping("/tags/{id}")
    public ResponseEntity<List<Motorcycle>> getAllByTagId(@PathVariable Long tagId){
        List<Motorcycle> motorcycles = motorcycleService.getAllByTagId(tagId);
        return new ResponseEntity<>(motorcycles, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Motorcycle> addMotorcycle(@RequestBody Motorcycle motorcycle){
        Motorcycle newMotorcycle = motorcycleService.addMotorcycle(motorcycle);
        return new ResponseEntity<>(newMotorcycle, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Motorcycle> updateMotorcycle(@RequestBody Motorcycle motorcycle){
        Motorcycle updateMotorcycle = motorcycleService.updateMotorcycle(motorcycle);
        return new ResponseEntity<>(updateMotorcycle, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMotorcycle(@PathVariable("id") Long id){
        motorcycleService.deleteMotorcycle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
