package com.example.programmeringseksamenbackend2025.service;

import com.example.programmeringseksamenbackend2025.entity.Levering;
import com.example.programmeringseksamenbackend2025.repository.LeveringRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeveringService {
    private final LeveringRepository leveringRepository;

    public LeveringService(LeveringRepository leveringRepository){
        this.leveringRepository = leveringRepository;
    }

    public List<Levering> getAllLevering(){
        return leveringRepository.findAll();
    }

    public Optional<Levering> getAllLeveringById(Long id){
        return leveringRepository.findById(id);
    }

    public Levering createLevering(Levering levering){
        return leveringRepository.save(levering);
    }

    public Levering updateLeveringById(Long id, Levering updateLevering){
        Levering existingLevering = leveringRepository.findById(id)
                .orElseThrow( () -> new RuntimeException());

        existingLevering.setFaktisk_levering(updateLevering.getFaktisk_levering());
        existingLevering.setForventet_levering(updateLevering.getForventet_levering());
        existingLevering.setAdresse(updateLevering.getAdresse());
        existingLevering.setDrone(updateLevering.getDrone());
        existingLevering.setPizza(updateLevering.getPizza());

        return leveringRepository.save(existingLevering);
    }

    public void deleteLevering(Long id){
        leveringRepository.deleteById(id);
    }
}
