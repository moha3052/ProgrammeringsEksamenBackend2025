package com.example.programmeringseksamenbackend2025.entity;

import com.example.programmeringseksamenbackend2025.enums.Driftsstatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int serial_uuid;
    private Driftsstatus driftsstatus;

    public Drone(Long id, int serial_uuid, Driftsstatus driftsstatus) {
        this.id = id;
        this.serial_uuid = serial_uuid;
        this.driftsstatus = driftsstatus;
    }

    public Drone(){

    }
}
