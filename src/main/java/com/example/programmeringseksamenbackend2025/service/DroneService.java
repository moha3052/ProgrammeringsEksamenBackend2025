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

    public DroneService(DroneRepository droneRepository, StationRepository stationRepository) {
        this.droneRepository = droneRepository;
        this.stationRepository = stationRepository;
    }

    /**
     * Henter alle droner.
     *
     * @return Liste over alle droner.
     */
    public List<Drone> getAllDrones() {
        return droneRepository.findAll();
    }

    /**
     * Henter en drone baseret på ID.
     *
     * @param id Dronens ID.
     * @return Den fundne drone eller tomt resultat.
     */
    public Optional<Drone> getDronesById(Long id) {
        return droneRepository.findById(id);
    }

    /**
     * Tilføjer en ny drone til stationen med færrest droner.
     *
     * @return Den oprettede drone.
     */
    public Drone addDrone() {
        List<Station> stations = stationRepository.findAll();

        if (stations.isEmpty()) {
            throw new RuntimeException("Ingen stationer i databasen. Kan ikke oprette en ny drone.");
        }

        Station stationWithFewestDrones = stations.stream()
                .min(Comparator.comparingInt(station -> station.getDroner().size()))
                .orElseThrow(() -> new RuntimeException("Ingen station fundet."));

        Drone drone = new Drone();
        drone.setSerial_uuid(UUID.randomUUID().toString());
        drone.setDriftsstatus(Driftsstatus.I_DRIFT);
        drone.setStation(stationWithFewestDrones);

        return droneRepository.save(drone);
    }

    /**
     * Opdaterer status for en drone.
     *
     * @param id     Dronens ID.
     * @param status Den nye status.
     * @return Den opdaterede drone.
     */
    public Drone updateDroneStatus(Long id, Driftsstatus status) {
        Drone drone = droneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drone not found with id: " + id));
        drone.setDriftsstatus(status);
        return droneRepository.save(drone);
    }

    /**
     * Opdaterer en drones informationer.
     *
     * @param id            Dronens ID.
     * @param updateDrones  De opdaterede droneinformationer.
     * @return Den opdaterede drone.
     */
    public Drone updateDrone(Long id, Drone updateDrones) {
        Drone existingDrone = droneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Drone blev ikke fundet"));

        existingDrone.setSerial_uuid(updateDrones.getSerial_uuid());
        existingDrone.setDriftsstatus(updateDrones.getDriftsstatus());
        existingDrone.setStation(updateDrones.getStation());

        return droneRepository.save(existingDrone);
    }

    /**
     * Sletter en drone baseret på ID.
     *
     * @param id Dronens ID.
     */
    public void deleteDroneById(Long id) {
        droneRepository.deleteById(id);
    }
}
