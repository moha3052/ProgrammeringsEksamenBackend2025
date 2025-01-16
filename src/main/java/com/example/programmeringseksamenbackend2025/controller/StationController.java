package com.example.programmeringseksamenbackend2025.controller;

import com.example.programmeringseksamenbackend2025.entity.Station;
import com.example.programmeringseksamenbackend2025.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/station")
@CrossOrigin("*")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService){
        this.stationService = stationService;
    }

    @GetMapping
    public ResponseEntity<List<Station>> getAllStations(){
        List<Station> allStations = stationService.getAllStations();
        return ResponseEntity.ok(allStations);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Station>> getAllStationById(@PathVariable Long id){
        Optional<Station> allStationById = stationService.getAllStationById(id);
        return ResponseEntity.ok(allStationById);
    }

    @PostMapping
    public ResponseEntity<Station> createStation(@RequestBody Station station){
        Station createStation = stationService.createStation(station);
        return new ResponseEntity<>(createStation, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<Station> updateStation(@PathVariable Long id, @RequestBody Station updatStation){
        try {
            Station existingStation = stationService.updateStationById(id, updatStation);
            return ResponseEntity.ok(existingStation);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStationById(@PathVariable Long id){
        try {
           stationService.deleteStationById(id);
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
