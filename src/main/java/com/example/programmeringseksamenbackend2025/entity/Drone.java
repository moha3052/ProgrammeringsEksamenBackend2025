package com.example.programmeringseksamenbackend2025.entity;

import com.example.programmeringseksamenbackend2025.enums.Driftsstatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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
}
