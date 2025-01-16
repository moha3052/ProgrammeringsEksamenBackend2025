package com.example.programmeringseksamenbackend2025.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double pris;


    public Pizza(Long id, String name, double pris) {
        this.id = id;
        this.name = name;
        this.pris = pris;
    }

    public Pizza(){

    }

}
