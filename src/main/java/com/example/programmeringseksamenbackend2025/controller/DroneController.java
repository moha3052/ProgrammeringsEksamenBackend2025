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
        Optional<Drone> allDronesById = droneService.getDronesById(id);

        return ResponseEntity.ok(allDronesById);
    }

    // Endpoint til at oprette en ny drone og koble den til stationen med færrest droner
    @PostMapping("/add")
    public ResponseEntity<Drone> createDrone(){
       try {
           Drone newDrone = droneService.addDrone();
           return ResponseEntity.ok(newDrone);
       }catch (RuntimeException  e){
           return ResponseEntity.badRequest().body(null);
       }
    }

    @PutMapping("{id}")
    public ResponseEntity<Drone> updateDroneById(@PathVariable Long id, @RequestBody Drone drone){
        try {
            Drone updateDrone = droneService.updateDrone(id, drone);
            return ResponseEntity.ok(updateDrone);
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDroneById(@PathVariable Long id){
        try {
            droneService.deleteDroneById(id);
            return ResponseEntity.noContent().build(); // Returnerer HTTP 204, hvis sletningen lykkes
        } catch (RuntimeException  e){
            return ResponseEntity.notFound().build(); // Returnerer HTTP 404, hvis pizzaen ikke findes
        }
    }

    // Endpoint til at ændre status på en drone til "i drift"
    @PutMapping("/{id}/enable")
    public ResponseEntity<Drone> enableDrone(@PathVariable Long id) {
        return updateDroneStatus(id, Driftsstatus.I_DRIFT);
    }

    // Endpoint til at ændre status på en drone til "ude af drift"
    @PutMapping("/{id}/disable")
    public ResponseEntity<Drone> disableDrone(@PathVariable Long id) {
        return updateDroneStatus(id, Driftsstatus.UDE_AF_DRIFT);
    }

    // Endpoint til at ændre status på en drone til "udfaset"
    @PutMapping("/{id}/retire")
    public ResponseEntity<Drone> retireDrone(@PathVariable Long id) {
        return updateDroneStatus(id, Driftsstatus.UDFASET);
    }

    // Fælles metode til statusopdatering
    private ResponseEntity<Drone> updateDroneStatus(Long id, Driftsstatus status) {
        try {
            Drone updatedDrone = droneService.updateDroneStatus(id, status);
            return ResponseEntity.ok(updatedDrone);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
