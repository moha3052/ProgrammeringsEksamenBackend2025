package com.example.programmeringseksamenbackend2025.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "station")
    private List<Drone> droner; // En station kan have flere droner

    public Station(Long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude; // Breddegrad
        this.longitude = longitude; // Længdegrad
    }

    public Station(){
    }


}
