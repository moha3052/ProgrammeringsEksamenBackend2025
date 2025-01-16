package com.example.programmeringseksamenbackend2025.entity;

import jakarta.persistence.*;
import java.util.List;


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

    public Station(double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude; // Breddegrad
        this.longitude = longitude; // Længdegrad
    }

    public Station(){
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public List<Drone> getDroner() {
        return droner;
    }

    public void setDroner(List<Drone> droner) {
        this.droner = droner;
    }
}
