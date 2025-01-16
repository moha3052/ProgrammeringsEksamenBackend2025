package com.example.programmeringseksamenbackend2025.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Levering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private LocalDateTime forventet_levering;
    private LocalDateTime faktisk_levering;

    public Levering(Long id, String adresse, LocalDateTime forventet_levering, LocalDateTime faktisk_levering) {
        this.id = id;
        this.adresse = adresse;
        this.forventet_levering = forventet_levering;
        this.faktisk_levering = faktisk_levering;
    }
    public Levering(){

    }
}
