package com.example.programmeringseksamenbackend2025.controller;

import com.example.programmeringseksamenbackend2025.entity.Drone;
import com.example.programmeringseksamenbackend2025.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/drones")
@CrossOrigin("*")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService){
        this.droneService = droneService;
    }

    @GetMapping
    public ResponseEntity<List<Drone>> getAllDrones(){
        List<Drone> allDrones = droneService.getAllDrones();
        return ResponseEntity.ok(allDrones);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Drone>> getAllDronesById(@PathVariable Long id){
        Optional<Drone> allDronesById = droneService.getAllDronesById(id);

        return ResponseEntity.ok(allDronesById);
    }

    @PostMapping
    public ResponseEntity<Drone> createDrone(@RequestBody Drone drone){
        Drone createDrone = droneService.createDrone(drone);
        return new ResponseEntity<>(createDrone, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Drone> updateDroneById(@PathVariable Long id, @RequestBody Drone drone){
        try {
            Drone updateDrone = droneService.updateDrone(id, drone);
            return ResponseEntity.ok(updateDrone);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDroneById(@PathVariable Long id){
        try {
            droneService.deleteDronesById(id);
            return ResponseEntity.noContent().build(); // Returnerer HTTP 204, hvis sletningen lykkes
        } catch (Exception e){
            return ResponseEntity.notFound().build(); // Returnerer HTTP 404, hvis pizzaen ikke findes
        }
    }


}
