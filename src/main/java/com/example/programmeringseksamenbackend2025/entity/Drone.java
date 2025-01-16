package com.example.programmeringseksamenbackend2025.entity;

import com.example.programmeringseksamenbackend2025.enums.Driftsstatus;
import jakarta.persistence.*;


import java.util.List;

@Entity
@Table(name = "Drones")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int serial_uuid;
    @Enumerated(EnumType.STRING)
    private Driftsstatus driftsstatus;

    @ManyToOne
    @JoinColumn(name = "station_id", nullable = false)
    private Station station; // Drone er knyttet til én station

    @OneToMany(mappedBy = "drone")
    private List<Levering> leveringer; // En drone kan udføre flere leveringer

    public Drone(Long id, int serial_uuid, Driftsstatus driftsstatus) {
        this.id = id;
        this.serial_uuid = serial_uuid;
        this.driftsstatus = driftsstatus;
    }

    public Drone(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSerial_uuid() {
        return serial_uuid;
    }

    public void setSerial_uuid(int serial_uuid) {
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
