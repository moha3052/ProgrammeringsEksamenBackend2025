package com.example.programmeringseksamenbackend2025.service;

import com.example.programmeringseksamenbackend2025.entity.Drone;
import com.example.programmeringseksamenbackend2025.entity.Station;
import com.example.programmeringseksamenbackend2025.enums.Driftsstatus;
import com.example.programmeringseksamenbackend2025.repository.DroneRepository;
import com.example.programmeringseksamenbackend2025.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DroneService {

    private final DroneRepository droneRepository;
    private final StationRepository stationRepository;

    public DroneService(DroneRepository droneRepository, StationRepository stationRepository){
        this.droneRepository = droneRepository;
        this.stationRepository = stationRepository;
    }

    public List<Drone> getAllDrones(){
        return droneRepository.findAll();
    }

    public Optional<Drone> getDronesById(Long id){
        return droneRepository.findById(id);
    }

    public Drone addDrone(){
        List<Station> stations = stationRepository.findAll();

        // Hvis der ikke er stationer, kast en exception
        if (stations.isEmpty()){
            throw new RuntimeException("Ingen stationer i databasen. Kan ikke oprette en ny drone.");
        }

        // Find stationen med færrest droner
        Station stationWithFewestDrones = stations.stream()
                .min(Comparator.comparingInt(station -> station.getDroner().size()))
                .orElseThrow(() -> new RuntimeException("Ingen station fundet."));


        // Opret en ny drone med tilfældigt UUID og status "i drift"
        Drone drone = new Drone();
        drone.setSerial_uuid(UUID.randomUUID().toString());
        drone.setDriftsstatus(Driftsstatus.I_DRIFT);
        drone.setStation(stationWithFewestDrones);

        // Gem og returner dronen
        return droneRepository.save(drone);
    }

    public Drone updateDroneStatus(Long id, Driftsstatus status) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drone not found with id: " + id));
        drone.setDriftsstatus(status); // Opdaterer kun statusfeltet
        return droneRepository.save(drone); // Gemmer ændringer i databasen
    }



    public Drone updateDrone(Long id, Drone updateDrones){
        Drone existingDrone = droneRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Drone blev ikke fundet"));

        existingDrone.setSerial_uuid(updateDrones.getSerial_uuid());
        existingDrone.setDriftsstatus(updateDrones.getDriftsstatus());
        existingDrone.setStation(updateDrones.getStation());

        return droneRepository.save(existingDrone);
    }

    public void deleteDroneById(Long id){
        droneRepository.deleteById(id);
    }
}
