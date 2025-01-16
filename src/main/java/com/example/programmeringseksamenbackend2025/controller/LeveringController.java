package com.example.programmeringseksamenbackend2025.controller;

import com.example.programmeringseksamenbackend2025.entity.Levering;
import com.example.programmeringseksamenbackend2025.service.LeveringService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/levering")
@CrossOrigin("*")
public class LeveringController {

    private final LeveringService leveringService;

    public LeveringController(LeveringService leveringService){
        this.leveringService = leveringService;
    }

    /**
     * Henter alle leveringer.
     * @return Liste over alle leveringer.
     */
    @GetMapping
    public ResponseEntity<List<Levering>> getAllLevering() {
        List<Levering> allLeverings = leveringService.getAllLevering();
        return ResponseEntity.ok(allLeverings);
    }

    /**
     * Endpoint for at hente alle leveringer, hvor faktiskLevering er null.
     * Dette repræsenterer uafsluttede leveringer.
     *
     * @return ResponseEntity med en liste over uafsluttede leveringer som JSON.
     */
    @GetMapping("/unfinished")
    public ResponseEntity<List<Levering>> getUnfinishedLeveringer() {
        // Henter listen af uafsluttede leveringer fra serviceklassen
        List<Levering> unfinishedLeveringer = leveringService.getUnfinishedLeveringer();

        // Returnerer listen pakket ind i en ResponseEntity med HTTP-status 200 OK
        return ResponseEntity.ok(unfinishedLeveringer);
    }

    /**
     * Henter en levering baseret på ID.
     * @param id Leveringens ID.
     * @return Leveringsobjekt, hvis det findes.
     */
    @GetMapping("{id}")
    public ResponseEntity<Optional<Levering>> getAllLeveringById(@PathVariable Long id) {
        Optional<Levering> allLeveringById = leveringService.getAllLeveringById(id);
        return ResponseEntity.ok(allLeveringById);
    }

    /**
     * Opretter en ny levering.
     * @param levering Leveringsobjekt, der skal oprettes.
     * @return Oprettet leveringsobjekt.
     */
    @PostMapping("/add")
    public ResponseEntity<Levering> createLevering(@RequestBody Levering levering) {
        try {
            Levering savedLevering = leveringService.createLevering(levering);
            return new ResponseEntity<>(savedLevering, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Opdaterer en eksisterende levering baseret på ID.
     * @param id Leveringens ID.
     * @param updateLevering Opdateret leveringsobjekt.
     * @return Opdateret leveringsobjekt.
     */
    @PutMapping("{id}")
    public ResponseEntity<Levering> updateLevering(@PathVariable Long id, @RequestBody Levering updateLevering) {
        try {
            Levering existingLevering = leveringService.updateLeveringById(id, updateLevering);
            return ResponseEntity.ok(existingLevering);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Sletter en levering baseret på ID.
     * @param id Leveringens ID.
     * @return HTTP-statuskode for operationen.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteLeveringById(@PathVariable Long id) {
        try {
            leveringService.deleteLevering(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Finder alle leveringer uden en tildelt drone.
     * @return Liste over leveringer uden drone.
     */
    @GetMapping("/queue")
    public ResponseEntity<List<Levering>> getQueuedLeveringer() {
        List<Levering> queuedLeverings = leveringService.getQueuedLeveringer();
        return ResponseEntity.ok(queuedLeverings);
    }

    /**
     * Tildeler en drone til en levering.
     * @param leveringId Leveringens ID.
     * @param droneId Dronens ID.
     * @return Opdateret leveringsobjekt.
     */
    @PostMapping("/schedule/{leveringId}/{droneId}")
    public ResponseEntity<Levering> scheduleLevering(@PathVariable Long leveringId, @PathVariable Long droneId) {
        try {
            Levering scheduledLevering = leveringService.scheduleLevering(droneId, leveringId);
            return ResponseEntity.ok(scheduledLevering);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Markerer en levering som færdig.
     * @param id Leveringens ID.
     * @return Opdateret leveringsobjekt.
     */
    @PostMapping("/finish/{id}")
    public ResponseEntity<Levering> finishLevering(@PathVariable Long id) {
        try {
            Levering finishedLevering = leveringService.finishLevering(id);
            return ResponseEntity.ok(finishedLevering);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
