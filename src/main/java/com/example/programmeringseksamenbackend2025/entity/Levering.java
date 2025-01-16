package com.example.programmeringseksamenbackend2025.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Leverings")
public class Levering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private LocalDateTime forventet_levering;
    private LocalDateTime faktisk_levering;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    private Drone drone; // Levering kan udføres af én drone (valgfrit)

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza; // Hver levering indeholder én pizza


    public Levering(Long id, String adresse, LocalDateTime forventet_levering, LocalDateTime faktisk_levering) {
        this.id = id;
        this.adresse = adresse;
        this.forventet_levering = forventet_levering;
        this.faktisk_levering = faktisk_levering;
    }
    public Levering(){

    }
}
