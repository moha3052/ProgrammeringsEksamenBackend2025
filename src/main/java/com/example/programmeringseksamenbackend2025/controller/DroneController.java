package com.example.programmeringseksamenbackend2025.controller;

import com.example.programmeringseksamenbackend2025.entity.Drone;
import com.example.programmeringseksamenbackend2025.enums.Driftsstatus;
import com.example.programmeringseksamenbackend2025.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/drones")
@CrossOrigin("*")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    /**
     * Hent alle droner.
     *
     * @return Liste over alle droner.
     */
    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones() {
        List<Drone> allDrones = droneService.getAllDrones();
        return ResponseEntity.ok(allDrones);
    }

    /**
     * Hent en drone baseret på ID.
     *
     * @param id Drone ID.
     * @return Den fundne drone eller tomt resultat.
     */
    @GetMapping("{id}")
    public ResponseEntity<Optional<Drone>> getAllDronesById(@PathVariable Long id) {
        Optional<Drone> allDronesById = droneService.getDronesById(id);
        return ResponseEntity.ok(allDronesById);
    }

    /**
     * Opret en ny drone og koble den til stationen med færrest droner.
     *
     * @return Den oprettede drone.
     */
    @PostMapping("/add")
    public ResponseEntity<Drone> createDrone() {
        try {
            Drone newDrone = droneService.addDrone();
            return ResponseEntity.ok(newDrone);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Opdater en drone baseret på ID.
     *
     * @param id    Drone ID.
     * @param drone Opdateret drone-data.
     * @return Den opdaterede drone.
     */
    @PutMapping("{id}")
    public ResponseEntity<Drone> updateDroneById(@PathVariable Long id, @RequestBody Drone drone) {
        try {
            Drone updateDrone = droneService.updateDrone(id, drone);
            return ResponseEntity.ok(updateDrone);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Slet en drone baseret på ID.
     *
     * @param id Drone ID.
     * @return HTTP 204 ved succes, HTTP 404 hvis dronen ikke findes.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDroneById(@PathVariable Long id) {
        try {
            droneService.deleteDroneById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Skift status på en drone til "i drift".
     *
     * @param id Drone ID.
     * @return Den opdaterede drone.
     */
    @PutMapping("/{id}/enable")
    public ResponseEntity<Drone> enableDrone(@PathVariable Long id) {
        return updateDroneStatus(id, Driftsstatus.I_DRIFT);
    }

    /**
     * Skift status på en drone til "ude af drift".
     *
     * @param id Drone ID.
     * @return Den opdaterede drone.
     */
    @PutMapping("/{id}/disable")
    public ResponseEntity<Drone> disableDrone(@PathVariable Long id) {
        return updateDroneStatus(id, Driftsstatus.UDE_AF_DRIFT);
    }

    /**
     * Skift status på en drone til "udfaset".
     *
     * @param id Drone ID.
     * @return Den opdaterede drone.
     */
    @PutMapping("/{id}/retire")
    public ResponseEntity<Drone> retireDrone(@PathVariable Long id) {
        return updateDroneStatus(id, Driftsstatus.UDFASET);
    }

    /**
     * Fælles metode til at opdatere dronens status.
     *
     * @param id     Drone ID.
     * @param status Ny driftsstatus.
     * @return Den opdaterede drone eller HTTP 404, hvis dronen ikke findes.
     */
    private ResponseEntity<Drone> updateDroneStatus(Long id, Driftsstatus status) {
        try {
            Drone updatedDrone = droneService.updateDroneStatus(id, status);
            return ResponseEntity.ok(updatedDrone);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
