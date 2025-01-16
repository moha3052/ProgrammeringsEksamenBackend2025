package com.example.programmeringseksamenbackend2025.controller;

import com.example.programmeringseksamenbackend2025.entity.Levering;
import com.example.programmeringseksamenbackend2025.service.LeveringService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/levering")
@CrossOrigin("*")
public class LeveringController {

    private final LeveringService leveringService;

    public LeveringController(LeveringService leveringService){
        this.leveringService = leveringService;
    }

    @GetMapping
    public ResponseEntity<List<Levering>> getAllLevering(){
        List<Levering> allLeverings = leveringService.getAllLevering();
        return ResponseEntity.ok(allLeverings);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Levering>> getAllLeveringById(@PathVariable Long id){
        Optional<Levering> allLeveringById = leveringService.getAllLeveringById(id);
        return ResponseEntity.ok(allLeveringById);
    }

    @PostMapping
    public ResponseEntity<Levering> createLevering(@RequestBody Levering levering){
        Levering createLevering = leveringService.createLevering(levering);
        return new ResponseEntity<>(createLevering, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ResponseEntity<Levering> updateLevering(@PathVariable Long id, @RequestBody Levering updateLevering){
        try {
            Levering existingLevering = leveringService.updateLeveringById(id, updateLevering);
            return ResponseEntity.ok(existingLevering);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLeveringById(@PathVariable Long id){
        try {
            leveringService.deleteLevering(id);
            return ResponseEntity.notFound().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
