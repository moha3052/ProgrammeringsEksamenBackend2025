package com.example.programmeringseksamenbackend2025.config;

import com.example.programmeringseksamenbackend2025.entity.Pizza;
import com.example.programmeringseksamenbackend2025.entity.Station;
import com.example.programmeringseksamenbackend2025.repository.DroneRepository;
import com.example.programmeringseksamenbackend2025.repository.LeveringRepository;
import com.example.programmeringseksamenbackend2025.repository.PizzaRepository;
import com.example.programmeringseksamenbackend2025.repository.StationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final PizzaRepository pizzaRepository;
    private final StationRepository stationRepository;


    public DataLoader(PizzaRepository pizzaRepository, StationRepository stationRepository, LeveringRepository leveringRepository, DroneRepository droneRepository) {
        this.pizzaRepository = pizzaRepository;
        this.stationRepository = stationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Indsæt Pizza
        pizzaRepository.saveAll(List.of(
                new Pizza("Margherita", 69),
                new Pizza("Pepperoni", 79),
                new Pizza("Hawaii", 89),
                new Pizza("Quattro Formaggi", 95),
                new Pizza( "Meat Lovers", 99)
        ));

        // Indsæt Station
        stationRepository.saveAll(List.of(
                new Station(55.50, 12.40),
                new Station(55.35, 12.45),
                new Station(55.60, 12.30)
        ));

    }
}
