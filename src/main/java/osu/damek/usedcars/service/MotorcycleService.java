package osu.damek.usedcars.service;

import osu.damek.usedcars.model.Motorcycle;

import java.util.List;

public interface MotorcycleService {
    List<Motorcycle> getAllMotorcycles();
    List<Motorcycle> getAllByTagId(Long tagId);
    Motorcycle getMotorcycleById(Long id);
    Motorcycle addMotorcycle(Motorcycle motorcycle);
    Motorcycle updateMotorcycle(Motorcycle motorcycle);
    void deleteMotorcycle(Long id);
}
