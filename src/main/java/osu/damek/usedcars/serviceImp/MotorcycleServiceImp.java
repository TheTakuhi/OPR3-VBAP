package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.NotFoundException;
import osu.damek.usedcars.model.Motorcycle;
import osu.damek.usedcars.repository.MotorcycleRepository;
import osu.damek.usedcars.service.MotorcycleService;

import java.util.List;

@Service
public class MotorcycleServiceImp implements MotorcycleService {
    private final MotorcycleRepository motorcycleRepository;

    @Autowired
    public MotorcycleServiceImp(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<Motorcycle> getAllMotorcycles(){
        return motorcycleRepository.findAll();
    }

    public List<Motorcycle> getAllByTagId(Long tagId) { return motorcycleRepository.getAllByTagsId(tagId);}

    public Motorcycle getMotorcycleById(Long id){
        return motorcycleRepository.getMotorcycleById(id).orElseThrow(() -> new NotFoundException("Motorcycle with id: " + id + " was not found"));
    }

    public Motorcycle addMotorcycle(Motorcycle motorcycle){
        return motorcycleRepository.save(motorcycle);
    }

    public Motorcycle updateMotorcycle(Motorcycle motorcycle){
        return motorcycleRepository.save(motorcycle);
    }

    public void deleteMotorcycle(Long id){
        motorcycleRepository.deleteMotorcycleById(id);
    }
}
