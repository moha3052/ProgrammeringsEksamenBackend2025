package com.example.programmeringseksamenbackend2025.entity;

import com.example.programmeringseksamenbackend2025.enums.Driftsstatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "Drones")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serial_uuid;
    @Enumerated(EnumType.STRING)
    private Driftsstatus driftsstatus;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    @JsonBackReference
    private Station station; // Drone er knyttet til én station

    @OneToMany(mappedBy = "drone")
    @JsonIgnore
    private List<Levering> leveringer; // En drone kan udføre flere leveringer

    public Drone(String serial_uuid, Driftsstatus driftsstatus) {
        this.serial_uuid = serial_uuid;
        this.driftsstatus = driftsstatus;
    }

    public Drone(){

    }


    public String getSerial_uuid() {
        return serial_uuid;
    }

    public void setSerial_uuid(String serial_uuid) {
        this.serial_uuid = serial_uuid;
    }

    public Driftsstatus getDriftsstatus() {
        return driftsstatus;
    }

    public void setDriftsstatus(Driftsstatus driftsstatus) {
        this.driftsstatus = driftsstatus;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<Levering> getLeveringer() {
        return leveringer;
    }

    public void setLeveringer(List<Levering> leveringer) {
        this.leveringer = leveringer;
    }
}
