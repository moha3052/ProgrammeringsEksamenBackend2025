package com.example.programmeringseksamenbackend2025.service;

import com.example.programmeringseksamenbackend2025.entity.Drone;
import com.example.programmeringseksamenbackend2025.repository.DroneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DroneService {

    private final DroneRepository droneRepository;

    public DroneService(DroneRepository droneRepository){
        this.droneRepository = droneRepository;
    }

    public List<Drone> getAllDrones(){
        return droneRepository.findAll();
    }

    public Optional<Drone> getAllDronesById(Long id){
        return droneRepository.findById(id);
    }

    public Drone createDrone(Drone drone){
        return droneRepository.save(drone);
    }

    public Drone updateDrone(Long id, Drone updateDrones){
        Drone existingDrone = droneRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Drone blev ikke fundet"));

        existingDrone.setSerial_uuid(updateDrones.getSerial_uuid());
        existingDrone.setDriftsstatus(updateDrones.getDriftsstatus());
        existingDrone.setLeveringer(updateDrones.getLeveringer());
        existingDrone.setStation(updateDrones.getStation());

        return droneRepository.save(existingDrone);
    }

    public void deleteDronesById(Long id){
        droneRepository.deleteById(id);
    }
}
