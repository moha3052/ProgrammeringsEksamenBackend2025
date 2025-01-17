package com.example.programmeringseksamenbackend2025.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;


import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Table(name = "Leverings")
public class Levering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adresse;
    private LocalTime forventet_levering;
    @Column(name = "faktisk_levering")
    private LocalTime faktiskLevering;

    @ManyToOne
    @JoinColumn(name = "drone_id")
    @JsonIgnore
    private Drone drone; // Levering kan udføres af én drone (valgfrit)

    @ManyToOne
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza; // Hver levering indeholder én pizza


    public Levering(Long id, String adresse, LocalTime forventet_levering, LocalTime faktiskLevering) {
        this.id = id;
        this.adresse = adresse;
        this.forventet_levering = forventet_levering;
        this.faktiskLevering = faktiskLevering;
    }
    public Levering(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalTime getForventet_levering() {
        return forventet_levering;
    }

    public void setForventet_levering(LocalTime forventet_levering) {
        this.forventet_levering = forventet_levering;
    }

    public LocalTime getFaktisk_levering() {
        return faktiskLevering;
    }

    public void setFaktisk_levering(LocalTime faktiskLevering) {
        this.faktiskLevering = faktiskLevering;
    }

    public Drone getDrone() {
        return drone;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
}
