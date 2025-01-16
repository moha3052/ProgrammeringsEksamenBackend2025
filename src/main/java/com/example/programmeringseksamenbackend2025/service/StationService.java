package com.example.programmeringseksamenbackend2025.service;

import com.example.programmeringseksamenbackend2025.entity.Station;
import com.example.programmeringseksamenbackend2025.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {

    private final StationRepository stationRepository;

    public StationService(StationRepository stationRepository){
        this.stationRepository = stationRepository;
    }

    public List<Station> getAllStations(){
        return stationRepository.findAll();
    }

    public Optional<Station> getAllStationById(Long id){
        return stationRepository.findById(id);
    }

    public Station createStation(Station station){
        return stationRepository.save(station);
    }

    public Station updateStationById(Long id, Station updateStation){
        Station existingStation = stationRepository.findById(id)
                .orElseThrow( () -> new RuntimeException("Station blev ikke fundet"));

        existingStation.setLatitude(updateStation.getLatitude());
        existingStation.setLongitude(updateStation.getLongitude());
        existingStation.setDroner(updateStation.getDroner());

        return stationRepository.save(existingStation);
    }


    public void deleteStationById(Long id){
        stationRepository.deleteById(id);
    }
}
