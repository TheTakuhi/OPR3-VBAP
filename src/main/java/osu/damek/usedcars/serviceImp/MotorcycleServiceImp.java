package osu.damek.usedcars.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import osu.damek.usedcars.exception.NotFoundException;
import osu.damek.usedcars.model.Motorcycle;
import osu.damek.usedcars.repository.MotorcycleRepository;

import java.util.List;

@Service
public class MotorcycleServiceImp {
    private final MotorcycleRepository motorcycleRepository;

    @Autowired
    public MotorcycleServiceImp(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    public Motorcycle addMotorcycle(Motorcycle motorcycle){
        return motorcycleRepository.save(motorcycle);
    }

    public List<Motorcycle> findAllMotorcycles(){
        return motorcycleRepository.findAll();
    }

    public List<Motorcycle> getAllByTagId(Long tagId) { return motorcycleRepository.findAllByTagsId(tagId);}

    public Motorcycle updateMotorcycle(Motorcycle motorcycle){
        return motorcycleRepository.save(motorcycle);
    }

    public Motorcycle findMotorcycleById(Long id){
        return motorcycleRepository.findMotorcycleById(id).orElseThrow(() -> new NotFoundException("Motorcycle with id: " + id + " was not found"));
    }

    public void deleteMotorcycle(Long id){
        motorcycleRepository.deleteMotorcycleById(id);
    }
}
